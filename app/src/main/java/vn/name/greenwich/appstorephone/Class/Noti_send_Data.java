package vn.name.greenwich.appstorephone.Class;

import java.util.Map;

public class Noti_send_Data {
    private String to;
    Map<String, String> notification;

    public Noti_send_Data(String to, Map<String, String> notification) {
        this.to = to;
        this.notification = notification;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public Map<String, String> getNotification() {
        return notification;
    }

    public void setNotification(Map<String, String> notification) {
        this.notification = notification;
    }
}
