package vn.name.greenwich.appstorephone.Activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.AlgorithmParameterGenerator;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;
import vn.momo.momo_partner.AppMoMoLib;
import vn.name.greenwich.appstorephone.Adapter.Cart_Adapter;
import vn.name.greenwich.appstorephone.Adapter.Check_out_Adapter;
import vn.name.greenwich.appstorephone.Class.Cart;
import vn.name.greenwich.appstorephone.Class.CreateOrder;
import vn.name.greenwich.appstorephone.Class.Noti_send_Data;
import vn.name.greenwich.appstorephone.Class.User;
import vn.name.greenwich.appstorephone.R;
import vn.name.greenwich.appstorephone.Retrofit.ApiProduct;
import vn.name.greenwich.appstorephone.Retrofit.ApiPushNotification;
import vn.name.greenwich.appstorephone.Retrofit.RetrofitClient;
import vn.name.greenwich.appstorephone.Retrofit.Retrofit_Notification;
import vn.name.greenwich.appstorephone.Server.Connect;
import vn.thanguit.toastperfect.ToastPerfect;
//import vn.zalopay.sdk.Environment;
//import vn.zalopay.sdk.ZaloPayError;
//import vn.zalopay.sdk.ZaloPaySDK;
//import vn.zalopay.sdk.listeners.PayOrderListener;

public class Check_out_Activity extends AppCompatActivity {

    Toolbar toolbar;
    TextView txt_total, txt_phone, txt_email, txt_username;
    EditText edt_address, edt_message;
    RecyclerView recyclerView_check_out;
    AppCompatButton btn_place_oder, btn_momo, btn_zalopay;
    CheckBox check_momo;
    CompositeDisposable compositeDisposable = new CompositeDisposable();
    ApiProduct apiProduct;
    List<Cart> List_check;
    Check_out_Adapter checkOutAdapter;
    long total;
    int totalItem;
    int id_order;

