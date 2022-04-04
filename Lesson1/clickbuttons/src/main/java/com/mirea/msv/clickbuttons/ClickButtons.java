package com.mirea.msv.clickbuttons;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ClickButtons extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnOK = (Button) findViewById(R.id.btnOK);
        Button btnCancel = (Button) findViewById(R.id.btnCancel);
        TextView tvOut = (TextView) findViewById(R.id.tvOut);

        View.OnClickListener oclBtnOK = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOut.setText("Нажата кнопка OK");
            }
        };

        View.OnClickListener oclBtnCancel = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvOut.setText("Нажата кнопка Canel"); }
        };

        btnOK.setOnClickListener(oclBtnOK);
        btnCancel.setOnClickListener(oclBtnCancel);
    }

    public void onButtonClickToast (View v) {
        Toast.makeText(v.getContext(), "Ещё один способ!", Toast.LENGTH_SHORT).show();
    }
}