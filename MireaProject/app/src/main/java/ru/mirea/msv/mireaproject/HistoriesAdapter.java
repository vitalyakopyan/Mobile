package ru.mirea.msv.mireaproject;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class HistoriesAdapter extends RecyclerView.Adapter<HistoriesAdapter.ViewHolder> {
    List<History> histories;
    private LayoutInflater inflater;

    HistoriesAdapter(Context context, List<History> histories)
    {
        this.histories = histories;
        inflater = LayoutInflater.from(context);
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(inflater.inflate(R.layout.hisories_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull HistoriesAdapter.ViewHolder holder, int position) {
        History history =  histories.get(position);
        holder.name.setText(history.name);
        holder.content.setText(history.content);
    }

    @Override
    public int getItemCount() {
        return histories.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        final TextView name, content;

        ViewHolder(View view){
            super(view);
            name = view.findViewById(R.id.textHLIName);
            content = view.findViewById(R.id.textHLIContent);
        }
    }

    public static class History{
        final String name, content;

        History(String name, String content){
            this.content = content;
            this.name = name;
        }
    }
}
