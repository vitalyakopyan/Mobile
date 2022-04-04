package ru.mirea.msv.practice7;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.IOException;
import java.net.Socket;

public class MainActivity extends AppCompatActivity {
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
    }

    public void onClick(View view) {
        GetTimeTask timeTask = new GetTimeTask();
        timeTask.execute();
    }

    private class GetTimeTask extends AsyncTask<Void, Void, String> {
        @Override
        protected String doInBackground(Void... params) {
            String timeResult = "";
            try {
                int port = 13;
                String host = "time-a.nist.gov";
                Socket socket = new Socket(host, port);
                BufferedReader reader = SocketUtils.getReader(socket);
                reader.readLine();
                timeResult = reader.readLine();
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            return timeResult;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            textView.setText(result);
        }
    }
}