    // Tính thanh toán momo
    private String amount = "10000";
    private String fee = "0";
    int environment = 0;//developer default
    private String merchantName = "Nam My"; // tên doanh nghiệp
    private String merchantCode = "MOMOV8MG20220629";
    private String merchantNameLabel = "Nam My";
    private String description = "Payment for momo service";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_out);
        AppMoMoLib.getInstance().setEnvironment(AppMoMoLib.ENVIRONMENT.DEVELOPMENT); // AppMoMoLib.ENVIRONMENT.PRODUCTION
        // zalo pay
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        // ZaloPay SDK Init
//        ZaloPaySDK.init(2554, Environment.SANDBOX);
        initView();
        countItem();
        initControl();
    }

    private void countItem() {
        // khai báo lại tổng giá trị đơn hàng được mua
        totalItem = 0;
        for (int i = 0; i < Connect.array_buy_cart.size(); i++){
            totalItem = totalItem + Connect.array_buy_cart.get(i).getAmount();
        }
    }

    //Get token through MoMo app
    private void requestPayment(String id_order) {
        AppMoMoLib.getInstance().setAction(AppMoMoLib.ACTION.PAYMENT);
        AppMoMoLib.getInstance().setActionType(AppMoMoLib.ACTION_TYPE.GET_TOKEN);

        Map<String, Object> eventValue = new HashMap<>();
        //client Required
        eventValue.put("merchantname", merchantName); //Tên đối tác. được đăng ký tại https://business.momo.vn. VD: Google, Apple, Tiki , CGV Cinemas
        eventValue.put("merchantcode", merchantCode); //Mã đối tác, được cung cấp bởi MoMo tại https://business.momo.vn
        eventValue.put("amount", amount); //Kiểu integer
        eventValue.put("orderId", id_order); //uniqueue id cho Bill order, giá trị duy nhất cho mỗi đơn hàng
        eventValue.put("orderLabel", id_order); //gán nhãn

        //client Optional - bill info
        eventValue.put("merchantnamelabel", "Dịch vụ");//gán nhãn
        eventValue.put("fee", "0"); //Kiểu integer
        eventValue.put("description", description); //mô tả đơn hàng - short description

        //client extra data
        eventValue.put("requestId",  merchantCode+"merchant_billId_"+System.currentTimeMillis());
        eventValue.put("partnerCode", merchantCode);
        //Example extra data
        JSONObject objExtraData = new JSONObject();
        try {
            objExtraData.put("site_code", "008");
            objExtraData.put("site_name", "CGV Cresent Mall");
            objExtraData.put("screen_code", 0);
            objExtraData.put("screen_name", "Special");
            objExtraData.put("movie_name", "Kẻ Trộm Mặt Trăng 3");
            objExtraData.put("movie_format", "2D");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        eventValue.put("extraData", objExtraData.toString());
        eventValue.put("extra", "");
        AppMoMoLib.getInstance().requestMoMoCallBack(this, eventValue);

    }
    //Get token callback from MoMo app an submit to server side
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == AppMoMoLib.getInstance().REQUEST_CODE_MOMO && resultCode == -1) {
            if(data != null) {
                if(data.getIntExtra("status", -1) == 0) {
                    //TOKEN IS AVAILABLE
                    Log.d("success", data.getStringExtra("message"));
//                    tvMessage.setText("message: " + "Get token " + data.getStringExtra("message"));
                    String token = data.getStringExtra("data"); //Token response
                    // truyền api cho momo
                    compositeDisposable.add(apiProduct.update_momo(id_order, token)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            insert_model -> {
                                if (insert_model.isSuccess()){
                                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                }
                            },
                            throwable -> {
                                Log.d("test error", throwable.getMessage());
                            }
                    ));
                    String phoneNumber = data.getStringExtra("phonenumber");
                    String env = data.getStringExtra("env");
                    if(env == null){
                        env = "app";
                    }

                    if(token != null && !token.equals("")) {
                        // TODO: send phoneNumber & token to your server side to process payment with MoMo server
                        // IF Momo topup success, continue to process your order
                    } else {
                        Log.d("success", "unsuccess");
//                        tvMessage.setText("message: " + this.getString(R.string.not_receive_info));
                    }
                } else if(data.getIntExtra("status", -1) == 1) {
                    //TOKEN FAIL
                    String message = data.getStringExtra("message") != null?data.getStringExtra("message"):"unsuccess";
                    Log.d("success", "unsuccess");
//                    tvMessage.setText("message: " + message);
                } else if(data.getIntExtra("status", -1) == 2) {
                    //TOKEN FAIL
                    Log.d("success", "unsuccess");
//                    tvMessage.setText("message: " + this.getString(R.string.not_receive_info));
                } else {
                    //TOKEN FAIL
                    Log.d("success", "unsuccess");
//                    tvMessage.setText("message: " + this.getString(R.string.not_receive_info));
                }
            } else {
                Log.d("success", "unsuccess");
//                tvMessage.setText("message: " + this.getString(R.string.not_receive_info));
            }
        } else {
            Log.d("success", "unsuccess");
//            tvMessage.setText("message: " + this.getString(R.string.not_receive_info_err));
        }
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

        // truyền dữ liệu của bên giỏ hàng qua thanh toán
        recyclerView_check_out.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView_check_out.setLayoutManager(layoutManager);
        checkOutAdapter = new Check_out_Adapter(getApplicationContext(), Connect.array_buy_cart);
        recyclerView_check_out.setAdapter(checkOutAdapter);


        // định dạng tổng tiền từng bên giỏ hàng chuyền qua
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        total = getIntent().getLongExtra("total", 0);
        txt_total.setText(decimalFormat.format(total) + "$");
        txt_email.setText(Connect.user_current.getEmail());
        txt_phone.setText(Connect.user_current.getMobile());
        txt_username.setText(Connect.user_current.getUsername());

        btn_place_oder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_address = edt_address.getText().toString().trim();
                String str_message = edt_message.getText().toString().trim();
                if (TextUtils.isEmpty(str_address)){
                    ToastPerfect.makeText(getApplicationContext(), ToastPerfect.ERROR, "You have not entered Address!", ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
                }else {
                    // post data (post toàn bộ giỏ hàng khi chuẩn bị thanh toán) để lưu database
                    Log.d("test", new Gson().toJson(Connect.array_buy_cart)); // kiểm tra sp
                    // post data
                    String str_email = Connect.user_current.getEmail();
                    String str_phone = Connect.user_current.getMobile();
                    int id = Connect.user_current.getId();
                    String str_username = Connect.user_current.getUsername();
                    compositeDisposable.add(apiProduct.Create_Order(id, str_username, str_email, str_address, str_phone, totalItem, String.valueOf(total), new Gson().toJson(Connect.array_buy_cart), str_message)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    insert_model -> {
                                        // gửi thông báo khi đã mua thành công truyền về cho admin
                                        push_Noti_to_User();
                                        ToastPerfect.makeText(getApplicationContext(), ToastPerfect.SUCCESS, "add order success", ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
                                        Connect.array_buy_cart.clear();
                                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                                        startActivity(intent);
                                        finish();
                                    },
                                    throwable -> {
                                        ToastPerfect.makeText(getApplicationContext() ,throwable.getMessage(), ToastPerfect.LENGTH_SHORT).show();
                                        ToastPerfect.makeText(getApplicationContext(), ToastPerfect.SUCCESS, throwable.getMessage(), ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
                                    }
                            ));
                }
            }
        });

        btn_momo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String str_address = edt_address.getText().toString().trim();
                String str_message = edt_message.getText().toString().trim();
                if (TextUtils.isEmpty(str_address)){
                    ToastPerfect.makeText(getApplicationContext(), ToastPerfect.ERROR, "You have not entered Address!", ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
                }else {
                    // post data (post toàn bộ giỏ hàng khi chuẩn bị thanh toán) để lưu database
                    Log.d("test", new Gson().toJson(Connect.array_buy_cart)); // kiểm tra sp
                    // post data
                    String str_email = Connect.user_current.getEmail();
                    String str_phone = Connect.user_current.getMobile();
                    int id = Connect.user_current.getId();
                    String str_username = Connect.user_current.getUsername();
                    compositeDisposable.add(apiProduct.Create_Order(id, str_username, str_email, str_address, str_phone, totalItem, String.valueOf(total), new Gson().toJson(Connect.array_buy_cart), str_message)
                            .subscribeOn(Schedulers.io())
                            .observeOn(AndroidSchedulers.mainThread())
                            .subscribe(
                                    insert_model -> {
                                        // gửi thông báo khi đã mua thành công truyền về cho admin
                                        push_Noti_to_User();
                                        ToastPerfect.makeText(getApplicationContext(), ToastPerfect.SUCCESS, "add order success", ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
                                        Connect.array_buy_cart.clear();
                                        // thanh toán qua momo
                                        id_order = Integer.parseInt(insert_model.getId_order());
                                        requestPayment(insert_model.getId_order());
//                                        finish();
                                    },
                                    throwable -> {
                                        ToastPerfect.makeText(getApplicationContext() ,throwable.getMessage(), ToastPerfect.LENGTH_SHORT).show();
                                        ToastPerfect.makeText(getApplicationContext(), ToastPerfect.SUCCESS, throwable.getMessage(), ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
                                    }
                            ));
                }
            }
        });

