package ru.mirea.msv.looper;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Message;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        MyLooper myLooper = new MyLooper();
        myLooper.start();
        long myAge = System.currentTimeMillis();

        findViewById(R.id.button).setOnClickListener((view)->{
            Message msg = new Message();
            Bundle bundle = new Bundle();
            bundle.putString("KEY", "Привет, я - КНОПКА, работаю в этом прложении" +
                    " уже " + (System.currentTimeMillis() - myAge)/1000 + " лет B)");
            msg.setData(bundle);
            if (myLooper != null) {
                myLooper.handler.sendMessage(msg);
            }
        });
    }
}