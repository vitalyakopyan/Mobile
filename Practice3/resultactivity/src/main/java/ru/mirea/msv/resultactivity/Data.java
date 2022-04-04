package ru.mirea.msv.resultactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class Data extends AppCompatActivity {
    private EditText universityEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_data);
        universityEditText = findViewById(R.id.editText);
    }

    public void sendResultOnMainActivityOnClick(View view) {
        Intent intent = new Intent();
        intent.putExtra("name", universityEditText.getText().toString());
        setResult(RESULT_OK, intent);
        finish();
    }
}