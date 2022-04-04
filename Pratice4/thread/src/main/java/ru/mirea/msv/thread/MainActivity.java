package ru.mirea.msv.thread;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView infoTextView = findViewById(R.id.textView);
        Thread mainThread = Thread.currentThread();
        infoTextView.setText(new StringBuilder("Текущий поток: " + mainThread.getName()).toString());

        mainThread.setName("MireaThread");
        infoTextView.append("\n Новое имя потока: " + mainThread.getName());

        findViewById(R.id.button).setOnClickListener(
                (view)->{
                    Runnable runnable = new Runnable() {
                        public void run() {
                            int numberThread = counter++;
                            Log.i("ThreadProject", "Запущен поток № " + numberThread);
                            long endTime = System.currentTimeMillis()
                                    + 20 * 1000;
                            while (System.currentTimeMillis() < endTime) {
                                synchronized (this) {
                                    try {
                                        wait(endTime -
                                                System.currentTimeMillis());
                                    } catch (Exception e) {
                                    }
                                }
                            }
                            Log.i("ThreadProject", "Выполнен поток № " + numberThread);
                        }
                    };
                    Thread thread = new Thread(runnable);
                    thread.start();
                }
        );
    }
}