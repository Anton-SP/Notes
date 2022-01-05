package com.home.notes.fragments;

import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.service.controls.templates.ControlButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.home.notes.R;
import com.home.notes.data.Constans;
import com.home.notes.data.InMemoryRepoImp;
import com.home.notes.data.Note;
import com.home.notes.data.Repo;
import com.home.notes.recycler.NoteAdapter;

public class NoteListFragment extends Fragment implements NoteAdapter.OnNoteClickListener {

    private Repo repository = InMemoryRepoImp.getInstance();

    private RecyclerView list;
    private NoteAdapter adapter;


    public NoteListFragment() {
        // Required empty public constructor
    }


    public static NoteListFragment newInstance(Note note) {
        NoteListFragment fragment = new NoteListFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constans.NOTE, note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(Constans.TAG, "onCreate()NOTE LISTTTTTTTT  ");
///////////////////////////////////

       requireActivity().getSupportFragmentManager().setFragmentResultListener(Constans.REQUEST_KEY, this, new FragmentResultListener() {
     //   getParentFragmentManager().setFragmentResultListener(Constans.REQUEST_KEY, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Note resultNote = (Note) result.getSerializable(Constans.NOTE);
                if (resultNote.getId() != -1) {
                    Log.d(Constans.TAG, "onFragmentResult() UPDATE ITEM!");
                    repository.update(resultNote);
                    adapter.notifyItemChanged(resultNote.getId());
                } else {
                    Log.d(Constans.TAG, "onFragmentResult() NEWWW ITEM!");
                    repository.create(resultNote);
                    adapter.notifyItemInserted(resultNote.getId());
                }

            }
        });

    }


    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_list, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (((InMemoryRepoImp) repository).getCounter() == 0) {
            fillRepo();
        }


        adapter = new NoteAdapter();
        adapter.setNotes(repository.getAll());

        adapter.setOnNoteClickListener(this::onNoteClick);

        list = view.findViewById(R.id.recycler_note_list);
        list.setLayoutManager(new LinearLayoutManager(this.getContext()));
        list.setAdapter(adapter);
    }

    private void fillRepo() {
        repository.create(new Note("Title1", "Description 1", "Normal","11/11/22"));
        repository.create(new Note("Title2", "Description 2", "Normal","11/11/22"));
        repository.create(new Note("Title3", "Description 3", "Normal","11/11/22"));
        repository.create(new Note("Title4", "Description 4", "Normal","11/11/22"));
        repository.create(new Note("Title5", "Description 5", "Normal","11/11/22"));
        repository.create(new Note("Title6", "Description 6", "Normal","11/11/22"));
        repository.create(new Note("Title7", "Description 7", "Normal","11/11/22"));
        repository.create(new Note("Title8", "Description 8", "Normal","11/11/22"));
        repository.create(new Note("Title9", "Description 9", "Normal","11/11/22"));
        repository.create(new Note("Title10", "Description 10", "Normal","11/11/22"));
        repository.create(new Note("Title11", "Description 11", "Normal","11/11/22"));
        repository.create(new Note("Title12", "Description 12", "Normal","11/11/22"));
        repository.create(new Note("Title13", "Description 13", "Normal","11/11/22"));
        repository.create(new Note("Title14", "Description 14", "Normal","11/11/22"));
        repository.create(new Note("Title15", "Description 15", "Normal","11/11/22"));

    }

    @Override
    public void onNoteClick(Note note) {
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.list_fragment_holder, EditNoteFragment.newInstance(note))
                    .addToBackStack(null)
                    .commit();
        } else {

            requireActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.detail_fragment_holder, EditNoteFragment.newInstance(note))
                    //     .addToBackStack(null)
                    .commit();

        }


    }


}

