package com.home.notes.fragments;

import android.content.res.Configuration;
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


public class EditNoteFragment extends Fragment {


    private EditText title;
    private EditText description;
    private MaterialButton updateNote;
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
            updateNote = view.findViewById(R.id.fragment_update_note_button);
            Note note = (Note) getArguments().getSerializable(Constans.NOTE);
            title.setText(note.getTitle());
            description.setText(note.getDescription());
            id = note.getId();
              }

        updateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle result = new Bundle();
                Note updatedNote = new Note(id, title.getText().toString(), description.getText().toString());
                result.putSerializable(Constans.NOTE,updatedNote);

                getParentFragmentManager().setFragmentResult(Constans.REQUEST_KEY,result);
                if  (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT){
                    getParentFragmentManager().popBackStack();
                } else {
                    getParentFragmentManager().beginTransaction().remove(EditNoteFragment.this).commit();
                }

            }
        });

    }
}


