package com.mirea.msv.layouttype;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView myTextView = (TextView) findViewById(R.id.textView7);
        myTextView.setText("MY NEW TEXT IN MIREA");

        Button button = (Button) findViewById(R.id.button22);
        button.setText("MireaButton");

        CheckBox checkBox = findViewById(R.id.checkBox);
        checkBox.setChecked(true);
    }
}