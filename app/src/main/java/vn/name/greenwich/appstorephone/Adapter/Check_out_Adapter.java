package vn.name.greenwich.appstorephone.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.List;

import vn.name.greenwich.appstorephone.Class.Cart;
import vn.name.greenwich.appstorephone.Class.EventBus.Event_Total;
import vn.name.greenwich.appstorephone.Interface.IImageClickListenner;
import vn.name.greenwich.appstorephone.R;
import vn.name.greenwich.appstorephone.Server.Connect;

public class Check_out_Adapter extends RecyclerView.Adapter<Check_out_Adapter.MyViewHolder> {
    Context context;
    List<Cart> CartList;

    public Check_out_Adapter(Context context, List<Cart> cartList) {
        this.context = context;
        CartList = cartList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_check_out, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cart cart = CartList.get(position);
        holder.item_name.setText(cart.getName());
        holder.item_amount.setText(cart.getAmount() + "");
        Glide.with(context).load(cart.getImage()).into(holder.item_image);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        long price = CartList.get(position).getAmount() * CartList.get(position).getPrice();
        holder.item_price.setText(decimalFormat.format(price) + "$");
    }

    @Override
    public int getItemCount() {
        return CartList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView item_name, item_price, item_amount;
        ImageView item_image;
        // Tạo trong interface IImageClickLíntenner
        IImageClickListenner listenner;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_name = itemView.findViewById(R.id.item_check_name);
            item_price = itemView.findViewById(R.id.item_check_price);
            item_amount = itemView.findViewById(R.id.item_check_amount);
            item_image = itemView.findViewById(R.id.item_check_image);
        }

        public void setListenner(IImageClickListenner listenner) {
            this.listenner = listenner;
        }

        @Override
        public void onClick(View view) {

        }
    }
}
