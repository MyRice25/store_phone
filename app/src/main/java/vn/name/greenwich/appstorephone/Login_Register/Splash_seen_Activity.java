package vn.name.greenwich.appstorephone.Login_Register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import io.paperdb.Paper;
import vn.name.greenwich.appstorephone.Activity.MainActivity;
import vn.name.greenwich.appstorephone.R;

public class Splash_seen_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_seen);
        Paper.init(this);
        Thread thread = new Thread(){
            @Override
            public void run() {
                try {
                    sleep(1000);
                }catch (Exception exception){

                }finally {
                    // nếu chưa đki thì sẽ khai báo lại login để đkí
                    if (Paper.book().read("user") == null){
                        Intent intent = new Intent(getApplicationContext(), Login_Activity.class);
                        startActivity(intent);
                        finish();
                    }else {
                        Intent main = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(main);
                        finish();
                    }
                }
            }
        };
        thread.start();
    }
}