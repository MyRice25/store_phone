package vn.name.greenwich.appstorephone.Chatting;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.google.firebase.firestore.DocumentChange;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.logging.SimpleFormatter;

import vn.name.greenwich.appstorephone.Adapter.Chat_Mess_Adapter;
import vn.name.greenwich.appstorephone.Class.ChatMessager;
import vn.name.greenwich.appstorephone.R;
import vn.name.greenwich.appstorephone.Server.Connect;

public class Message_user_Activity extends AppCompatActivity {
    Toolbar toolbar;
    RecyclerView recyclerView_chat;
    EditText edt_chat;
    ImageView img_send;
    FirebaseFirestore firestore;
    Chat_Mess_Adapter chatMessAdapter;
    List<ChatMessager> chatMessagerList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_user);
        initView();
        initToolbar();
        initControl();
        listenerMess();
        insertUser();
    }

    private void initToolbar() {
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setLogo(R.drawable.ic_account);
        toolbar.setNavigationIcon(R.drawable.ic_baseline_back_24);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }

    // chat với admin
    private void insertUser() {
        HashMap<String, Object> user = new HashMap<>();
        user.put("id", Connect.user_current.getId());
        user.put("username", Connect.user_current.getUsername());
        // key tạo id user không trùng
        firestore.collection("users").document(String.valueOf(Connect.user_current.getId())).set(user);
    }

    private void initControl() {
        img_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sendMessToFireBase();
            }
        });
    }

    private void sendMessToFireBase() {
        String str_mess = edt_chat.getText().toString().trim();
        if (TextUtils.isEmpty(str_mess)){

        }else {
            HashMap<String, Object> message = new HashMap<>();
            message.put(Connect.ID_SEND, String.valueOf(Connect.user_current.getId()));
            message.put(Connect.ID_TAKEN, Connect.ID_RECEIVED);
            message.put(Connect.MESSAGE, str_mess);
            message.put(Connect.DATA_TIME, new Date());
            firestore.collection(Connect.PATH_CHAT).add(message);
            // sau khi user nhập xong sẽ xóa dòng đã nhập
            edt_chat.setText("");
        }
    }

    private void listenerMess(){
        firestore.collection(Connect.PATH_CHAT)
                .whereEqualTo(Connect.ID_SEND, String.valueOf(Connect.user_current.getId()))
                .whereEqualTo(Connect.ID_TAKEN, Connect.ID_RECEIVED)
                .addSnapshotListener(eventListener);

        firestore.collection(Connect.PATH_CHAT)
                .whereEqualTo(Connect.ID_SEND, Connect.ID_RECEIVED)
                .whereEqualTo(Connect.ID_TAKEN, String.valueOf(Connect.user_current.getId()))
                .addSnapshotListener(eventListener);
    }

    private final EventListener<QuerySnapshot> eventListener = (value, error) -> {
        if (error != null){
            return;
        }
        if (value != null){
            int count = chatMessagerList.size();
            for (DocumentChange documentChange : value.getDocumentChanges()){
                if (documentChange.getType() == DocumentChange.Type.ADDED){
                    ChatMessager chatMessager = new ChatMessager();
                    chatMessager.id_send = documentChange.getDocument().getString(Connect.ID_SEND);
                    chatMessager.id_received = documentChange.getDocument().getString(Connect.ID_TAKEN);
                    chatMessager.messager = documentChange.getDocument().getString(Connect.MESSAGE);
                    chatMessager.dateObj = documentChange.getDocument().getDate(Connect.DATA_TIME);
                    chatMessager.date_time = format_date(documentChange.getDocument().getDate(Connect.DATA_TIME));
                    chatMessagerList.add(chatMessager);
                }
            }
            Collections.sort(chatMessagerList, (obj1, obj2) -> obj1.dateObj.compareTo(obj2.dateObj));
            if (count == 0){
                chatMessAdapter.notifyDataSetChanged();
            }else {
                chatMessAdapter.notifyItemRangeInserted(chatMessagerList.size(), chatMessagerList.size());
                recyclerView_chat.smoothScrollToPosition(chatMessagerList.size() - 1);
            }

        }
    };

    private String format_date(Date date){
        return new SimpleDateFormat("MMMM dd, yyyy - hh:mm a", Locale.getDefault()).format(date);
    }


    private void initView() {
        toolbar = findViewById(R.id.toolbar_message);
        chatMessagerList = new ArrayList<>();
        firestore = FirebaseFirestore.getInstance();
        recyclerView_chat = findViewById(R.id.recyclerview_chat);
        img_send = findViewById(R.id.img_chat);
        edt_chat = findViewById(R.id.edt_chat_text);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView_chat.setLayoutManager(layoutManager);
        recyclerView_chat.setHasFixedSize(true);
        chatMessAdapter = new Chat_Mess_Adapter(getApplicationContext(), chatMessagerList, String.valueOf(Connect.user_current.getId()));
        recyclerView_chat.setAdapter(chatMessAdapter);
    }
}