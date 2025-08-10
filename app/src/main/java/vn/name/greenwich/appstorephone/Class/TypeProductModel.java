package vn.name.greenwich.appstorephone.Class;

import java.util.List;

public class TypeProductModel {
    boolean success;
    String message;
    List<TypeProduct> result;

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

    public List<TypeProduct> getResult() {
        return result;
    }

    public void setResult(List<TypeProduct> result) {
        this.result = result;
    }
}
