package com.home.notes.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.home.notes.R;
import com.home.notes.data.InMemoryRepoImp;
import com.home.notes.data.Note;
import com.home.notes.data.Repo;
import com.home.notes.recycler.NoteAdapter;

public class NotesListActivity extends AppCompatActivity {

    private Repo repository = new InMemoryRepoImp();
    private RecyclerView list;
    private NoteAdapter adapter;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);

        fillRepo();

        adapter = new NoteAdapter();
        adapter.setNotes(repository.getAll());
        
        
        list=findViewById(R.id.list);
        list.setLayoutManager(new LinearLayoutManager(this));
        list.setAdapter(adapter);
        
   
        

  }

    private void fillRepo() {
        repository.create(new Note("Title1","Description 1"));
        repository.create(new Note("Title2","Description 2"));
        repository.create(new Note("Title3","Description 3"));
        repository.create(new Note("Title4","Description 4"));
        repository.create(new Note("Title5","Description 5"));
        repository.create(new Note("Title6","Description 6"));
        repository.create(new Note("Title7","Description 7"));
        repository.create(new Note("Title8","Description 8"));
        repository.create(new Note("Title9","Description 9"));
        repository.create(new Note("Title10","Description 10"));
        repository.create(new Note("Title11","Description 11"));
        repository.create(new Note("Title12","Description 12"));
        repository.create(new Note("Title13","Description 13"));
        repository.create(new Note("Title14","Description 14"));
        repository.create(new Note("Title15","Description 15"));


    }
}