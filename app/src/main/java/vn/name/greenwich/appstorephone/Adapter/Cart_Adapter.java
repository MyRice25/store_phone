package vn.name.greenwich.appstorephone.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.text.DecimalFormat;
import java.util.List;

import javax.microedition.khronos.opengles.GL;

import vn.name.greenwich.appstorephone.Class.Cart;
import vn.name.greenwich.appstorephone.Class.EventBus.Event_Total;
import vn.name.greenwich.appstorephone.Interface.IImageClickListenner;
import vn.name.greenwich.appstorephone.R;
import vn.name.greenwich.appstorephone.Server.Connect;

public class Cart_Adapter extends RecyclerView.Adapter<Cart_Adapter.MyViewHolder> {
    Context context;
    List<Cart> List_cart;

    public Cart_Adapter(Context context, List<Cart> list_cart) {
        this.context = context;
        List_cart = list_cart;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_cart, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Cart cart = List_cart.get(position);
        holder.item_cart_name.setText(cart.getName());
        holder.item_cart_amount.setText(cart.getAmount() + "");
        Glide.with(context).load(cart.getImage()).into(holder.item_cart_image);
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        long price = List_cart.get(position).getAmount() * List_cart.get(position).getPrice();
        holder.item_cart_price.setText(decimalFormat.format(price) + "$");
        // phương thức chọn sp bằng checkbox (lấy từng sp của giỏ hàng)
        holder.checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b){
                    Connect.array_buy_cart.add(cart);
                    // Bắn sự kiện ngược lại qua Fragment_Cart
                    // xét sự kiện để chuyền giữa act và adapter cart (tính tổng giỏ hàng)
                    EventBus.getDefault().postSticky(new Event_Total());
                }else {
                    for (int i = 0; i < Connect.array_buy_cart.size(); i++){
                        if (Connect.array_buy_cart.get(i).getId() == cart.getId()){
                            Connect.array_buy_cart.remove(i);
                            EventBus.getDefault().postSticky(new Event_Total());
                        }
                    }
                }
            }
        });

        // phương thức gọi hàm để mảng sp nhảy qua đọc dữ liệu
        holder.setListenner(new IImageClickListenner() {
            @Override
            public void onImageClick(View view, int pos, int value) {
                Log.d("TAG", "Test Amount: " + pos + "..." + value);
                if (value == 1){
                    if (List_cart.get(pos).getAmount() > 1){
                        int amount_new = List_cart.get(pos).getAmount() - 1;
                        List_cart.get(pos).setAmount(amount_new);
                        // nếu user nhấn thêm sản phẩm thì sẽ xét lại giá
                        // Hiển thị lại số lượng và giá trong giỏ hàng (nếu bạn muốn đổi giá trị sp)
                        holder.item_cart_amount.setText(List_cart.get(pos).getAmount() + "");
                        long price = List_cart.get(pos).getAmount() * List_cart.get(pos).getPrice();
                        holder.item_cart_price.setText(decimalFormat.format(price) + "$");
                        // xét sự kiện để chuyền giữa act và adapter cart (tính tổng giỏ hàng)
                        EventBus.getDefault().postSticky(new Event_Total());
                    }else if (List_cart.get(pos).getAmount() == 1){
                        // Thông báo khi người dùng muốn xóa sp trong giỏ hàng
                        AlertDialog.Builder builder = new AlertDialog.Builder(view.getRootView().getContext());
                        builder.setTitle("Notification");
                        builder.setMessage("Are you sure you want to quit this product?");
                        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                // nếu user bấm đồng ý sẽ tiến hành xóa sp
                                Connect.array_cart.remove(pos);
                                notifyDataSetChanged();
                                // xét sự kiện để chuyền giữa act và adapter cart (tính tổng giỏ hàng)
                                EventBus.getDefault().postSticky(new Event_Total());
                            }
                        });
                        builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                dialogInterface.dismiss();
                            }
                        });
                        builder.show();
                    }
                } else if (value == 2) {
                    if (List_cart.get(pos).getAmount() < 100){
                        int amount_new = List_cart.get(pos).getAmount() + 1;
                        List_cart.get(pos).setAmount(amount_new);
                    }
                    // Hiển thị lại số lượng và giá trong giỏ hàng (nếu bạn muốn đổi giá trị sp)
                    holder.item_cart_amount.setText(List_cart.get(pos).getAmount() + "");
                    long price = List_cart.get(pos).getAmount() * List_cart.get(pos).getPrice();
                    holder.item_cart_price.setText(decimalFormat.format(price) + "$");
                    // xét sự kiện để chuyền giữa act và adapter cart (tính tổng giỏ hàng)
                    EventBus.getDefault().postSticky(new Event_Total());
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return List_cart.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView item_cart_image, item_remove, item_add;
        TextView item_cart_name, item_cart_price, item_cart_amount;
        CheckBox checkBox;
        // truyền interface
        IImageClickListenner listenner;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            item_cart_image = itemView.findViewById(R.id.item_cart_image);
            item_cart_name = itemView.findViewById(R.id.item_cart_name);
            item_cart_price = itemView.findViewById(R.id.item_cart_price);
            item_cart_amount = itemView.findViewById(R.id.item_cart_amount);
            checkBox = itemView.findViewById(R.id.item_cart_check);
            // cộng trừ thêm xóa sản phẩm
            item_add = itemView.findViewById(R.id.item_cart_add);
            item_remove = itemView.findViewById(R.id.item_cart_remove);
            // event click
            item_add.setOnClickListener(this);
            item_remove.setOnClickListener(this);

        }

        public void setListenner(IImageClickListenner listenner) {
            this.listenner = listenner;
        }


        @Override
        public void onClick(View view) {
            if (view == item_remove){
                listenner.onImageClick(view, getAdapterPosition(), 1);
                // trừ 1
            }else if (view == item_add){
                // cộng 2
                listenner.onImageClick(view, getAdapterPosition(), 2);
            }
        }
    }
}
