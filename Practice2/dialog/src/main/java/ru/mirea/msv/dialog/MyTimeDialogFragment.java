package ru.mirea.msv.dialog;

import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.format.DateFormat;

import androidx.fragment.app.DialogFragment;

import java.util.Calendar;

public class MyTimeDialogFragment extends DialogFragment{

    Calendar calendar;
    TimePickerDialog.OnTimeSetListener onTimeSetListener;
    //DialogInterface.OnCancelListener onCancelListener;

    public MyTimeDialogFragment(Calendar calendar, TimePickerDialog.OnTimeSetListener onTimeSetListener) {
        super();
        this.calendar = calendar;
        this.onTimeSetListener = onTimeSetListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        int hour = calendar.get(Calendar.HOUR_OF_DAY);
        int minute = calendar.get(Calendar.MINUTE);

        TimePickerDialog dialog = new TimePickerDialog(getActivity(), onTimeSetListener, hour, minute,
                DateFormat.is24HourFormat(getActivity()));
        //dialog.setOnCancelListener(onCancelListener);
        return dialog;
    }
}
