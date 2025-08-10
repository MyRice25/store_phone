package vn.name.greenwich.appstorephone.Server;

import java.util.ArrayList;
import java.util.List;

import vn.name.greenwich.appstorephone.Class.Cart;
import vn.name.greenwich.appstorephone.Class.Item_details;
import vn.name.greenwich.appstorephone.Class.Product;
import vn.name.greenwich.appstorephone.Class.User;

public class Connect {
    public static final String BASE_URL = "http://192.168.10.5/appstore/";
//    public static final String BASE_URL = "http://172.16.10.241/appstore/";

    public static List<Cart> array_cart;
    public static List<Cart> array_buy_cart = new ArrayList<>();
    public static User user_current = new User(); // lưu thông tin khi đăng kí
    public static Product product_current = new Product();

    public static String ID_RECEIVED; // id của người nhận

    public static final String ID_SEND = "idsend"; // gửi trong chatting
    public static final String ID_TAKEN = "idtaken"; // nhận trong chatting
    public static final String MESSAGE = "message";
    public static final String DATA_TIME = "datatime";
    public static final String PATH_CHAT = "chat";

}
