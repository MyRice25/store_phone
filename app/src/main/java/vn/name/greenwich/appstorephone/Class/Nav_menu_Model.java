package vn.name.greenwich.appstorephone.Class;

import java.util.List;

public class Nav_menu_Model {
    boolean success;
    String message;
    List<Nav_menu> result;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Nav_menu> getResult() {
        return result;
    }

    public void setResult(List<Nav_menu> result) {
        this.result = result;
    }
}
