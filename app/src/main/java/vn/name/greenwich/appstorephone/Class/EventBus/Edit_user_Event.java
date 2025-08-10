package vn.name.greenwich.appstorephone.Class.EventBus;

import vn.name.greenwich.appstorephone.Class.User;

public class Edit_user_Event {
    User user;

    public Edit_user_Event(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
