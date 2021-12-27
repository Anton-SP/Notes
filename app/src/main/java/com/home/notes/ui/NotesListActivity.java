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

import com.home.notes.R;
import com.home.notes.data.Constans;
import com.home.notes.data.InMemoryRepoImp;
import com.home.notes.data.Note;
import com.home.notes.data.Repo;
import com.home.notes.recycler.NoteAdapter;

public class NotesListActivity extends AppCompatActivity implements NoteAdapter.OnNoteClickListener {


    private Repo repository = InMemoryRepoImp.getInstance();

    private RecyclerView list;
    private NoteAdapter adapter;

    private ActivityResultLauncher<Intent> updateLauncher;


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
                        //  adapter.setNotes(repository.getAll());
                        adapter.notifyItemChanged(updatedNote.getId());//с этим получилось лучше
                    }
                }
        );



    }
    /*
 private void changeTheme() {
        themeLauncher = registerForActivityResult(
                new ActivityResultContracts.StartActivityForResult(),
                result -> {
                    Intent settingResult = result.getData();
                    if (settingResult != null) {
                        int mode = settingResult.getIntExtra(THEME_MODE, -1);
                        if (mode == 1)
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                        if (mode == 2)
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    }
                }
        );
    }

*/


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_create:
                //add implementation
                return true;
        }


        return super.onOptionsItemSelected(item);
    }


}