//        btn_zalopay.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                String str_address = edt_address.getText().toString().trim();
//                String str_message = edt_message.getText().toString().trim();
//                if (TextUtils.isEmpty(str_address)){
//                    ToastPerfect.makeText(getApplicationContext(), ToastPerfect.ERROR, "You have not entered Address!", ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
//                }else {
//                    // post data (post toàn bộ giỏ hàng khi chuẩn bị thanh toán) để lưu database
//                    Log.d("test", new Gson().toJson(Connect.array_buy_cart)); // kiểm tra sp
//                    // post data
//                    String str_email = Connect.user_current.getEmail();
//                    String str_phone = Connect.user_current.getMobile();
//                    int id = Connect.user_current.getId();
//                    String str_username = Connect.user_current.getUsername();
//                    compositeDisposable.add(apiProduct.Create_Order(id, str_username, str_email, str_address, str_phone, totalItem, String.valueOf(total), new Gson().toJson(Connect.array_buy_cart), str_message)
//                            .subscribeOn(Schedulers.io())
//                            .observeOn(AndroidSchedulers.mainThread())
//                            .subscribe(
//                                    insert_model -> {
//                                        // gửi thông báo khi đã mua thành công truyền về cho admin
//                                        push_Noti_to_User();
//                                        ToastPerfect.makeText(getApplicationContext(), ToastPerfect.SUCCESS, "add order success", ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
//                                        Connect.array_buy_cart.clear();
//                                        // thanh toán qua momo
//                                        id_order = Integer.parseInt(insert_model.getId_order());
//                                        requestZaloPay();
////                                        finish();
//                                    },
//                                    throwable -> {
//                                        ToastPerfect.makeText(getApplicationContext() ,throwable.getMessage(), ToastPerfect.LENGTH_SHORT).show();
//                                        ToastPerfect.makeText(getApplicationContext(), ToastPerfect.SUCCESS, throwable.getMessage(), ToastPerfect.BOTTOM, ToastPerfect.LENGTH_SHORT).show();
//                                    }
//                            ));
//                }
//            }
//        });
    }

    private void requestZaloPay() {
        CreateOrder orderApi = new CreateOrder();

        try {
            JSONObject data = orderApi.createOrder("100000");
////            Log.d("Amount", txtAmount.getText().toString());
////            lblZpTransToken.setVisibility(View.VISIBLE);
            String code = data.getString("return_code");
            Log.d("test", code);
////            Toast.makeText(getApplicationContext(), "return_code: " + code, Toast.LENGTH_LONG).show();
            if (code.equals("1")) {
                String token = data.getString("zp_trans_token");
                Log.d("test", token);

//                ZaloPaySDK.getInstance().payOrder(Check_out_Activity.this, token, "demozpdk://app", new PayOrderListener() {
//                    @Override
//                    public void onPaymentSucceeded(String s, String s1, String s2) {
//                        // truyền api cho zalo pay
//                        compositeDisposable.add(apiProduct.update_momo(id_order, token)
//                                .subscribeOn(Schedulers.io())
//                                .observeOn(AndroidSchedulers.mainThread())
//                                .subscribe(
//                                        insert_model -> {
//                                            if (insert_model.isSuccess()){
//                                                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
//                                                startActivity(intent);
//                                                finish();
//                                            }
//                                        },
//                                        throwable -> {
//                                            Log.d("test error", throwable.getMessage());
//                                        }
//                                ));
//                    }
//
//                    @Override
//                    public void onPaymentCanceled(String s, String s1) {
//
//                    }
//
//                    @Override
//                    public void onPaymentError(ZaloPayError zaloPayError, String s, String s1) {
//
//                    }
//                });
//                lblZpTransToken.setText("zptranstoken");
//                txtToken.setText(data.getString("zp_trans_token"));
//                IsDone();
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void push_Noti_to_User() {
        // get token
        compositeDisposable.add(apiProduct.gettoken(1)
        .subscribeOn(Schedulers.io())
        .observeOn(AndroidSchedulers.mainThread())
        .subscribe(
                userModel -> {
                    if (userModel.isSuccess()){
                        // xét cho từng admin nếu trên 1
                        for (int i = 0; i < userModel.getResult().size(); i++){
                            Map<String, String> data = new HashMap<>();
                            data.put("title", "notification");
                            data.put("body", "You have a new order!!");
                            Noti_send_Data notiSendData = new Noti_send_Data(userModel.getResult().get(i).getToken(), data);
                            ApiPushNotification apiPushNotification = Retrofit_Notification.getInstance().create(ApiPushNotification.class);
                            compositeDisposable.add(apiPushNotification.sendNotification(notiSendData)
                                    .subscribeOn(Schedulers.io())
                                    .observeOn(AndroidSchedulers.mainThread())
                                    .subscribe(
                                            noti_response_data -> {

                                            },
                                            throwable -> {
                                                Log.d("test error", throwable.getMessage());
                                            }
                                    ));
                        }
//                        String token = "fpJMNpboQB206miVsw1hau:APA91bGs9us40XsDTyj945TK51Yn8tb5-zC7Mb4xq4uP11ECg7DWfKLeia9bxHBbpxZHWIbEE1z4X22H1C6kaBe98zvVRJRK0yHQfeKk-xtsv65wr89A4ULdIJm-j2qHI9CoVzaiAvao";

                    }
                },
                throwable -> {
                    Log.d("test erorr", throwable.getMessage());
                }
        ));

    }

    private void initView() {
        apiProduct = RetrofitClient.getInstance(Connect.BASE_URL).create(ApiProduct.class);
        toolbar = findViewById(R.id.toolbar_checkout);
        txt_total = findViewById(R.id.txt_total_check_out);
        txt_phone = findViewById(R.id.txt_phone_check);
        txt_email = findViewById(R.id.txt_email_check);
        txt_username = findViewById(R.id.txt_username_check);
        edt_address = findViewById(R.id.edt_address);
        edt_message = findViewById(R.id.edt_message);
        btn_place_oder = findViewById(R.id.btn_place_order);
        recyclerView_check_out = findViewById(R.id.recyclerview_check_out);
//        check_momo = findViewById(R.id.check_momo);
        btn_momo = findViewById(R.id.btn_momo);
//        btn_zalopay = findViewById(R.id.btn_zalopay);
    }

    @Override
    protected void onDestroy() {
        compositeDisposable.clear();
        super.onDestroy();
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
//        ZaloPaySDK.getInstance().onResult(intent);
    }
}