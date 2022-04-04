package ru.mirea.msv.practice3;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowDateString extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_date_string);

        ((TextView)findViewById(R.id.textView)).setText(getIntent().getStringExtra("dateString"));
    }
}