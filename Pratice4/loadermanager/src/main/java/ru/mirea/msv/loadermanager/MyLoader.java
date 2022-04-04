package ru.mirea.msv.loadermanager;

import android.content.Context;
import android.os.Bundle;
import android.os.SystemClock;


import androidx.annotation.NonNull;
import androidx.loader.content.AsyncTaskLoader;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MyLoader extends AsyncTaskLoader<String> {
    private String firstName;
    private String myJob;
    public static final String ARG_WORD = "word";
    public MyLoader(@NonNull Context context, Bundle args) {
        super(context);
        if (args != null)
        {
            firstName = args.getString(ARG_WORD);
            myJob = args.getString("job");
        }
    }
    @Override
    protected void onStartLoading() {
        super.onStartLoading();
        forceLoad();
    }
    @Override
    public String loadInBackground() {
        SystemClock.sleep(1000);
        List<Character> list = new ArrayList<Character>();
        for(char c : myJob.toCharArray()) {
            list.add(c);
        }
        Collections.shuffle(list);
        StringBuilder builder = new StringBuilder();
        for(char c : list) {
            builder.append(c);
        }

        return builder.toString();
    }
}
