package vn.name.greenwich.appstorephone.Category;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import vn.name.greenwich.appstorephone.Adapter.HeadphoneAdapter;
import vn.name.greenwich.appstorephone.Adapter.TabletAdapter;
import vn.name.greenwich.appstorephone.Class.Product;
import vn.name.greenwich.appstorephone.R;
import vn.name.greenwich.appstorephone.Retrofit.ApiProduct;
import vn.name.greenwich.appstorephone.Retrofit.RetrofitClient;
import vn.name.greenwich.appstorephone.Server.Connect;
import vn.thanguit.toastperfect.ToastPerfect;

public class TabletActivity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView;
    ApiProduct apiProduct;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    // truyen san pham vao
    int page = 1;
    int type;
    TabletAdapter adapter_tablet;
    List<Product> productList;
    LinearLayoutManager linearLayoutManager;
    Handler handler = new Handler();
    boolean isLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tablet);
        apiProduct = RetrofitClient.getInstance(Connect.BASE_URL).create(ApiProduct.class);
        type = getIntent().getIntExtra("type", 1);

        initView();
        ActionToolBar();
        getData(page);
        addEventLoad();
    }

    private void addEventLoad() {
        recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
            }

            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (isLoading == false){
                    if (linearLayoutManager.findLastCompletelyVisibleItemPosition() == productList.size() - 1){
                        isLoading = true;
                        loadMore();
                    }
                }
            }
        });
    }

    private void loadMore() {
        handler.post(new Runnable() {
            @Override
            public void run() {
                // add null
                productList.add(null);
                adapter_tablet.notifyItemInserted(productList.size() - 1);
            }
        });
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                // remove null
                productList.remove(productList.size() - 1);
                adapter_tablet.notifyItemRemoved(productList.size());
                page = page + 1;
                getData(page);
                adapter_tablet.notifyDataSetChanged();
                isLoading = false;
            }
        },2000);
    }

    private void getData(int page) {
        compositeDisposable.add(apiProduct.getProduct(page, type)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        productModel -> {
                            if (productModel.isSuccess()){
                                if (adapter_tablet == null){
                                    productList = productModel.getResult();
                                    adapter_tablet = new TabletAdapter(getApplicationContext(), productList);
                                    recyclerView.setAdapter(adapter_tablet);
                                }else {
                                    int position = productList.size() - 1;
                                    int amount_add = productModel.getResult().size();
                                    for (int i = 0; i < amount_add; i++){
                                        productList.add(productModel.getResult().get(i));
                                    }
                                    adapter_tablet.notifyItemRangeInserted(position, amount_add);
                                }

                            }else {
                                ToastPerfect.makeText(getApplicationContext(), ToastPerfect.INFORMATION, "Out of stock!!", ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
                                isLoading = true;
                            }
                        },throwable -> {
                            ToastPerfect.makeText(getApplicationContext(), ToastPerfect.WARNING, "Can't connect to server", ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
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
        toolbar = findViewById(R.id.toolbar_tablet);
        recyclerView = findViewById(R.id.recyclerview_tablet);
        // RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
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