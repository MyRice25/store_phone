package vn.name.greenwich.appstorephone.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.text.DecimalFormat;
import java.util.List;

import vn.name.greenwich.appstorephone.Activity.DetailActivity;
import vn.name.greenwich.appstorephone.Class.Product;
import vn.name.greenwich.appstorephone.Interface.ItemClickListener;
import vn.name.greenwich.appstorephone.R;
import vn.name.greenwich.appstorephone.Server.Connect;

public class Product_new_Adapter extends RecyclerView.Adapter<Product_new_Adapter.MyViewHolder> {
    Context context;
    List<Product> array;

    public Product_new_Adapter(Context context, List<Product> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View item = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product_new, parent, false);
        return new MyViewHolder(item);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // Xem tên đt, thêm giá tiền, những chức năng của sản phẩm mới nhất
        Product product = array.get(position);
        holder.name.setText(product.getName());
        DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
        holder.price.setText("Price: " + decimalFormat.format(Double.parseDouble(product.getPrice())) + "$");
        // tìm hình ảnh đưa lên
        if (product.getImage().contains("http")){
            Glide.with(context).load(product.getImage()).into(holder.image);
        }else {
            String image = Connect.BASE_URL + "images/" + product.getImage();
            Glide.with(context).load(image).into(holder.image);
        }

        holder.setItemClickListener(new ItemClickListener() {
            @Override
            public void onClick(View view, int pos, boolean isLongClick) {
                if (!isLongClick){
                    // click vao san pham dien thoai, laptop
                    Intent intent = new Intent(context, DetailActivity.class);
                    intent.putExtra("Detail", product);// dòng chuyển tiếp sản phẩm cho bên DetailActivity
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        // Xét cho recycler cho màn hình chính build cho sản phẩm
        TextView price, name;
        ImageView image;

        private ItemClickListener itemClickListener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.item_name_new);
            price = itemView.findViewById(R.id.item_price_new);
            image = itemView.findViewById(R.id.item_image_new);
            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(), false);
        }
    }
}
