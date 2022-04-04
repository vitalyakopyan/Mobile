package ru.mirea.msv.dialog;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class MyProgressDialogFragment extends DialogFragment {

    public MyProgressDialogFragment(){
        super();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        ProgressDialog dialog = new ProgressDialog(getActivity());
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("Loading...");
        return dialog;
    }
}
