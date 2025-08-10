package vn.name.greenwich.appstorephone.Account;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import vn.name.greenwich.appstorephone.Fragment.Fragment_Account;
import vn.name.greenwich.appstorephone.R;

public class Help_Activity extends AppCompatActivity {
    TextView cancel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        cancel = findViewById(R.id.Cancel1);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}