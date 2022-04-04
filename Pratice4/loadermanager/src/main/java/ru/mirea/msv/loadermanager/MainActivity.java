package ru.mirea.msv.loadermanager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;

import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity implements
        LoaderManager.LoaderCallbacks<String> {
    public final String TAG = this.getClass().getSimpleName();
    private int LoaderID = 1234;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.button).setOnClickListener((view)->{
            Bundle bundle = new Bundle();
            bundle.putString(MyLoader.ARG_WORD, "ScrambleText");
            bundle.putString("job", ((EditText)findViewById(R.id.textEdit)).getText().toString());
            getSupportLoaderManager().initLoader(LoaderID, bundle, this);
        });
    }
    @Override
    public void onLoaderReset(@NonNull Loader<String> loader) {
        Log.d(TAG, "onLoaderReset");
    }
    @NonNull
    @Override
    public Loader<String> onCreateLoader(int i, @Nullable Bundle bundle) {
        if (i == LoaderID) {
            Toast.makeText(this, "Scrambling your text", Toast.LENGTH_SHORT).show();
            return new MyLoader(this, bundle);
        }
        return null;
    }
    @Override
    public void onLoadFinished(@NonNull Loader<String> loader, String s) {
        if (loader.getId() == LoaderID) {
            Log.d(TAG, "onLoadFinished: " + s);
            Toast.makeText(this, "onLoadFinished: ScrambleText", Toast.LENGTH_SHORT).show();
            ((TextView)findViewById(R.id.textView)).setText(s);
        }
    }
}