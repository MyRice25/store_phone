package vn.name.greenwich.appstorephone.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import vn.name.greenwich.appstorephone.Adapter.PhoneAdapter;
import vn.name.greenwich.appstorephone.Class.Product;
import vn.name.greenwich.appstorephone.R;
import vn.name.greenwich.appstorephone.Retrofit.ApiProduct;
import vn.name.greenwich.appstorephone.Retrofit.RetrofitClient;
import vn.name.greenwich.appstorephone.Server.Connect;

public class Search_Activity extends AppCompatActivity {
    RecyclerView recyclerView;
    EditText edt_search;
    TextView cancel;
    PhoneAdapter phoneAdapter;
    List<Product> productList;
    ApiProduct apiProduct;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        initView();
        initControl();
    }

    private void initControl() {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent cancel = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(cancel);
                finish();
            }
        });
    }

    private void initView() {
        productList = new ArrayList<>();
        apiProduct = RetrofitClient.getInstance(Connect.BASE_URL).create(ApiProduct.class);
        recyclerView = findViewById(R.id.recyclerview_search);
        edt_search = findViewById(R.id.edt_search);
        cancel = findViewById(R.id.cancel);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        // tìm kiếm sản phẩm
        edt_search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                
            }

            @Override // hàm đang khi test thay đổi
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (charSequence.length() == 0){
                    productList.clear();
                    phoneAdapter = new PhoneAdapter(getApplicationContext(), productList);
                    recyclerView.setAdapter(phoneAdapter);
                }else {
                    getDataSearch(charSequence.toString());
                }
            }

            @Override// hàm sau khi test thay đổi
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    private void getDataSearch(String str_search) {
        productList.clear();
        compositeDisposable.add(apiProduct.search(str_search)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                productModel -> {
                    if (productModel.isSuccess()){
                        productList = productModel.getResult();
                        phoneAdapter = new PhoneAdapter(getApplicationContext(), productList);
                        recyclerView.setAdapter(phoneAdapter);
                    }
                },
                throwable -> {
                    Toast.makeText(getApplicationContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
                }
        ));
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}