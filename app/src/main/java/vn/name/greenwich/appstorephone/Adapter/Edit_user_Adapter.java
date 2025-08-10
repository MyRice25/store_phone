package vn.name.greenwich.appstorephone.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.name.greenwich.appstorephone.Class.User;
import vn.name.greenwich.appstorephone.Interface.ItemClickListener;
import vn.name.greenwich.appstorephone.R;

public class Edit_user_Adapter extends RecyclerView.Adapter<Edit_user_Adapter.MyViewHolder> {
    Context context;
    List<User> array;

    public Edit_user_Adapter(Context context, List<User> array) {
        this.context = context;
        this.array = array;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_edit_user, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        User user = array.get(position);
        holder.email.setText(user.getEmail());
        holder.address.setText(user.getAddress());
        holder.username.setText(user.getUsername());
        holder.mobile.setText(user.getMobile());
    }

    @Override
    public int getItemCount() {
        return array.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener, View.OnLongClickListener{
        TextView email, address, username, mobile;
        private ItemClickListener itemClickListener;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            email = itemView.findViewById(R.id.item_email_edit);
            address = itemView.findViewById(R.id.item_address_edit);
            username = itemView.findViewById(R.id.item_username_edit);
            mobile = itemView.findViewById(R.id.item_mobile_edit);
        }

        public void setItemClickListener(ItemClickListener itemClickListener) {
            this.itemClickListener = itemClickListener;
        }

        @Override
        public void onClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), false);
        }

        @Override
        public boolean onLongClick(View v) {
            itemClickListener.onClick(v, getAdapterPosition(), true);
            return false;
        }
    }
}
