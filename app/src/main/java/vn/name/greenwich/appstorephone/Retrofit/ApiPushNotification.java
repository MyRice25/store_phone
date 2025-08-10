package vn.name.greenwich.appstorephone.Retrofit;

import io.reactivex.rxjava3.core.Observable;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import vn.name.greenwich.appstorephone.Class.Noti_response_Data;
import vn.name.greenwich.appstorephone.Class.Noti_send_Data;

public interface ApiPushNotification {
    @Headers(
            {
                    "Content-Type: application/json",
                    "Authorization: key=AAAAS7fabKI:APA91bGBHM0Mpu5jmaL3CqcoDiGQsION8MHWmQWbYaDSiJ1ZUbwlanMJUSJ0KRBJ0-9ZwZ5DlRbP_DO9fnvacLIqLkwrITArE56cdsuP8J2PyzVIfLmCQSm7A-BvcalXwwEfBHSnaqIh"
            }
    )
    @POST("fcm/send")
    Observable<Noti_response_Data> sendNotification(@Body Noti_send_Data sendData);
}
