package vn.name.greenwich.appstorephone.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.google.android.material.navigation.NavigationView;
import com.nex3z.notificationbadge.NotificationBadge;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import vn.name.greenwich.appstorephone.Activity.MainActivity;
import vn.name.greenwich.appstorephone.Adapter.Category_Adapter;
import vn.name.greenwich.appstorephone.Adapter.Product_new_Adapter;
import vn.name.greenwich.appstorephone.Class.Product;
import vn.name.greenwich.appstorephone.R;
import vn.name.greenwich.appstorephone.Retrofit.ApiProduct;
import vn.name.greenwich.appstorephone.Retrofit.RetrofitClient;
import vn.name.greenwich.appstorephone.Server.Connect;

public class Fragment_Category extends AppCompatActivity {

    Toolbar toolbar;
    RecyclerView recyclerview;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiProduct apiProduct;
    // Tạo danh sách sản phẩm
    List<Product> arrayProduct;
    Category_Adapter categoryAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        apiProduct = RetrofitClient.getInstance(Connect.BASE_URL).create(ApiProduct.class);
        initView();
        ActionBar();
        get_all_product();
    }

    private void get_all_product() {
        compositeDisposable.add(apiProduct.getTypeNew()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        productModel -> {
                            arrayProduct = productModel.getResult();
                            categoryAdapter = new Category_Adapter(getApplicationContext(), arrayProduct);
                            recyclerview.setAdapter(categoryAdapter);
                        }, throwable -> {
                            Toast.makeText(getApplicationContext(), "Not connect server" + throwable.getMessage(), Toast.LENGTH_LONG).show();
                        }
                ));
    }

    private void ActionBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar_category);
        recyclerview = findViewById(R.id.recyclerview_category);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerview.setLayoutManager(layoutManager);
        recyclerview.setHasFixedSize(true);
        // Khởi tạo list của màn hình chính
        arrayProduct = new ArrayList<>();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}