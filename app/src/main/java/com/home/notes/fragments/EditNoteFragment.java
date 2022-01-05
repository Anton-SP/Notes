package com.home.notes.fragments;

import android.app.DatePickerDialog;
import android.app.FragmentManager;
import android.content.res.Configuration;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


import com.google.android.material.button.MaterialButton;
import com.home.notes.R;
import com.home.notes.data.Constans;
import com.home.notes.data.Note;


public class EditNoteFragment extends Fragment  {


    private EditText title;
    private EditText description;
    private EditText date;
    private MaterialButton updateNote;
    private MaterialButton setDate;
    private Spinner importanceSpinner;
    private String importance;
    private int id = -1;



    private Note note;


    public EditNoteFragment() {
        // Required empty public constructor
    }



    public static EditNoteFragment newInstance(Note note) {
        EditNoteFragment fragment = new EditNoteFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constans.NOTE,note);
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
        return inflater.inflate(R.layout.fragment_edit_note, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Bundle args = getArguments();



        if (args!=null &&  args.containsKey(Constans.NOTE)) {
            title= view.findViewById(R.id.fragment_edit_title);
            description = view.findViewById(R.id.fragment_edit_description);
            date = view.findViewById(R.id.fragment_edit_date);
            updateNote = view.findViewById(R.id.fragment_update_note_button);
            importanceSpinner = view.findViewById(R.id.fragment_edit_importance_spinner);
            setDate = view.findViewById(R.id.fragment_edit_date_button);

            Note note = (Note) getArguments().getSerializable(Constans.NOTE);
            title.setText(note.getTitle());
            description.setText(note.getDescription());
            id = note.getId();
//////////////////////////////////////////////


           // getParentFragmentManager().setFragmentResultListener(Constans.REQUEST_DATE_KEY, this, new FragmentResultListener() {
           requireActivity().getSupportFragmentManager().setFragmentResultListener(Constans.REQUEST_DATE_KEY, this, new FragmentResultListener() {
                @Override
                public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                    String setDate = result.getString(Constans.DATE);
                    // Note resultNote = (Note) result.getSerializable(Constans.NOTE);
                    Log.d(Constans.TAG, "onFragmentResult() called with: requestKey = [" + requestKey + "], result = [" + result + "]");
                    date.setText(setDate);
                }
            });




            importanceSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                    importance = (String) parent.getItemAtPosition(position);
                }

                @Override
                public void onNothingSelected(AdapterView<?> parent) {

                    importance = (String) parent.getItemAtPosition(1);
                }
            });

        }



        updateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle result = new Bundle();
                Note updatedNote = new Note(id, title.getText().toString(), description.getText().toString(),importance,date.getText().toString());
                result.putSerializable(Constans.NOTE,updatedNote);
                requireActivity().getSupportFragmentManager().setFragmentResult(Constans.REQUEST_KEY,result);
              //  getParentFragmentManager().setFragmentResult(Constans.REQUEST_KEY,result);
                if  (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                    getParentFragmentManager().popBackStack();
                } else {
                    getParentFragmentManager().beginTransaction().remove(EditNoteFragment.this).commit();
                }

            }
        });

        initSpinner();

        setDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                requireActivity().getSupportFragmentManager()
                        .beginTransaction()
                        .add(R.id.list_fragment_holder, DateFragment.newInstance())  //EditNoteFragment.newInstance(note))
                        .addToBackStack(null)
                        .commit();

            }
        });

    }

    private void initSpinner() {
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(requireContext(),
                R.array.note_importance_level,
                android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource( android.R.layout.simple_spinner_item);
        importanceSpinner.setAdapter(adapter);
    }




}


