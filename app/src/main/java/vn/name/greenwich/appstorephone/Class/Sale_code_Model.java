package vn.name.greenwich.appstorephone.Class;

import java.util.List;

public class Sale_code_Model {
    boolean success;
    String message;
    List<Sale_code> result;

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

    public List<Sale_code> getResult() {
        return result;
    }

    public void setResult(List<Sale_code> result) {
        this.result = result;
    }
}
