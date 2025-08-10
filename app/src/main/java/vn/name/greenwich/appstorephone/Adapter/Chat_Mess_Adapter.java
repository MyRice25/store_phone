package vn.name.greenwich.appstorephone.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import vn.name.greenwich.appstorephone.Class.ChatMessager;
import vn.name.greenwich.appstorephone.R;

public class Chat_Mess_Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context context;
    private List<ChatMessager> chatMessagerList;
    private String id_send;
    private static final int TYPE_SEND = 1;
    private static final int TYPE_RECEIVED = 2;

    public Chat_Mess_Adapter(Context context, List<ChatMessager> chatMessagerList, String id_send) {
        this.context = context;
        this.chatMessagerList = chatMessagerList;
        this.id_send = id_send;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view;
        if (viewType == TYPE_SEND){
            view = LayoutInflater.from(context).inflate(R.layout.item_send_chatting, parent, false);
            return  new SendMessageViewHolder(view);
        }else {
            view = LayoutInflater.from(context).inflate(R.layout.item_received_chat, parent, false);
            return  new ReceivedViewHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (getItemViewType(position) == TYPE_SEND){
            ((SendMessageViewHolder) holder).txt_mess_send.setText(chatMessagerList.get(position).messager);
            ((SendMessageViewHolder) holder).txt_datetime_send.setText(chatMessagerList.get(position).date_time);
        }else {
            ((ReceivedViewHolder) holder).txt_mess_received.setText(chatMessagerList.get(position).messager);
            ((ReceivedViewHolder) holder).txt_datetime_received.setText(chatMessagerList.get(position).date_time);
        }
    }

    @Override
    public int getItemCount() {
        return chatMessagerList.size();
    }

    @Override
    public int getItemViewType(int position) {
        if (chatMessagerList.get(position).id_send.equals(id_send)){
            return  TYPE_SEND;
        }else {
            return TYPE_RECEIVED;
        }
    }

    class SendMessageViewHolder extends RecyclerView.ViewHolder{
        TextView txt_mess_send, txt_datetime_send;

        public SendMessageViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_mess_send = itemView.findViewById(R.id.txt_message_send);
            txt_datetime_send = itemView.findViewById(R.id.date_time_send);
        }
    }

    class ReceivedViewHolder extends RecyclerView.ViewHolder{

        TextView txt_mess_received, txt_datetime_received;

        public ReceivedViewHolder(@NonNull View itemView) {
            super(itemView);
            txt_mess_received = itemView.findViewById(R.id.txt_message_received);
            txt_datetime_received = itemView.findViewById(R.id.date_time_received);
        }
    }
}
