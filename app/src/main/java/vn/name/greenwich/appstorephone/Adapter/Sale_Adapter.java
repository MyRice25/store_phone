package vn.name.greenwich.appstorephone.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatButton;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import io.reactivex.rxjava3.internal.operators.maybe.MaybeNever;
import vn.name.greenwich.appstorephone.Activity.MainActivity;
import vn.name.greenwich.appstorephone.Category.TabletActivity;
import vn.name.greenwich.appstorephone.Class.Cart;
import vn.name.greenwich.appstorephone.Class.Sale_code;
import vn.name.greenwich.appstorephone.Fragment.Fragment_Cart;
import vn.name.greenwich.appstorephone.Fragment.Fragment_Category;
import vn.name.greenwich.appstorephone.Interface.ItemClickListener;
import vn.name.greenwich.appstorephone.R;
import vn.thanguit.toastperfect.ToastPerfect;

public class Sale_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<Sale_code> array;

    public Sale_Adapter(Context context, List<Sale_code> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sale, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof MyViewHolder){
            MyViewHolder myViewHolder = (MyViewHolder) holder;
            Sale_code saleCode = array.get(position);
            myViewHolder.txt_name_sale.setText(saleCode.getName_sale().trim());
            myViewHolder.sale.setText(saleCode.getSale().trim());
            myViewHolder.out_date.setText(saleCode.getOut_of_date());
            Glide.with(context).load(saleCode.getImage_sale()).into(myViewHolder.image_sale);

            myViewHolder.setItemClickListener(new ItemClickListener() {
                @Override
                public void onClick(View view, int pos, boolean isLongClick) {
                    if (!isLongClick){
                        // click vào khi đã thêm vào mã giảm giá
                        Intent intent = new Intent(context, Fragment_Category.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        context.startActivity(intent);
                        ToastPerfect.makeText(view.getContext(), ToastPerfect.SUCCESS,"Have add sale code success!!",ToastPerfect.CENTER, Toast.LENGTH_LONG).show();
                    }
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView txt_name_sale, sale, out_date;
        ImageView image_sale;
        AppCompatButton btn_use_now;
        private ItemClickListener itemClickListener;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_name_sale = itemView.findViewById(R.id.item_sale_name);
            sale = itemView.findViewById(R.id.item_sale);
            out_date = itemView.findViewById(R.id.item_sale_out_date);
            image_sale = itemView.findViewById(R.id.item_sale_image);
            btn_use_now = itemView.findViewById(R.id.btn_use_now);

            itemView.setOnClickListener(this);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View view) {
            itemClickListener.onClick(view, getAdapterPosition(),false);
        }
    }
}
