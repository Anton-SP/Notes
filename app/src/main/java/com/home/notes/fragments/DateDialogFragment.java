package com.home.notes.fragments;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.Toast;

import com.home.notes.R;
import com.home.notes.data.Constans;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


public class DateDialogFragment extends DialogFragment
        implements DatePickerDialog.OnDateSetListener {


    public DateDialogFragment() {
        // Required empty public constructor
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
       // return super.onCreateDialog(savedInstanceState);
        // Use the current date as the default date in the picker
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int day = c.get(Calendar.DAY_OF_MONTH);

        // Create a new instance of DatePickerDialog and return it

        return new DatePickerDialog(getActivity(), this, year, month, day);
    }

    public static DateDialogFragment newInstance() {
        DateDialogFragment fragment = new DateDialogFragment();
        Bundle args = new Bundle();

        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_date_dialog, container, false);
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

        Bundle date = new Bundle();
        String newDate = view.getDayOfMonth() + "/" + (view.getMonth() + 1)+ "/" + view.getYear();
        date.putString(Constans.DATE, newDate);
        requireActivity().getSupportFragmentManager().setFragmentResult(Constans.REQUEST_DATE_KEY, date);


    }
}