package vn.name.greenwich.appstorephone.Fragment;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import io.paperdb.Paper;
import vn.name.greenwich.appstorephone.Account.Account_setting_Activity;
import vn.name.greenwich.appstorephone.Account.Help_Activity;
import vn.name.greenwich.appstorephone.Account.Purchase_order_Activity;
import vn.name.greenwich.appstorephone.Activity.MainActivity;
import vn.name.greenwich.appstorephone.Category.HeadphoneActivity;
import vn.name.greenwich.appstorephone.Category.LaptopActivity;
import vn.name.greenwich.appstorephone.Category.Location_Activity;
import vn.name.greenwich.appstorephone.Category.PhoneActivity;
import vn.name.greenwich.appstorephone.Category.Sale_code_Activity;
import vn.name.greenwich.appstorephone.Category.TabletActivity;
import vn.name.greenwich.appstorephone.Class.Nav_menu;
import vn.name.greenwich.appstorephone.Login_Register.Login_Activity;
import vn.name.greenwich.appstorephone.R;

public class Fragment_Account extends AppCompatActivity {
    Toolbar toolbar;
    AppCompatButton btn_Purchase_order, btn_wallet, btn_account,btn_help, btn_logout;
    List<Nav_menu> array_menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account);
        initView();
        ActionBar();
        initControl();
    }

    private void initControl() {
        btn_Purchase_order.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent order = new Intent(getApplicationContext(), Purchase_order_Activity.class);
                startActivity(order);
            }
        });
        btn_wallet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent wallet = new Intent(getApplicationContext(), Sale_code_Activity.class);
                startActivity(wallet);
            }
        });
        btn_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent account = new Intent(getApplicationContext(), Account_setting_Activity.class);
                startActivity(account);

            }
        });
        btn_help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent help = new Intent(getApplicationContext(), Help_Activity.class);
                startActivity(help);
            }
        });
        btn_logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // xóa hết key user
                Paper.book().delete("user");
//                array_menu.add(new Nav_menu());
                Intent logout = new Intent(getApplicationContext(), Login_Activity.class);
                startActivity(logout);
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });
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
        toolbar = findViewById(R.id.toolbar_account);
        btn_Purchase_order = findViewById(R.id.btn_Purchase_order);
        btn_wallet = findViewById(R.id.btn_wallet);
        btn_account = findViewById(R.id.btn_account_setting);
        btn_help = findViewById(R.id.btn_help_center);
        btn_logout = findViewById(R.id.btn_logout);
    }
}