package vn.name.greenwich.appstorephone.Account;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import vn.name.greenwich.appstorephone.Class.EventBus.Edit_user_Event;
import vn.name.greenwich.appstorephone.Class.SaveInfo;
import vn.name.greenwich.appstorephone.Class.User;
import vn.name.greenwich.appstorephone.R;
import vn.name.greenwich.appstorephone.Retrofit.ApiProduct;
import vn.name.greenwich.appstorephone.Retrofit.RetrofitClient;
import vn.name.greenwich.appstorephone.Server.Connect;
import vn.name.greenwich.appstorephone.databinding.ActivityEditAccountBinding;
import vn.thanguit.toastperfect.ToastPerfect;

public class Edit_account_Activity extends AppCompatActivity {
    Toolbar toolbar;
    ActivityEditAccountBinding binding;
    ApiProduct apiProduct;
    List<User> userList;
    User edit_user;

    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityEditAccountBinding.inflate(getLayoutInflater());
        apiProduct = RetrofitClient.getInstance(Connect.BASE_URL).create(ApiProduct.class);
        setContentView(binding.getRoot());
        initView();
        initData();
        binding.editEmail.setText(Connect.user_current.getEmail());
        binding.editAddress.setText(Connect.user_current.getAddress());
        binding.editUsername.setText(Connect.user_current.getUsername());
        binding.editMobile.setText(Connect.user_current.getMobile());

    }

    private void initData() {
        binding.btnEditUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                try {
//                    Edit_user();
//                }catch (Exception e){
//                    int i = 0;
//                }
                Edit_user();
            }
        });
    }

    private void Edit_user() {
        String edt_email = binding.editEmail.getText().toString().trim();
        String edt_address = binding.editAddress.getText().toString().trim();
        String edt_username = binding.editUsername.getText().toString().trim();
        String edt_mobile = binding.editMobile.getText().toString().trim();
        // xét điều kiện add đầy đủ thông tin
        if (TextUtils.isEmpty(edt_email) || TextUtils.isEmpty(edt_address) || TextUtils.isEmpty(edt_username) || TextUtils.isEmpty(edt_mobile)){
            ToastPerfect.makeText(getApplicationContext(), ToastPerfect.INFORMATION, "Please enter full information !", ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
        } else {
            compositeDisposable.add(apiProduct.edit_user(edt_email, edt_address, edt_username, edt_mobile, Connect.user_current.getId())
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            userModel -> {
                                if (userModel.isSuccess()){

                                    // call api get thông tin User mới về lại
                                    // Khi khi call api thành công lưu thông tin user xuông Paper (Coi lại Login)
                                    // set lại connect.user_current = về lại user mới
                                    /*Intent intent = new Intent(getApplicationContext(), Account_setting_Activity.class);
                                    startActivity(intent);*/
                                    SaveInfo.MY_ADDRESS = edt_address;
                                    SaveInfo.MY_EMAIL = edt_email;
                                    SaveInfo.MY_MOBILE = edt_mobile;
                                    SaveInfo.MY_USERNAME = edt_username;

                                    Paper.book().write("editUser_email", edt_email); // Dung cho Login...
                                    finish();
                                }else {

                                }
                            },
                            throwable -> {

                            }
                    ));
        }
    }

    private void initView() {
        toolbar = findViewById(R.id.toolbar_edit_account);
        userList = new ArrayList<>();
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}