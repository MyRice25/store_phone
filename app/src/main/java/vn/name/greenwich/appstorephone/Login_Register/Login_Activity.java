package vn.name.greenwich.appstorephone.Login_Register;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import io.paperdb.Paper;
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import vn.name.greenwich.appstorephone.Activity.MainActivity;
import vn.name.greenwich.appstorephone.Class.SaveInfo;
import vn.name.greenwich.appstorephone.R;
import vn.name.greenwich.appstorephone.Retrofit.ApiProduct;
import vn.name.greenwich.appstorephone.Retrofit.RetrofitClient;
import vn.name.greenwich.appstorephone.Server.Connect;
import vn.thanguit.toastperfect.ToastPerfect;

public class Login_Activity extends AppCompatActivity {

    TextView txt_register, txt_forgot_pass;
    EditText email, pass;
    AppCompatButton btn_login;
    ApiProduct apiProduct;
    FirebaseAuth firebaseAuth;
    FirebaseUser user;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    boolean isLogin = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        initView();
        initControl();
    }

    private void initControl() {
        txt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Register_Activity.class);
                startActivity(intent);
            }
        });

        txt_forgot_pass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), Forgot_Pass_Activity.class);
                startActivity(intent);
            }
        });

        btn_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_email = email.getText().toString().trim();
                String str_pass = pass.getText().toString().trim();
                // Kiểm tra nếu trống báo lỗi
                if (TextUtils.isEmpty(str_email)){
                    ToastPerfect.makeText(getApplicationContext(), ToastPerfect.ERROR, "You have not entered Email!", ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
                }else if (TextUtils.isEmpty(str_pass)) {
                    ToastPerfect.makeText(getApplicationContext(), ToastPerfect.ERROR, "You have not entered Password!", ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
                }else {
                    // save lưu giá trị (phần đăng nhập) khi người dùng đã thoát ứng dụng
                    Paper.book().write("email", str_email);
                    Paper.book().write("password", str_pass);
//                    login(str_email, str_pass);
                    login(str_email, str_pass);
                    if(isLogin) {
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
//                        firebaseAuth.signInWithEmailAndPassword(str_email, str_pass)
//                                .addOnCompleteListener(Login_Activity.this, new OnCompleteListener<AuthResult>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<AuthResult> task) {
//                                        if (task.isSuccessful()){
//                                            //login(str_email, str_pass);
//                                            Paper.book().write("editUser_email", email); // Dung cho Login...
//                                            Paper.book().write("editUser_pass", str_pass);
//                                        }
//                                    }
//                                });
                    }
                    if (user != null){
                        // user đã đăng kí firebase

                    }else {
                        // user đã sign out
//                        firebaseAuth.signInWithEmailAndPassword(str_email, str_pass)
//                                .addOnCompleteListener(Login_Activity.this, new OnCompleteListener<AuthResult>() {
//                                    @Override
//                                    public void onComplete(@NonNull Task<AuthResult> task) {
//                                        if (task.isSuccessful()){
//                                            login(str_email, str_pass);
//                                            Paper.book().write("editUser_email", email); // Dung cho Login...
//                                            Paper.book().write("editUser_pass", str_pass);
//                                        }
//                                    }
//                                });
                    }
                }
            }
        });
    }

    private void initView() {
        // Khởi tạo Paper (bên Gradle có ghi chú) (lưu trữ user để khi user thoát app khi đăng nhập lại thì vào trang main)
        Paper.init(this);
        // Khởi tạo API
        apiProduct = RetrofitClient.getInstance(Connect.BASE_URL).create(ApiProduct.class);
        txt_register = findViewById(R.id.txt_register);
        txt_forgot_pass = findViewById(R.id.txt_forget_pass);
        email = findViewById(R.id.email);
        pass = findViewById(R.id.Password);
        btn_login = findViewById(R.id.btn_login);
        // firebase
        firebaseAuth = FirebaseAuth.getInstance();
        user = firebaseAuth.getCurrentUser();
        // Read (tạo đăng nhập) data (lưu email và pass khi thoát hẳn ứng dụng)
        if (Paper.book().read("email") != null && Paper.book().read("password") != null){
            email.setText(Paper.book().read("email"));
            pass.setText(Paper.book().read("password"));
            // ghi nhớ phần đăng nhập để vào thẳng trang chính
            if (Paper.book().read("islogin") != null){
                boolean flag = Paper.book().read("islogin");
                if (flag){
                    new Handler().postDelayed(new Runnable() {
                        @Override
                        public void run() {
//                            login(Paper.book().read("email"), Paper.book().read("password"));
                        }
                    },500);
                }
            }
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        String editUser_email = Paper.book().read("editUser_email", null); // Lay tu Edit Account
        String editUser_pass = Paper.book().read("editUser_pass", null); // Lay tu Edit Account
        if (editUser_email != null && editUser_pass != null) {
            email.setText(editUser_email);
            pass.setText(editUser_pass);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // nếu đăng kí thành công thì email vs pass không cần phải ghi lại bên đăng nhập
        if (Connect.user_current.getEmail() != null && Connect.user_current.getPass() != null) {
            email.setText(Connect.user_current.getEmail());
            pass.setText(Connect.user_current.getPass());
        }
//        else if (!SaveInfo.MY_EMAIL.equals("")){
//            email.setText(SaveInfo.MY_EMAIL);
//        }
    }

    private void login(String email, String pass) {
        compositeDisposable.add(apiProduct.login(email, pass)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        userModel -> {
                            if (userModel.isSuccess()){
                                // ghi nhớ đăng nhập người dùng khi thoát ra người dùng đăng nhập sẽ vào thẳng luôn trang chính
//                                isLogin = true;
                                Paper.book().write("islogin", isLogin);
                                Connect.user_current = userModel.getResult().get(0); // câu lệnh gắn trên user php (backend) // do chuyền phần tử list nên danh sách chuyền vào lấy từ get 0
                                // lưu lại thông tin người dùng sau khi đã đăng nhập
                                // Khi khi call api thành công lưu thông tin user xuông Paper (Coi lại Login) gọi lại dòng này
                                Paper.book().write("user", userModel.getResult().get(0));
                                firebaseAuth.signInWithEmailAndPassword(email, pass)
                                        .addOnCompleteListener(Login_Activity.this, new OnCompleteListener<AuthResult>() {
                                            @Override
                                            public void onComplete(@NonNull Task<AuthResult> task) {
                                                if (task.isSuccessful()){
                                                    login(email, pass);
                                                    Paper.book().write("editUser_email", email); // Dung cho Login...
                                                    Paper.book().write("editUser_pass", pass);
                                                }
                                            }
                                        });
                                //Luu username,pass vo firebase
                                //...
                                //Neu luu thanh cong
                                isLogin = true;
//                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                startActivity(intent);
//                                finish();
                            }else {
                                ToastPerfect.makeText(getApplicationContext(), ToastPerfect.ERROR, "You have entered wrong Email or Password, please check again!!", ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
                            }
                        },
                        // dòng thông báo lỗi khi ko kết nối đc
                        throwable -> {
                            ToastPerfect.makeText(getApplicationContext(), ToastPerfect.WARNING, throwable.getMessage(), ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
                        }
                ));
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();

    }
}