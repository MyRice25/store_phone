package vn.name.greenwich.appstorephone.Account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import vn.name.greenwich.appstorephone.Adapter.Purchase_Adapter;
import vn.name.greenwich.appstorephone.R;
import vn.name.greenwich.appstorephone.Retrofit.ApiProduct;
import vn.name.greenwich.appstorephone.Retrofit.RetrofitClient;
import vn.name.greenwich.appstorephone.Server.Connect;

public class Purchase_order_Activity extends AppCompatActivity {

    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiProduct apiProduct;
    Toolbar toolbar;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_order);
        initView();
        initToolbar();
        getOrder();

    }

    private void getOrder() {
        compositeDisposable.add(apiProduct.viewOrder(Connect.user_current.getId())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        seen_order_model -> {
                            Purchase_Adapter adapter = new Purchase_Adapter(getApplicationContext(), seen_order_model.getResult());
                            recyclerView.setAdapter(adapter);
                        },
                        throwable -> {

                        }
                ));
    }

    private void initToolbar() {
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
        apiProduct = RetrofitClient.getInstance(Connect.BASE_URL).create(ApiProduct.class);
        toolbar = findViewById(R.id.toolbar_seen_order);
        recyclerView = findViewById(R.id.recyclerview_purchase);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}