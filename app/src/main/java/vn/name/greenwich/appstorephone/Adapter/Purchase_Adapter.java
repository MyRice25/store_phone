package vn.name.greenwich.appstorephone.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.name.greenwich.appstorephone.Class.Seen_order;
import vn.name.greenwich.appstorephone.R;

public class Purchase_Adapter extends RecyclerView.Adapter<Purchase_Adapter.MyViewHolder> {
    private  RecyclerView.RecycledViewPool viewPool = new RecyclerView.RecycledViewPool();

    Context context;
    List<Seen_order> seenOrderList;

    public Purchase_Adapter(Context context, List<Seen_order> seenOrderList) {
        this.context = context;
        this.seenOrderList = seenOrderList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Seen_order seenOrder = seenOrderList.get(position);
        holder.txt_purchase.setText("Purchase: " + seenOrder.getId());
        LinearLayoutManager layoutManager = new LinearLayoutManager(holder.recyclerView.getContext(), LinearLayoutManager.VERTICAL, false);
        layoutManager.setInitialPrefetchItemCount(seenOrder.getItem().size());
        // kết nối adapter Purchase_detail
        Purchase_detail_Adapter detailAdapter = new Purchase_detail_Adapter(context, seenOrder.getItem());
        holder.recyclerView.setLayoutManager(layoutManager);
        holder.recyclerView.setAdapter(detailAdapter);
        // vì sản phẩm nằm trong recycview nên phải xét thêm sp
        holder.recyclerView.setRecycledViewPool(viewPool);


    }

    @Override
    public int getItemCount() {
        return seenOrderList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView txt_purchase;
        RecyclerView recyclerView;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_purchase = itemView.findViewById(R.id.txt_purchase);
            recyclerView = itemView.findViewById(R.id.recyc_purchase);
        }
    }
}
