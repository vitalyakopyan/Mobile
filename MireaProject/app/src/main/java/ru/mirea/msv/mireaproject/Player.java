package ru.mirea.msv.mireaproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Player extends Fragment {

    boolean play;

    public Player() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_player, container, false);

        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        play = false;
        view.findViewById(R.id.playButton).setOnClickListener((view2)->{
            play = !play;
            if (play) {
                getActivity().startService(new Intent(getActivity(), PlayerService.class));
            }
            else {
                getActivity().stopService(new Intent(getActivity(), PlayerService.class));
            }
        });
    }
}
