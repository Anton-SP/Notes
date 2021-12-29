package com.home.notes.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.google.android.material.button.MaterialButton;
import com.home.notes.R;
import com.home.notes.data.Constans;
import com.home.notes.data.Note;

import java.sql.Connection;

public class CreateNoteFragment extends Fragment {

    private EditText title;
    private EditText description;
    private MaterialButton createNote;
    private int id = -1;




    public CreateNoteFragment() {
        // Required empty public constructor
    }

    public static CreateNoteFragment newInstance() {
        CreateNoteFragment fragment = new CreateNoteFragment();
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
        return inflater.inflate(R.layout.fragment_create_note, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);


        title= view.findViewById(R.id.fragment_create_title);
        description = view.findViewById(R.id.fragment_create_description);
        createNote = view.findViewById(R.id.fragment_create_note_button);


        createNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle result = new Bundle();
                Note createdNote = new Note(id, title.getText().toString(), description.getText().toString());
                result.putSerializable(Constans.NOTE,createdNote);
                getParentFragmentManager().setFragmentResult(Constans.REQUEST_KEY,result);
                getParentFragmentManager().popBackStack();

                /*
                  Bundle result = new Bundle();
                Note updatedNote = new Note(id, title.getText().toString(), description.getText().toString());
                result.putSerializable(Constans.NOTE,updatedNote);

                getParentFragmentManager().setFragmentResult(Constans.REQUEST_KEY,result);
                getParentFragmentManager().popBackStack();
                */
            }
        });

    }


}