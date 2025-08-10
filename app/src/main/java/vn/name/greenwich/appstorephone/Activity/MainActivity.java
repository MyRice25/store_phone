package vn.name.greenwich.appstorephone.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.navigation.NavigationView;
import com.google.firebase.messaging.FirebaseMessaging;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import vn.name.greenwich.appstorephone.Account.Purchase_order_Activity;
import vn.name.greenwich.appstorephone.Adapter.Menu_nav_Adapter;
import vn.name.greenwich.appstorephone.Adapter.Product_new_Adapter;
import vn.name.greenwich.appstorephone.Category.HeadphoneActivity;
import vn.name.greenwich.appstorephone.Category.LaptopActivity;
import vn.name.greenwich.appstorephone.Category.Location_Activity;
import vn.name.greenwich.appstorephone.Category.PhoneActivity;
import vn.name.greenwich.appstorephone.Category.Sale_code_Activity;
import vn.name.greenwich.appstorephone.Category.TabletActivity;
import vn.name.greenwich.appstorephone.Chatting.Message_user_Activity;
import vn.name.greenwich.appstorephone.Class.Nav_menu;
import vn.name.greenwich.appstorephone.Class.Product;
import vn.name.greenwich.appstorephone.Class.User;
import vn.name.greenwich.appstorephone.Fragment.Fragment_Cart;
import vn.name.greenwich.appstorephone.Fragment.Fragment_Category;
import vn.name.greenwich.appstorephone.Fragment.Fragment_Account;
import vn.name.greenwich.appstorephone.R;
import vn.name.greenwich.appstorephone.Retrofit.ApiProduct;
import vn.name.greenwich.appstorephone.Retrofit.RetrofitClient;
import vn.name.greenwich.appstorephone.Server.Connect;

public class MainActivity extends AppCompatActivity {
    //tạo quảng cáo
    Toolbar toolbar;
    NavigationView navigationView;
    ListView listView_menu;
    ViewFlipper viewFlipper;
    DrawerLayout drawerLayout;
    RecyclerView recyclerview_product_new;
    NotificationBadge badge;
    ImageView img_search, img_message;
    TextView tv_search;
    FrameLayout frame_cart;
    // tạo từng thành phần tìm id cho 6 sản phẩm đt, lap,...
    ImageView imgphone, imglaptop, imgheadphone, imgtablet, img_sale, img_location;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiProduct apiProduct;
    // Tạo danh sách sản phẩm
    List<Product> arrayProduct;
    List<Nav_menu> array_menu;
    Product_new_Adapter productNewAdapter;
    Menu_nav_Adapter menuNavAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        apiProduct = RetrofitClient.getInstance(Connect.BASE_URL).create(ApiProduct.class);
        // lưu ghi nhớ lại tất cả thông tin user đã đkí
        Paper.init(this);
        if (Paper.book().read("user") != null){
            User user = Paper.book().read("user");
            Connect.user_current = user;
        }
        getToken();
        initView();
        ActionBar();

