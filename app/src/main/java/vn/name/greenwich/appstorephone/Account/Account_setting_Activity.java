package vn.name.greenwich.appstorephone.Account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import vn.name.greenwich.appstorephone.Adapter.Edit_user_Adapter;
import vn.name.greenwich.appstorephone.Class.EventBus.Edit_user_Event;
import vn.name.greenwich.appstorephone.Class.SaveInfo;
import vn.name.greenwich.appstorephone.Class.User;
import vn.name.greenwich.appstorephone.Fragment.Fragment_Account;
import vn.name.greenwich.appstorephone.R;
import vn.name.greenwich.appstorephone.Retrofit.ApiProduct;
import vn.name.greenwich.appstorephone.Retrofit.RetrofitClient;
import vn.name.greenwich.appstorephone.Server.Connect;
import vn.thanguit.toastperfect.ToastPerfect;

public class Account_setting_Activity extends AppCompatActivity {
    
    TextView cancel, email, address, username, mobile;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiProduct apiProduct;
    ImageView edit;
    List<User> userList;
    RecyclerView recyclerView;
    Edit_user_Adapter user_adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_setting);
        initView();
        initControl();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("AccountSetting", "MY_ADDRESS: " + SaveInfo.MY_ADDRESS);
        if (!SaveInfo.MY_ADDRESS.equals("") && !SaveInfo.MY_EMAIL.equals("") && !SaveInfo.MY_USERNAME.equals("")&& !SaveInfo.MY_MOBILE.equals("")) {
            address.setText("Address: " + SaveInfo.MY_ADDRESS);
            email.setText("Email: " + SaveInfo.MY_EMAIL);
            username.setText("Username: " + SaveInfo.MY_USERNAME);
            mobile.setText("Mobile: " + SaveInfo.MY_MOBILE);
        }
    }

    private void initData() {
        email.setText("Email: "+ Connect.user_current.getEmail());
        address.setText("Address: "+ Connect.user_current.getAddress());
        username.setText("Username: "+ Connect.user_current.getUsername());
        mobile.setText("Mobile: "+ Connect.user_current.getMobile());
        String str_email = Connect.user_current.getEmail();
        String str_phone = Connect.user_current.getMobile();
        String str_username = Connect.user_current.getUsername();
        String str_address = Connect.user_current.getAddress();
        compositeDisposable.add(apiProduct.Account(str_username, str_email, str_address, str_phone)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                userModel -> {
                    if (userModel.isSuccess()){
                        apiProduct.edit_user(str_email, str_address, str_username, str_phone, Connect.user_current.getId())
                                .subscribeOn(Schedulers.io())
                                .observeOn(AndroidSchedulers.mainThread())
                                .subscribe(
                                );
                    }else {

                    }

                },
                throwable -> {
                    ToastPerfect.makeText(getApplicationContext() ,throwable.getMessage(), ToastPerfect.LENGTH_SHORT).show();
                }
        ));
    }

    private void initControl() {
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent edit = new Intent(getApplicationContext(), Edit_account_Activity.class);
                startActivity(edit);
//                EventBus.getDefault().postSticky(new Edit_user_Event((User) userList));
            }
        });
    }

    private void initView() {
        apiProduct = RetrofitClient.getInstance(Connect.BASE_URL).create(ApiProduct.class);
        cancel = findViewById(R.id.account_cancel);
        email = findViewById(R.id.account_email);
        address = findViewById(R.id.account_address);
        username = findViewById(R.id.account_username);
        mobile = findViewById(R.id.account_mobile);
        recyclerView = findViewById(R.id.recyclerview_account);
        edit = findViewById(R.id.img_edit);
        userList = new ArrayList<>();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}