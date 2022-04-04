package ru.mirea.msv.mireaproject;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import org.jetbrains.annotations.NotNull;

import java.util.List;

public class HistoriesDialogFragment extends DialogFragment {

    @NotNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Dialog dialog;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.label_dialog_histories)
                .setView(getLayoutInflater().inflate(R.layout.dialog_histories_add, null))
                .setPositiveButton(R.string.HistoryDialogFragmentAdd, null)
                .setNegativeButton(R.string.cancel, null);

        dialog = builder.create();
        dialog.setOnShowListener((dialog2)->{
            ((AlertDialog)dialog2).getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener((view2)->{
                EditText name = getDialog().findViewById(R.id.dialog_histories_name);
                EditText content = getDialog().findViewById(R.id.dialog_histories_content);
                boolean isOK = true;
                if (name.getText().toString().trim().length() == 0) {
                    isOK = false;
                    name.setError("Empty");
                }
                if (content.getText().toString().trim().length() == 0) {
                    isOK = false;
                    content.setError("Empty");
                }
                if (isOK) {
                    Intent intent = new Intent();
                    intent.putExtra("Name", name.getText().toString().trim());
                    intent.putExtra("Content", content.getText().toString().trim());
                    getTargetFragment().onActivityResult(getTargetRequestCode(), 123, intent);
                    dialog2.dismiss();
                }
            });
        });
        return dialog;
    }
}