        if (isConnected(this)){
            ActionViewFlipper();
            get_menu_nav();
            // bắt sự kiện cho các menu_nav home, cart, category and setting
            getEventClick();
            get_product_new();
            initClick();
        }else {
            Toast.makeText(getApplicationContext(),"Don't Internet, Please connect!!!", Toast.LENGTH_LONG).show();
        }
    }

    // get token để thông báo
    private void getToken(){
        FirebaseMessaging.getInstance().getToken()
                .addOnSuccessListener(new OnSuccessListener<String>() {
                    @Override
                    public void onSuccess(String token) {
                        // kiểm tra nếu khác rỗng
                        if (!TextUtils.isEmpty(token)) {
                            compositeDisposable.add(apiProduct.update_token(Connect.user_current.getId(), token)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(
                                            insert_model -> {

                                            },
                                            throwable -> {
                                                Log.d("test", throwable.getMessage());
                                            }
                                    ));
                        }
                    }
                });
        // lấy id của admin
        compositeDisposable.add(apiProduct.gettoken(1)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                userModel -> {
                    if (userModel.isSuccess()){
                        Connect.ID_RECEIVED = String.valueOf(userModel.getResult().get(0).getId());
                    }
                },
                throwable -> {

                }
        ));
    }

    private void getEventClick() {
        listView_menu.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                switch (i){
                    case 0:
                        Intent home = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(home);
                        break;
                    case 1:
                        Intent cart = new Intent(getApplicationContext(), Fragment_Cart.class);
                        startActivity(cart);
                        break;
                    case 2:
                        Intent category = new Intent(getApplicationContext(), Fragment_Category.class);
                        startActivity(category);
                        break;
                    case 3:
                        Intent setting = new Intent(getApplicationContext(), Fragment_Account.class);
                        startActivity(setting);
                        break;
                }
            }
        });
    }

    private void get_product_new() {
        compositeDisposable.add(apiProduct.getTypeNew()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        productModel -> {
                            arrayProduct = productModel.getResult();
                            productNewAdapter = new Product_new_Adapter(getApplicationContext(), arrayProduct);
                            recyclerview_product_new.setAdapter(productNewAdapter);
                        }, throwable -> {
                            Toast.makeText(getApplicationContext(), "Not connect server" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }
                ));
    }

    private void get_menu_nav() {
        compositeDisposable.add(apiProduct.get_menu_nav()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                nav_menu_model -> {
                    if (nav_menu_model.isSuccess()){
                        array_menu = nav_menu_model.getResult();
                        // Khởi tạo Adapter cho menu_nav
                        menuNavAdapter = new Menu_nav_Adapter(getApplicationContext(), array_menu);
                        listView_menu.setAdapter(menuNavAdapter);
                    }
                }
        ));
    }

    private void initClick() {
        imgphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent phone = new Intent(getApplicationContext(), PhoneActivity.class);
                phone.putExtra("type", 1);
                startActivity(phone);

            }
        });
        imglaptop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent laptop = new Intent(getApplicationContext(), LaptopActivity.class);
                laptop.putExtra("type", 2);
                startActivity(laptop);
            }
        });
        imgheadphone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent headphone = new Intent(getApplicationContext(), HeadphoneActivity.class);
                headphone.putExtra("type", 3);
                startActivity(headphone);

            }
        });
        imgtablet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent tablet = new Intent(getApplicationContext(), TabletActivity.class);
                tablet.putExtra("type", 4);
                startActivity(tablet);

            }
        });
        img_sale.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent sale = new Intent(getApplicationContext(), Sale_code_Activity.class);
                startActivity(sale);

            }
        });
        img_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent location = new Intent(getApplicationContext(), Location_Activity.class);
                startActivity(location);
            }
        });
    }

    private void ActionViewFlipper() {
        List<String> arrayadvertise = new ArrayList<>();
        arrayadvertise.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-Le-hoi-phu-kien-800-300.png");
        arrayadvertise.add("http://http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-HC-Tra-Gop-800-300.png");
        arrayadvertise.add("http://mauweb.monamedia.net/thegioididong/wp-content/uploads/2017/12/banner-big-ky-nguyen-800-300.jpg");
        arrayadvertise.add("https://www.pdsttechnologyineducation.ie/en/Technology/Purchasing-Frameworks/Purchasing-Frameworks.jpg");
        arrayadvertise.add("data:image/jpeg;base64,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");
        for (int i = 0; i < arrayadvertise.size(); i++) {
            ImageView imageView = new ImageView(getApplicationContext());
            Glide.with(getApplicationContext()).load(arrayadvertise.get(i)).into(imageView);
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
            viewFlipper.addView(imageView);
        }
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);
        Animation slide_in = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_in_right);
        Animation slide_out = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_out_right);
        viewFlipper.setInAnimation(slide_in);
        viewFlipper.setOutAnimation(slide_out);
    }

    private void ActionBar(){
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_menu_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                drawerLayout.openDrawer(GravityCompat.START);
            }
        });
    }


    private void initView() {
        toolbar = findViewById(R.id.toolbarscreen);
        viewFlipper = findViewById(R.id.ViewPaper_advertise);
        drawerLayout = findViewById(R.id.drawerlayout);
        listView_menu = findViewById(R.id.lv_screen_main);
        recyclerview_product_new = findViewById(R.id.Rcv_product_new);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerview_product_new.setLayoutManager(layoutManager);
        recyclerview_product_new.setHasFixedSize(true);
        navigationView = findViewById(R.id.nav_view);
        imgphone = findViewById(R.id.img_phone);
        imglaptop = findViewById(R.id.img_laptop);
        imgtablet = findViewById(R.id.img_tablet);
        imgheadphone = findViewById(R.id.img_Headphone);
        img_sale = findViewById(R.id.img_sale);
        img_location = findViewById(R.id.img_location);
        badge = findViewById(R.id.menu_number);
        frame_cart = findViewById(R.id.frame_cart);
        img_message = findViewById(R.id.img_message);
        // tạo tìm kiếm
        img_search = findViewById(R.id.img_search);
        tv_search = findViewById(R.id.tv_search);
        // Khởi tạo list của menu
        array_menu = new ArrayList<>();
        // Khởi tạo list của màn hình chính
        arrayProduct = new ArrayList<>();
        if (Connect.array_cart == null) {
            Connect.array_cart = new ArrayList<>();
        }else {
            // khai báo lại tổng giá trị đơn hàng được mua
            int totalItem = 0;
            for (int i = 0; i < Connect.array_cart.size(); i++){
                totalItem = totalItem + Connect.array_cart.get(i).getAmount();
            }
            // báo cáo số lượng sản phẩm đc chuyền vào giỏ hàng
            badge.setText(String.valueOf(totalItem));
        }
        frame_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cart = new Intent(getApplicationContext(), Fragment_Cart.class);
                startActivity(cart);
            }
        });

        tv_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Search_Activity.class);
                startActivity(intent);
            }
        });

        img_message.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Message_user_Activity.class);
                startActivity(intent);
            }
        });

    }

    private boolean isConnected(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo wifi = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_WIFI);  // Thêm quyền truy cập bên AndroidManifest  "<uses-permission android:name="android.permission.ACCESS_NETWORK_STATE"/>"
        NetworkInfo mobile = connectivityManager.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
        if ((wifi != null && wifi.isConnected()) || (mobile != null && mobile.isConnected())) {
            return true;
        } else {
            return false;
        }
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    // Chuyền về main khi đã mua sp
    @Override
    protected void onResume() {
        super.onResume();
        // khai báo lại tổng giá trị đơn hàng được mua
        int totalItem = 0;
        for (int i = 0; i < Connect.array_cart.size(); i++){
            totalItem = totalItem + Connect.array_cart.get(i).getAmount();
        }
        // báo cáo số lượng sản phẩm đc chuyền vào giỏ hàng
        badge.setText(String.valueOf(totalItem));
    }
}