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

import com.home.notes.fragments.CreateNoteFragment;

import com.home.notes.fragments.NoteListFragment;

public class NotesListActivity extends AppCompatActivity {


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
                    getSupportFragmentManager()
                            .beginTransaction()
                            .add(R.id.list_fragment_holder, new CreateNoteFragment())
                            .addToBackStack(null)
                            .commit();
                    return true;
                } else {
                    getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.detail_fragment_holder, new CreateNoteFragment())
                            .addToBackStack(null)
                            .commit();
                    return true;
                }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {

        for (Fragment f: getSupportFragmentManager().getFragments())
        {
            if (f.isVisible())
            {
                FragmentManager childFm = f.getChildFragmentManager();
                if (childFm.getBackStackEntryCount()>0)
                {
                    childFm.popBackStack();
                    return;
                }
            }

        }


        super.onBackPressed();
    }
}


