package vn.name.greenwich.appstorephone.Login_Register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import vn.name.greenwich.appstorephone.R;
import vn.name.greenwich.appstorephone.Retrofit.ApiProduct;
import vn.name.greenwich.appstorephone.Retrofit.RetrofitClient;
import vn.name.greenwich.appstorephone.Server.Connect;
import vn.thanguit.toastperfect.ToastPerfect;

public class Register_Activity extends AppCompatActivity {

    EditText username, address, email, pass, re_pass, mobile;
    TextView txt_Login;
    AppCompatButton btn_register;
    ApiProduct apiProduct;
    FirebaseAuth firebaseAuth;
    CompositeDisposable compositeDisposable = new CompositeDisposable();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        initView();
        initControll();
    }

    private void initControll() {
        btn_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Register();
            }
        });
        txt_Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private void Register() {
        String str_username = username.getText().toString().trim(); // Cách chuỗi ở 2 đầu
        String str_address = address.getText().toString().trim();
        String str_email = email.getText().toString().trim();
        String str_pass = pass.getText().toString().trim();
        String str_re_pass = re_pass.getText().toString().trim();
        String str_mobile = mobile.getText().toString().trim();
        // Kiểm tra đăng kí từng thành phần (nếu trống báo lỗi)
        if (TextUtils.isEmpty(str_username)){
            ToastPerfect.makeText(this, ToastPerfect.ERROR, "You have not entered Username!", ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(str_address)) {
            ToastPerfect.makeText(this, ToastPerfect.ERROR, "You have not entered Address!", ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(str_email)) {
            ToastPerfect.makeText(this, ToastPerfect.ERROR, "You have not entered Email!", ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(str_pass)) {
            ToastPerfect.makeText(this, ToastPerfect.ERROR, "You have not entered Password!", ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(str_re_pass)) {
            ToastPerfect.makeText(this, ToastPerfect.ERROR, "You have not entered Re-Password!", ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
        }else if (TextUtils.isEmpty(str_mobile)) {
            ToastPerfect.makeText(this, ToastPerfect.ERROR, "You have not entered Phone Number!", ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
        }else {
            // Kiểm tra thêm pass và re-pass phải giống nhau
            if (str_pass.equals(str_re_pass)){
                // post data
                firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.createUserWithEmailAndPassword(str_email, str_pass)
                        .addOnCompleteListener(Register_Activity.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()){
                                    FirebaseUser user = firebaseAuth.getCurrentUser();
//                                    postData(str_email, str_username, str_address, str_pass, str_mobile, user.getUid());
                                    if (user != null){
                                        postData(str_email, str_username, str_address, str_pass, str_mobile, user.getUid());
                                    }
                                }else {
                                    Toast.makeText(getApplicationContext(), "This email already exists!!", Toast.LENGTH_LONG).show();
                                }
                            }
                        });
//                compositeDisposable.add(apiProduct.register(str_username, str_address, str_email,str_pass,str_mobile)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(
//                        userModel -> {
//                            if (userModel.isSuccess()){
//                                // Khi người dùng đã đki thì sẽ hiện thị bên đăng nhập
//                                Connect.user_current.setEmail(str_email);
//                                Connect.user_current.setPass(str_pass);
//                                // Sau khi đăng kí thành công sẽ chuyển về đăng nhập
//                                Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
//                                startActivity(intent);
//                                finish();
//                            }else {
//                                ToastPerfect.makeText(this, ToastPerfect.INFORMATION,userModel.getMessage(), ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
//                            }
//                        },
//                        throwable -> {
//                            ToastPerfect.makeText(this, ToastPerfect.SUCCESS,throwable.getMessage(), ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
//                        }
//                ));
            }else {
                ToastPerfect.makeText(this, ToastPerfect.WARNING, "Password does not match, please check again!", ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
            }
        }
    }

    private void postData(String str_email, String str_username, String str_address, String str_pass, String str_mobile, String uid){
        // post data
        compositeDisposable.add(apiProduct.register(str_username, str_address, str_email,str_pass,str_mobile, uid)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userModel -> {
                            if (userModel.isSuccess()){
                                // Khi người dùng đã đki thì sẽ hiện thị bên đăng nhập
                                Connect.user_current.setEmail(str_email);
                                Connect.user_current.setPass(str_pass);
                                // Sau khi đăng kí thành công sẽ chuyển về đăng nhập
                                Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                                startActivity(intent);
                                finish();
                            }else {
                                ToastPerfect.makeText(getApplicationContext(), ToastPerfect.INFORMATION,userModel.getMessage(), ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
                            }
                        },
                        throwable -> {
                            ToastPerfect.makeText(getApplicationContext(), ToastPerfect.SUCCESS,throwable.getMessage(), ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
                        }
                ));
    }

    private void initView() {
        username = findViewById(R.id.sign_up_username);
        address = findViewById(R.id.sign_up_address);
        email = findViewById(R.id.sign_up_email);
        pass = findViewById(R.id.sign_up_password);
        re_pass = findViewById(R.id.Re_Password);
        mobile = findViewById(R.id.sign_up_phone);
        btn_register = findViewById(R.id.btn_register);
        txt_Login = findViewById(R.id.txt_login);
        // bắt sự kiện Api
        apiProduct = RetrofitClient.getInstance(Connect.BASE_URL).create(ApiProduct.class);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }
}