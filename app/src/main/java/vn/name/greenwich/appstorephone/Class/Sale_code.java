package vn.name.greenwich.appstorephone.Class;

import androidx.appcompat.widget.AppCompatButton;

import java.io.Serializable;

public class Sale_code implements Serializable {
    int id;
    String name_sale;
    String image_sale;
    String sale;
    String out_of_date;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName_sale() {
        return name_sale;
    }

    public void setName_sale(String name_sale) {
        this.name_sale = name_sale;
    }

    public String getImage_sale() {
        return image_sale;
    }

    public void setImage_sale(String image_sale) {
        this.image_sale = image_sale;
    }

    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    public String getOut_of_date() {
        return out_of_date;
    }

    public void setOut_of_date(String out_of_date) {
        this.out_of_date = out_of_date;
    }
}
