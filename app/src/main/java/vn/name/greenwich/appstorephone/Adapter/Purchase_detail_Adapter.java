package vn.name.greenwich.appstorephone.Adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import vn.name.greenwich.appstorephone.Class.Item_details;
import vn.name.greenwich.appstorephone.Class.Order;
import vn.name.greenwich.appstorephone.R;

public class Purchase_detail_Adapter extends RecyclerView.Adapter<Purchase_detail_Adapter.MyViewHolder> {
    Context context;
    List<Order> orderList;

    public Purchase_detail_Adapter(Context context, List<Order> orderList) {
        this.context = context;
        this.orderList = orderList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_detail_purchase, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Order order = orderList.get(position);
        holder.txt_name.setText(order.getName() + "");
        holder.txt_amount.setText("Amount: " + order.getAmount() + "");
        holder.txt_price.setText("Price: " + order.getPrice() + "$");

        Glide.with(context).load(order.getImage()).into(holder.img_purchase);

    }

    @Override
    public int getItemCount() {
        return orderList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{
        ImageView img_purchase;
        TextView txt_name, txt_amount, txt_price;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            img_purchase = itemView.findViewById(R.id.item_img_purchase);
            txt_name = itemView.findViewById(R.id.item_name_purchase);
            txt_amount = itemView.findViewById(R.id.item_amount_purchase);
            txt_price = itemView.findViewById(R.id.item_price_purchase);
        }


    }
}
