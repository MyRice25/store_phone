package vn.name.greenwich.appstorephone.Category;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import vn.name.greenwich.appstorephone.Adapter.Sale_Adapter;
import vn.name.greenwich.appstorephone.Class.Sale_code;
import vn.name.greenwich.appstorephone.R;
import vn.name.greenwich.appstorephone.Retrofit.ApiProduct;
import vn.name.greenwich.appstorephone.Retrofit.RetrofitClient;
import vn.name.greenwich.appstorephone.Server.Connect;
import vn.thanguit.toastperfect.ToastPerfect;

public class Sale_code_Activity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    ApiProduct apiProduct;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    Sale_Adapter saleAdapter;
    List<Sale_code> saleCodeList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sale_code);
        // phần kết nối sản phẩm từ SQL,php
        apiProduct = RetrofitClient.getInstance(Connect.BASE_URL).create(ApiProduct.class);
        initView();
        ActionBar();
        getSale();
    }

    private void getSale() {
        compositeDisposable.add(apiProduct.getSale()
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                sale_code_model -> {
                    if (sale_code_model.isSuccess()){
                        saleCodeList = sale_code_model.getResult();
                        saleAdapter = new Sale_Adapter(getApplicationContext(), saleCodeList);
                        recyclerView.setAdapter(saleAdapter);
                    }
                },
                throwable -> {
                    ToastPerfect.makeText(this,ToastPerfect.ERROR,"Can't connect to server",ToastPerfect.BOTTOM, Toast.LENGTH_LONG).show();
                }
        ));
    }

    private void ActionBar() {
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
        toolbar = findViewById(R.id.toolbar_Sale_code);
        recyclerView = findViewById(R.id.recyclerview_sale);
        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(this, 1);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        saleCodeList = new ArrayList<>();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}