package ru.mirea.msv.mireaproject;

import android.app.AlertDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;
import java.util.PrimitiveIterator;

import static android.content.Context.MODE_PRIVATE;

public class Histories extends Fragment {
    public final static String SP_HISTORY = "MIREAPROJECT_HISTORIES";
    private ArrayList<HistoriesAdapter.History> histories;
    private HistoriesAdapter adapter;
    private Gson gson;
    private SharedPreferences sharedPreferences;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_histories, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        gson = new Gson();
        sharedPreferences = getActivity().getPreferences(MODE_PRIVATE);
        histories = gson.fromJson(sharedPreferences.getString(SP_HISTORY, null),  new TypeToken<List<HistoriesAdapter.History>>(){}.getType());
        if (histories == null)
            histories = new ArrayList<>();
        adapter = new HistoriesAdapter(getContext(), histories);
        RecyclerView recyclerView = ((RecyclerView)view.findViewById(R.id.fragment_histories_recycler_view));
        recyclerView.setAdapter(adapter);

        view.findViewById(R.id.floatingActionButton).setOnClickListener(
                (view2)->{
                    HistoriesDialogFragment fragment = new HistoriesDialogFragment();
                    fragment.setTargetFragment(this, 1234);
                    fragment.show(this.getParentFragmentManager(), "DialogNewHistory");
                }
        );
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 1234 && resultCode == 123){
            histories.add(new HistoriesAdapter.History(data.getStringExtra("Name"), data.getStringExtra("Content")));
            adapter.notifyItemInserted(histories.size() - 1);
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        String json = gson.toJson(histories);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SP_HISTORY, json);
        editor.apply();
    }
}