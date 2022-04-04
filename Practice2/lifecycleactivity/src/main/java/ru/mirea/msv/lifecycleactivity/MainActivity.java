package ru.mirea.msv.lifecycleactivity;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.i(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Override
    protected void onPostCreate(@Nullable Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        Log.i(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.i(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.i(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.i(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.i(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Override
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.i(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
    }

    public void onClickNewActivity(View view) {
        Intent intent = new Intent(this, SecondActivity.class);
        intent.putExtra("key", "MIREA - ФАМИЛИЯ ИМЯ ОТЧЕТСВО СТУДЕНТА");
        startActivity(intent);
    }
}