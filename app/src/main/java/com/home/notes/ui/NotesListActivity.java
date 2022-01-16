package com.home.notes.ui;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.res.Configuration;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.home.notes.R;

import com.home.notes.data.Constans;
import com.home.notes.data.Note;
import com.home.notes.dialogs.ConfirmExitDialog;
import com.home.notes.dialogs.NoteDialog;
import com.home.notes.fragments.CreateNoteFragment;

import com.home.notes.fragments.NoteListFragment;

public class NotesListActivity extends AppCompatActivity implements NoteDialog.NoteDialogController {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_list);
        getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.list_fragment_holder, new NoteListFragment())
                .commit();
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

                if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
                    NoteDialog.getInstance(null).show(getSupportFragmentManager(), Constans.DIALOG_NOTE);
                    return true;
                } else {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.detail_fragment_holder, new CreateNoteFragment(), Constans.NOTE_LIST_FRAGMENT)
                            .addToBackStack(null)
                            .commit();
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        for (Fragment f : getSupportFragmentManager().getFragments()) {
            if (f.isVisible()) {

                FragmentManager childFm = f.getChildFragmentManager();
                if (childFm.getBackStackEntryCount() > 0) {
                    childFm.popBackStack();
                    return;
                }


            }

        }

        if (getSupportFragmentManager().getBackStackEntryCount()==0)
        {
            new ConfirmExitDialog().show(getSupportFragmentManager(),Constans.DIALOG_EXIT);
        } else
        {
            super.onBackPressed();
        }


    }

    @Override
    public void update(Note note) {
        Bundle result = new Bundle();
        result.putSerializable(Constans.NOTE, note);
        getSupportFragmentManager().setFragmentResult(Constans.REQUEST_KEY, result);
    }

    @Override
    public void create(String title, String description, String importance, String date) {
        Bundle result = new Bundle();
        Note createdNote = new Note(-1, title, description, importance, date);
        result.putSerializable(Constans.NOTE, createdNote);
        getSupportFragmentManager().setFragmentResult(Constans.REQUEST_KEY, result);
    }
}


