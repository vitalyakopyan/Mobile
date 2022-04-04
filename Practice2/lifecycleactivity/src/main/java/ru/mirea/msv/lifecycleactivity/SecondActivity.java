package ru.mirea.msv.lifecycleactivity;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static final String TAG = "SecondActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i(TAG, new Object(){}.getClass().getEnclosingMethod().getName());
        setContentView(R.layout.activity_second);

        ((TextView)findViewById(R.id.textView)).setText((String)getIntent().getSerializableExtra("key"));
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
}