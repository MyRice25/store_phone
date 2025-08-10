package vn.name.greenwich.appstorephone.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
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

public class LaptopAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Product> array;
    private  static final int VIEW_TYPE_DATA = 0;
    private static final int VIEW_TYPE_LOADING = 1;

    public LaptopAdapter(Context context, List<Product> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == VIEW_TYPE_DATA){
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phone, parent, false);
            return new MyViewHolder(view);
        }else {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_loading, parent, false);
            return  new LoadingViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof LaptopAdapter.MyViewHolder){
            LaptopAdapter.MyViewHolder myViewHolder = (LaptopAdapter.MyViewHolder) holder;
            Product product = array.get(position);
            myViewHolder.name.setText(product.getName().trim());
            DecimalFormat decimalFormat = new DecimalFormat("###,###,###");
            myViewHolder.price.setText("Price: " + decimalFormat.format(Double.parseDouble(product.getPrice())) + "$");
            myViewHolder.desciption.setText(product.getDescription());
//            myViewHolder.id.setText(product.getId() + "");
            Glide.with(context).load(product.getImage()).into(myViewHolder.image);

            myViewHolder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int pos, boolean isLongClick) {
                    if (!isLongClick){
                        // click vao san pham dien thoai, laptop
                        Intent intent = new Intent(context, DetailActivity.class);
                        intent.putExtra("Detail", product); // dòng chuyển tiếp sản phẩm cho bên DetailActivity
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                    }
                }
            });

        }else{
            // loading thêm để xem thêm  các sản phẩm phone, laptop, tablet
            LoadingViewHolder loadingViewHolder = (LoadingViewHolder) holder;
            loadingViewHolder.progressBar.setIndeterminate(true);
        }
    }

    @Override
    public int getItemViewType(int position) {
        return array.get(position) == null ? VIEW_TYPE_LOADING:VIEW_TYPE_DATA;
    }

    @Override
    public int getItemCount() {
        return array.size();
    }
    public class LoadingViewHolder extends RecyclerView.ViewHolder{
        ProgressBar progressBar;

        public LoadingViewHolder(@NonNull View itemView) {
            super(itemView);
            progressBar = itemView.findViewById(R.id.progressbar);
        }
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView name, price, desciption, id;
        ImageView image;
        private ItemClickListener itemClickListener;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
//            id = itemView.findViewById(R.id.item_phone_id); // (nếu muốn coi id sản phẩm)
            name = itemView.findViewById(R.id.item_phone_name);
            price = itemView.findViewById(R.id.item_phone_price);
            desciption = itemView.findViewById(R.id.item_phone_description);
            image = itemView.findViewById(R.id.item_phone_image);

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
