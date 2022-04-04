package ru.mirea.msv.mireaproject;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.navigation.NavigationView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static android.content.Context.MODE_PRIVATE;

public class Settings extends Fragment {

    public final static String SP_NAME = "MIREAPROJECT_SETTINGS_NAME";
    public final static String SP_EMAIL = "MIREAPROJECT_SETTINGS_EMAIL";
    private static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", Pattern.CASE_INSENSITIVE);

    private SharedPreferences preferences;
    private EditText editName;
    private EditText editEmail;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        preferences = getActivity().getPreferences(MODE_PRIVATE);
        editName = view.findViewById(R.id.editTextTextPersonName);
        editEmail = view.findViewById(R.id.editTextTextEmailAddress);

        editName.setText(preferences.getString(Settings.SP_NAME, "SomeUserName"));
        editEmail.setText(preferences.getString(Settings.SP_EMAIL, "some@user.email"));

        view.findViewById(R.id.buttonSettingsSave).setOnClickListener((view2)->{
            boolean isEmpty = false;
            if (editName.getText().toString().trim().length() == 0) {
                isEmpty = true;
                editName.setError("Empty");
            }
            if (editEmail.getText().toString().trim().length() == 0) {
                isEmpty = true;
                editEmail.setError("Empty");
            }
            if (isEmpty)
                return;
            if (validateEmail(editEmail.getText().toString())) {
                SharedPreferences.Editor editor = preferences.edit();
                editor.putString(SP_NAME, editName.getText().toString());
                editor.putString(SP_EMAIL, editEmail.getText().toString());
                editor.apply();
                ((TextView)(((NavigationView)getActivity().findViewById(R.id.nav_view)).getHeaderView(0).findViewById(R.id.textViewNavHeaderMainName))).setText(
                        preferences.getString(Settings.SP_NAME, "SomeUserName")
                );
                ((TextView)(((NavigationView)getActivity().findViewById(R.id.nav_view)).getHeaderView(0).findViewById(R.id.textViewNavHeaderMainEmail))).setText(
                        preferences.getString(Settings.SP_EMAIL, "some@user.email")
                );
            }else
                editEmail.setError("Invalid format");
        });
    }

    private boolean validateEmail(String emailStr) {
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(emailStr);
        return matcher.find();
    }
}
