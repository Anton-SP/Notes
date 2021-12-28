package com.home.notes.ui;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.home.notes.R;
import com.home.notes.data.Constans;
import com.home.notes.data.InMemoryRepoImp;
import com.home.notes.data.Note;
import com.home.notes.data.Repo;
import com.home.notes.recycler.NoteAdapter;

public class NotesListActivity extends AppCompatActivity implements NoteAdapter.OnNoteClickListener {

/////////////////
    private Repo repository = InMemoryRepoImp.getInstance();

    private RecyclerView list;
    private NoteAdapter adapter;

    private ActivityResultLauncher<Intent> updateLauncher;
    private ActivityResultLauncher<Intent> createLauncher;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        fillRepo();

        adapter = new NoteAdapter();
        adapter.setNotes(repository.getAll());

        adapter.setOnNoteClickListener(this);

        list = findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);

        updateNote();
        createNote();


    }


    private void fillRepo() {
        repository.create(new Note("Title1", "Description 1"));
        repository.create(new Note("Title2", "Description 2"));
        repository.create(new Note("Title3", "Description 3"));
        repository.create(new Note("Title4", "Description 4"));
        repository.create(new Note("Title5", "Description 5"));
        repository.create(new Note("Title6", "Description 6"));
        repository.create(new Note("Title7", "Description 7"));
        repository.create(new Note("Title8", "Description 8"));
        repository.create(new Note("Title9", "Description 9"));
        repository.create(new Note("Title10", "Description 10"));
        repository.create(new Note("Title11", "Description 11"));
        repository.create(new Note("Title12", "Description 12"));
        repository.create(new Note("Title13", "Description 13"));
        repository.create(new Note("Title14", "Description 14"));
        repository.create(new Note("Title15", "Description 15"));


    }

    @Override
    public void onNoteClick(Note note) {
        Intent editIntent = new Intent(this, EditNoteActivity.class);
        editIntent.putExtra(Constans.NOTE, note);
        updateLauncher.launch(editIntent);

    }

    private void updateNote() {
        updateLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Intent updateResult = result.getData();
                    if (updateResult != null) {
                        Note updatedNote = (Note) updateResult.getSerializableExtra(Constans.NOTE);
                        repository.update(updatedNote);
                        adapter.notifyItemChanged(updatedNote.getId());
                    }
                }
        );

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_create:
                Intent createNoteIntent = new Intent(this, CreateNoteActivity.class);
                createLauncher.launch(createNoteIntent);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void createNote() {

        createLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Intent createResult = result.getData();
                    if (createResult != null) {
                        Note newNote = (Note) createResult.getSerializableExtra(Constans.NOTE);
                        repository.create(newNote);
                        adapter.notifyItemInserted(newNote.getId());
                        adapter.notifyItemInserted(newNote.getId());

                    }
                }
        );


    }


}


