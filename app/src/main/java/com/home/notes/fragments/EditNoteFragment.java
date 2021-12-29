package com.home.notes.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.button.MaterialButton;
import com.home.notes.R;
import com.home.notes.data.Constans;
import com.home.notes.data.Note;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link EditNoteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class EditNoteFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private EditText title;
    private EditText description;
    private MaterialButton updateNote;
    private int id = -1;


    // TODO: Rename and change types of parameters
    private Note note;


    public EditNoteFragment() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
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
   //         Toast.makeText(requireContext(),id+"index",Toast.LENGTH_SHORT).show();
            }

        updateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle result = new Bundle();
                Note updatedNote = new Note(id, title.getText().toString(), description.getText().toString());
                result.putSerializable(Constans.NOTE,updatedNote);

                getParentFragmentManager().setFragmentResult(Constans.REQUEST_KEY,result);
                getParentFragmentManager().popBackStack();
                //getFragmentManager().popBackStack();
            }
        });

    }
}
/*


button.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Bundle result = new Bundle();
        result.putString("bundleKey", "result");
        getParentFragmentManager().setFragmentResult("requestKey", result);
    }
});


*/



/*
  private EditText title;
    private EditText description;
    private MaterialButton updateNote;
    private int id = -1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_note);

        title = findViewById(R.id.edit_title);
        description = findViewById(R.id.edit_description);
        updateNote = findViewById(R.id.update_note_button);


        Intent intent = getIntent();
        if (intent != null) {
            Note note = (Note) intent.getSerializableExtra(Constans.NOTE);
            id = note.getId();
            title.setText(note.getTitle());
            description.setText(note.getDescription());
        }


        updateNote.setOnClickListener(v -> {
            Note updatedNote = new Note(id, title.getText().toString(), description.getText().toString());
            Intent returnUpdateIntent = new Intent();
            returnUpdateIntent.putExtra(Constans.NOTE, updatedNote);
            setResult(RESULT_OK, returnUpdateIntent);
            finish();
        });

    }
}





*/

