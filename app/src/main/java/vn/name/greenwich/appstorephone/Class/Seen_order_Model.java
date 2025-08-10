package vn.name.greenwich.appstorephone.Class;

import java.util.List;

public class Seen_order_Model {
    boolean success;
    String message;
    List<Seen_order> result;

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

    public List<Seen_order> getResult() {
        return result;
    }

    public void setResult(List<Seen_order> result) {
        this.result = result;
    }
}
