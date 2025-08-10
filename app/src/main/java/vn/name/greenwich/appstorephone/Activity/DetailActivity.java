package vn.name.greenwich.appstorephone.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.android.material.chip.Chip;
import com.google.android.material.chip.ChipGroup;
import com.google.android.material.textfield.TextInputLayout;
import com.nex3z.notificationbadge.NotificationBadge;

import java.text.DecimalFormat;
import java.util.ArrayList;

import vn.name.greenwich.appstorephone.Class.Cart;
import vn.name.greenwich.appstorephone.Class.Product;
import vn.name.greenwich.appstorephone.Fragment.Fragment_Cart;
import vn.name.greenwich.appstorephone.R;
import vn.name.greenwich.appstorephone.Server.Connect;

public class DetailActivity extends AppCompatActivity implements View.OnClickListener {
    TextView name, price, description;
    Button btn_add;
    ImageView image;
    Spinner spinner;
    Toolbar toolbar;
    Product product_detail;
    NotificationBadge badge;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
        initView();
        ActionToolBar();
        initData_Details();
        //Tạo cái này để Thêm sản phẩm giỏ hàng
        initControl();
        initEvent();
    }

    private void initEvent() {
    }


    //Thêm sản phẩm giỏ hàng
    private void initControl() {
        btn_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                add_Cart();
            }
        });
    }

    private void add_Cart() {
        if (Connect.array_cart.size() > 0) {
            boolean flag = false; // cau lenh khong trung san pham
            int amount = Integer.parseInt(spinner.getSelectedItem().toString());
            for (int i = 0; i < Connect.array_cart.size(); i++){
                if (Connect.array_cart.get(i).getId() == product_detail.getId()){
                    Connect.array_cart.get(i).setAmount(amount + Connect.array_cart.get(i).getAmount());
//                    long price = Long.parseLong(product_detail.getPrice()) * Connect.array_cart.get(i).getAmount();
//                    Connect.array_cart.get(i).setPrice(price);
                    flag = true;
                }
            }
            if (flag == false){
//                long price = Long.parseLong(product_detail.getPrice()) * amount; nguyên bản
                long price = Long.parseLong(product_detail.getPrice());
                Cart cart = new Cart();
                cart.setPrice(price);
                cart.setAmount(amount);
                cart.setId(product_detail.getId());
                cart.setName(product_detail.getName());
                cart.setImage(product_detail.getImage());
                Connect.array_cart.add(cart);
            }
        }else {
            int amount = Integer.parseInt(spinner.getSelectedItem().toString());
//            long price = Long.parseLong(product_detail.getPrice()) * amount;
            long price = Long.parseLong(product_detail.getPrice());
            Cart cart = new Cart();
            cart.setPrice(price);
            cart.setAmount(amount);
            cart.setId(product_detail.getId());
            cart.setName(product_detail.getName());
            cart.setImage(product_detail.getImage());
            Connect.array_cart.add(cart);
        }
        // khai báo lại tổng giá trị đơn hàng được mua
        int totalItem = 0;
        for (int i = 0; i < Connect.array_cart.size(); i++){
            totalItem = totalItem + Connect.array_cart.get(i).getAmount();
        }
        // báo cáo số lượng sản phẩm đc chuyền vào giỏ hàng
        badge.setText(String.valueOf(totalItem));
    }

    private void initData_Details() {
        product_detail = (Product) getIntent().getSerializableExtra("Detail");
        name.setText(product_detail.getName());
        description.setText(product_detail.getDescription());
        Glide.with(getApplicationContext()).load(product_detail.getImage()).into(image);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        price.setText("Price: " + decimalFormat.format(Double.parseDouble(product_detail.getPrice())) + "$");
        // Thêm dữ liệu spinner ( để báo số lượng bạn mua bao nhiêu sản phẩm)
        Integer[] number = new Integer[]{1,2,3,4,5,6,7,8,9,10};
        ArrayAdapter<Integer> adapter_spinner = new ArrayAdapter<>(this, R.layout.item_spinner, number);
        adapter_spinner.setDropDownViewResource(R.layout.item_dropdown_spinner);
        spinner.setAdapter(adapter_spinner);
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        name = findViewById(R.id.txt_name_detail);
        price = findViewById(R.id.txt_price_detail);
        description = findViewById(R.id.txt_detail_product);
        btn_add = findViewById(R.id.btn_add_cart);
        image = findViewById(R.id.img_detail);
        spinner = findViewById(R.id.spinner);
        toolbar = findViewById(R.id.toolbar_detail);
        badge = findViewById(R.id.menu_number);   // tìm id cho để đếm số lượng giỏ hàng
        FrameLayout frameLayout_cart = findViewById(R.id.frame_cart);
        frameLayout_cart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cart = new Intent(getApplicationContext(), Fragment_Cart.class);
                startActivity(cart);
            }
        });
        // Khai báo lưu trữ khi thêm tiếp sản phẩm vào giỏ hàng
        if (Connect.array_cart != null){
            int totalItem = 0;
            for (int i = 0; i < Connect.array_cart.size(); i++){
                totalItem = totalItem + Connect.array_cart.get(i).getAmount();
            }
            badge.setText(String.valueOf(totalItem));
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Khai báo lưu trữ khi thêm tiếp sản phẩm vào giỏ hàng
        if (Connect.array_cart != null){
            int totalItem = 0;
            for (int i = 0; i < Connect.array_cart.size(); i++){
                totalItem = totalItem + Connect.array_cart.get(i).getAmount();
            }
            badge.setText(String.valueOf(totalItem));
        }
    }

    @Override
    public void onClick(View view) {

    }
}