package com.home.notes.ui;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.button.MaterialButton;
import com.home.notes.R;
import com.home.notes.data.Constans;
import com.home.notes.data.Note;

public class CreateNoteActivity extends AppCompatActivity {
    private EditText title;
    private EditText description;
    private MaterialButton createNote;
    private int id = -1;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_note);

        title = findViewById(R.id.create_title);
        description = findViewById(R.id.create_description);
        createNote = findViewById(R.id.create_note_button);


        createNote.setOnClickListener(v -> {
            Note newNote = new Note(id, title.getText().toString(), description.getText().toString());
            Intent returnCreateIntent = new Intent();
            returnCreateIntent.putExtra(Constans.NOTE, newNote);
            setResult(RESULT_OK, returnCreateIntent);
            finish();
        });


    }
}
/*
     Intent intent = getIntent();
        if (intent != null) {
            Note note = (Note) intent.getSerializableExtra(Constans.NOTE);
            id = note.getId();
            title.setText(note.getTitle());
            description.setText(note.getDescription());
        }

        updateNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Note updatedNote = new Note(id, title.getText().toString(), description.getText().toString());
                Intent returnUpdateIntent = new Intent();
                returnUpdateIntent.putExtra(Constans.NOTE, updatedNote);
                setResult(RESULT_OK,returnUpdateIntent);
                finish();
            }
        });

    }


*/

