package vn.name.greenwich.appstorephone.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import vn.name.greenwich.appstorephone.Activity.Check_out_Activity;
import vn.name.greenwich.appstorephone.Adapter.Cart_Adapter;
import vn.name.greenwich.appstorephone.Class.Cart;
import vn.name.greenwich.appstorephone.Class.EventBus.Event_Total;
import vn.name.greenwich.appstorephone.R;
import vn.name.greenwich.appstorephone.Server.Connect;
import vn.thanguit.toastperfect.ToastPerfect;

public class Fragment_Cart extends AppCompatActivity {

    TextView cart_empty, total; // khai báo giỏ hàng trống
    Toolbar toolbar;
    RecyclerView recyclerView_Cart;
    Button btn_buy;
    Cart_Adapter cartAdapter;
    List<Cart> List_cart;
    long total_product;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        initView();
        initControl();
        init_Total();
    }

    // tính tổng tiền trong giỏ hàng
    private void init_Total() {
        total_product = 0;
        for (int i = 0; i < Connect.array_buy_cart.size(); i++){
            total_product = total_product + (Connect.array_buy_cart.get(i).getPrice() * Connect.array_buy_cart.get(i).getAmount());
        }
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        total.setText(decimalFormat.format(total_product) + "$");
    }


    private void initControl() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        // truyền dữ liệu của bên chi tiết đơn hàng qua giỏ hàng
        recyclerView_Cart.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView_Cart.setLayoutManager(layoutManager);
        // hiển thị khi giỏ hàng bị trống
        if (Connect.array_cart.size() == 0){
            cart_empty.setVisibility(View.VISIBLE);
        }else {
            cartAdapter = new Cart_Adapter(getApplicationContext(), Connect.array_cart);
            recyclerView_Cart.setAdapter(cartAdapter);
        }

        btn_buy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (Connect.array_cart.size() == 0){
//                    cartAdapter = new Cart_Adapter(getApplicationContext(), Connect.array_cart);
//                    recyclerView_Cart.setAdapter(cartAdapter);
                    ToastPerfect.makeText(getApplicationContext(), ToastPerfect.INFORMATION, "There are no products in the cart, please continue shopping", ToastPerfect.CENTER,ToastPerfect.LENGTH_LONG).show();
                }
                else {
                    Intent buy = new Intent(getApplicationContext(), Check_out_Activity.class);
                    buy.putExtra("total", total_product);
                    Connect.array_cart.clear();
                    startActivity(buy);
                }
            }
        });
    }

    private void initView() {
        cart_empty = findViewById(R.id.tv_cart_empty);
        total = findViewById(R.id.txt_total);
        toolbar = findViewById(R.id.toolbar_cart);
        recyclerView_Cart = findViewById(R.id.recyclerview_cart);
        btn_buy = findViewById(R.id.btn_buy);
    }

    @Override
    protected void onStart() {
        super.onStart();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onStop() {
        EventBus.getDefault().unregister(this);
        super.onStop();
    }
    // xét sự kiện lại tổng tiền cart
    @Subscribe(sticky = true, threadMode = ThreadMode.MAIN)
    public void event_Total(Event_Total eventTotal){
        if (eventTotal != null){
            init_Total();
        }
    }
}