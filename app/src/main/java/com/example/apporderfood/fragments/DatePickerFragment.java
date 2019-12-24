package com.example.apporderfood.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.widget.DatePicker;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import com.example.apporderfood.R;

import java.util.Calendar;

public class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {

    private DatePickerDialog mDatePickerDialog;
    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        if(getActivity() != null) {
            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);
            mDatePickerDialog = new DatePickerDialog(getActivity(), this, year, month, day);
        }
        return mDatePickerDialog;
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        if (getActivity() != null) {
            EditText edDob = getActivity().findViewById(R.id.ed_dob);
            String dob = dayOfMonth + "/" + (month + 1) + "/" + year;
            edDob.setText(dob);
        }
    }
}
