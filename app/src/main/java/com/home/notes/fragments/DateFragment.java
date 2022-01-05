package com.home.notes.fragments;

import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.google.android.material.button.MaterialButton;
import com.home.notes.R;
import com.home.notes.data.Constans;

import java.util.Date;


public class DateFragment extends Fragment {

    private DatePicker datePicker;
    private MaterialButton cancel;
    private MaterialButton set;


    public DateFragment() {
        // Required empty public constructor
    }

    public static DateFragment newInstance() {
        DateFragment fragment = new DateFragment();
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

        return inflater.inflate(R.layout.fragment_date, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        datePicker = view.findViewById(R.id.date_picker);
        cancel = view.findViewById(R.id.fragment_date_cancel);
        set = view.findViewById(R.id.fragment_date_set);


        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle date = new Bundle();
                String newDate = datePicker.getDayOfMonth() + "/" + datePicker.getMonth() + "/" + datePicker.getYear();
                date.putString(Constans.DATE, newDate);
                requireActivity().getSupportFragmentManager().setFragmentResult(Constans.REQUEST_DATE_KEY, date);
               // getParentFragmentManager().setFragmentResult(Constans.REQUEST_DATE_KEY, date);
                Log.d(Constans.TAG, "get new date " + newDate);
                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    Log.d(Constans.TAG, "popBackStack " + newDate);

                    getParentFragmentManager().popBackStack();
                } else {
                    getParentFragmentManager().beginTransaction().remove(DateFragment.this).commit();
                }

            }
        });


    }
}
