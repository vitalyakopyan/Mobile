package ru.mirea.msv.internalfilestorage;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private int PERMISSIONS_CODE = 123;
    private String[] PERMISSIONS = {
            Manifest.permission.WRITE_EXTERNAL_STORAGE
    };
    private boolean isPermissionGranted = false;
    private String fileName;
    private EditText editfileContents;
    private EditText editfileName;
    private SharedPreferences preferences;
    final String SAVED_TEXT = "saved_text";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (!(isPermissionGranted = hasPermissions(this, PERMISSIONS)))
            ActivityCompat.requestPermissions(this, PERMISSIONS,
                    PERMISSIONS_CODE);
        editfileContents = findViewById(R.id.editFile);
        editfileName = findViewById(R.id.editName);
        preferences = getPreferences(MODE_PRIVATE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PERMISSIONS_CODE) {
            isPermissionGranted = grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED;
        }
    }

    public static boolean hasPermissions(Context context, String[] permissions) {
        if (context != null && permissions != null) {
            for (String permission : permissions) {
                if (ActivityCompat.checkSelfPermission(context, permission) != PackageManager.PERMISSION_GRANTED) {
                    return false;
                }
            }
        }
        return true;
    }

    public String getTextFromFile(String file) {
        FileInputStream fin = null;
        try {
            fin = openFileInput(file);
            byte[] bytes = new byte[fin.available()];
            fin.read(bytes);
            String text = new String(bytes);
            Log.d(LOG_TAG, text);
            return text;
        } catch (IOException ex) {
            Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
        } finally {
            try {
                if (fin != null)
                    fin.close();
            } catch (IOException ex) {
                Toast.makeText(this, ex.getMessage(), Toast.LENGTH_SHORT).show();
            }
        }
        return null;
    }

    public boolean isExternalStorageWritable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state)) {
            return true;
        }
        return false;
    }

    public boolean isExternalStorageReadable() {
        String state = Environment.getExternalStorageState();
        if (Environment.MEDIA_MOUNTED.equals(state) ||
                Environment.MEDIA_MOUNTED_READ_ONLY.equals(state)) {
            return true;
        }
        return false;
    }

    public void onSaveText(View view) {
        new Thread(()->{
            if (isPermissionGranted && isExternalStorageWritable()) {
                FileOutputStream outputStream;
                fileName = editfileName.getText().toString();
                if (!fileName.equals("")) {
                    try {
                        outputStream = openFileOutput(fileName, Context.MODE_PRIVATE);
                        outputStream.write(editfileContents.getText().toString().getBytes());
                        outputStream.close();
                        SharedPreferences.Editor editor = preferences.edit();
                        editor.putString(SAVED_TEXT, editfileName.getText().toString());
                        editor.apply();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
    public void onLoadText(View view) {

        new Thread(()->{
            if (isPermissionGranted && isExternalStorageReadable()) {
                editfileContents.post(()->{
                    fileName = preferences.getString(SAVED_TEXT, null);
                    if (fileName != null) {
                        editfileContents.setText(getTextFromFile(fileName));
                        editfileName.setText(fileName);
                    }
                });
            }
        }).start();
    }
}