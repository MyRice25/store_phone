package vn.name.greenwich.appstorephone.Retrofit;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import vn.name.greenwich.appstorephone.Class.Insert_Model;
import vn.name.greenwich.appstorephone.Class.Nav_menu_Model;
import vn.name.greenwich.appstorephone.Class.ProductModel;
import vn.name.greenwich.appstorephone.Class.Sale_code_Model;
import vn.name.greenwich.appstorephone.Class.Seen_order_Model;
import vn.name.greenwich.appstorephone.Class.TypeProductModel;
import vn.name.greenwich.appstorephone.Class.UserModel;

public interface ApiProduct {
    // GET DATA
    @GET("menu_nav.php")
    Observable<Nav_menu_Model> get_menu_nav();

    @GET("getproduct.php")
    Observable<ProductModel> getTypeNew();

    @GET("sale_code.php")
    Observable<Sale_code_Model> getSale();

    // POST DATA
    @POST("detail.php")
    @FormUrlEncoded
    Observable<ProductModel> getProduct(
            @Field("page") int page,
            @Field("type") int type
    );

    @POST("register.php")
    @FormUrlEncoded
    Observable<UserModel> register(
            @Field("username") String username,
            @Field("address") String address,
            @Field("email") String email,
            @Field("password") String password,
            @Field("mobile") String mobile,
            @Field("uid") String uid
    );

    @POST("update_token.php")
    @FormUrlEncoded
    Observable<Insert_Model> update_token(
            @Field("id") int id,
            @Field("token") String token
    );

    @POST("pay_e-wallet.php")
    @FormUrlEncoded
    Observable<Insert_Model> update_momo(
            @Field("id") int id,
            @Field("token") String token
    );

    @POST("login.php")
    @FormUrlEncoded
    Observable<UserModel> login(
            @Field("email") String email,
            @Field("password") String password
    );

    @POST("reset.php")
    @FormUrlEncoded
    Observable<UserModel> reset_pass(
            @Field("email") String email
    );

    @POST("order.php")
    @FormUrlEncoded
    Observable<Insert_Model> Create_Order(
            @Field("iduser") int id,
            @Field("username") String username,
            @Field("email") String email,
            @Field("address") String address,
            @Field("phone") String phone,
            @Field("amount") int amount,
            @Field("total") String total,
            @Field("details") String details,
            @Field("message") String message
    );

    @POST("view_order.php")
    @FormUrlEncoded
    Observable<Seen_order_Model> viewOrder(
            @Field("iduser") int id
    );

    @POST("search.php")
    @FormUrlEncoded
    Observable<ProductModel> search(
            @Field("search") String search
    );

    @POST("get_token.php")
    @FormUrlEncoded
    Observable<UserModel> gettoken(
            @Field("status") int status
    );

    @POST("order.php")
    @FormUrlEncoded
    Observable<UserModel> Account(
            @Field("username") String username,
            @Field("email") String email,
            @Field("address") String address,
            @Field("phone") String phone
    );

    @POST("edit_user.php")
    @FormUrlEncoded
    Observable<UserModel> edit_user(
            @Field("email") String email,
            @Field("address") String address,
            @Field("username") String username,
            @Field("mobile") String mobile,
            @Field("id") int id
    );
}
