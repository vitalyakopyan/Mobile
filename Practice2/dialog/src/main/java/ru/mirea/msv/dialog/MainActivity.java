package ru.mirea.msv.dialog;

import android.os.Bundle;
import android.text.format.DateUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Calendar;

public class MainActivity extends AppCompatActivity {

    TextView dateView;
    Calendar calendar = Calendar.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        dateView = (TextView)findViewById(R.id.dateView);
        setDateView();
    }

    public void setDateView(){
        dateView.setText(DateUtils.formatDateTime(this,
                calendar.getTimeInMillis(),
                DateUtils.FORMAT_SHOW_DATE | DateUtils.FORMAT_SHOW_YEAR
                        | DateUtils.FORMAT_SHOW_TIME));
    }

    public void onClickShowDialog(View view){
        MyDialogFragment dialogFragment = new MyDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "mirea");
    }

    public void onClickShowTimeDialog(View view){
        MyTimeDialogFragment dialogFragment = new MyTimeDialogFragment(calendar, (view1, hourOfDay, minute) -> {
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
            calendar.set(Calendar.MINUTE, minute);
            setDateView();
        });
        dialogFragment.show(getSupportFragmentManager(), "TimeDialog");
    }

    public void onClickShowDateDialog(View view){
        MyDateDialogFragment dialogFragment = new MyDateDialogFragment(calendar, (view1, year, month, dayOfMonth) -> {
            calendar.set(Calendar.YEAR, year);
            calendar.set(Calendar.MONTH, month);
            calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
            setDateView();
        });
        dialogFragment.show(getSupportFragmentManager(), "DateDialog");
    }

    public void onClickShowProgressDialog(View view){
        MyProgressDialogFragment dialogFragment = new MyProgressDialogFragment();
        dialogFragment.show(getSupportFragmentManager(), "ProgressDialog");
    }

    public void onOkClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Иду дальше\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onCancelClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"Нет\"!",
                Toast.LENGTH_LONG).show();
    }
    public void onNeutralClicked() {
        Toast.makeText(getApplicationContext(), "Вы выбрали кнопку \"На паузе\"!",
                Toast.LENGTH_LONG).show();
    }
}