package vn.name.greenwich.appstorephone.Category;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import retrofit2.Retrofit;
import vn.name.greenwich.appstorephone.Adapter.PhoneAdapter;
import vn.name.greenwich.appstorephone.Class.Product;
import vn.name.greenwich.appstorephone.R;
import vn.name.greenwich.appstorephone.Retrofit.ApiProduct;
import vn.name.greenwich.appstorephone.Retrofit.RetrofitClient;
import vn.name.greenwich.appstorephone.Server.Connect;
import vn.thanguit.toastperfect.ToastPerfect;

public class PhoneActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    ApiProduct apiProduct;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    // truyen san pham vao
    int page = 1;
    int type;
    PhoneAdapter phoneAdapter;
    List<Product> productList;
    LinearLayoutManager linearLayoutManager;
    // Tạo xem thêm sản phẩm trong phone, laptop
    Handler handler = new Handler();
    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        apiProduct = RetrofitClient.getInstance(Connect.BASE_URL).create(ApiProduct.class);
        type = getIntent().getIntExtra("type", 1);

        initView();
        ActionToolBar();
        getData(page);
        addEventLoading();
    }

    private void addEventLoading() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isLoading == false){
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == productList.size()-1){
                        isLoading = true;
                        loadingMore();
                    }
                }
            }
        });
    }

    // Tạo để load thêm sản phẩm trong 2s trong phone, laptop
    private void loadingMore() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                // add null
                productList.add(null);
                phoneAdapter.notifyItemInserted(productList.size() - 1);
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // remove null
                productList.remove(productList.size()-1);
                phoneAdapter.notifyItemRemoved(productList.size());
                page = page + 1;
                getData(page);
                phoneAdapter.notifyDataSetChanged();
                isLoading = false;
            }
        }, 2000);
    }

    private void getData(int page) {
        compositeDisposable.add(apiProduct.getProduct(page,type)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                productModel -> {
                    if (productModel.isSuccess()){
                        if (phoneAdapter == null){
                            productList = productModel.getResult();
                            phoneAdapter = new PhoneAdapter(getApplicationContext(), productList);
                            recyclerView.setAdapter(phoneAdapter);
                        }else {
                            int position = productList.size() - 1;
                            int amount_add = productModel.getResult().size();
                            for (int i = 0; i < amount_add; i++){
                                productList.add(productModel.getResult().get(i));
                            }
                            phoneAdapter.notifyItemRangeInserted(position, amount_add);
                        }
                    }else {
                        ToastPerfect.makeText(this,ToastPerfect.INFORMATION,"Hết sản phẩm",ToastPerfect.BOTTOM, Toast.LENGTH_LONG).show();
                        isLoading = true;
                    }
                },
                throwable -> {
                    ToastPerfect.makeText(this,ToastPerfect.ERROR,"Can't connect to server",ToastPerfect.BOTTOM, Toast.LENGTH_LONG).show();
                }
        ));
    }

    private void ActionToolBar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        // bắt sự kiện nút back quay về màn hình chính
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar_phone);
        recyclerView = findViewById(R.id.recyclerview_phone);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        linearLayoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);
        productList = new ArrayList<>();

    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}