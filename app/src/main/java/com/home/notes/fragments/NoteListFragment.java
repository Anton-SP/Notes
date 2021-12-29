package com.home.notes.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentResultListener;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
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


    // TODO: Rename and change types and number of parameters
    public static NoteListFragment newInstance(Note note) {
        NoteListFragment fragment = new NoteListFragment();
        Bundle args = new Bundle();
        args.putSerializable(Constans.NOTE,note);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getParentFragmentManager().setFragmentResultListener(Constans.REQUEST_KEY, this, new FragmentResultListener() {
            @Override
            public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle result) {
                Note resultNote  = (Note) result.getSerializable(Constans.NOTE);
                repository.update(resultNote);
                adapter.notifyItemChanged(resultNote.getId());
            }
        });



    }

/*
@Override
public void onCreate(@Nullable Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    getParentFragmentManager().setFragmentResultListener("requestKey", this, new FragmentResultListener() {
        @Override
        public void onFragmentResult(@NonNull String requestKey, @NonNull Bundle bundle) {
            // We use a String here, but any type that can be put in a Bundle is supported
            String result = bundle.getString("bundleKey");
            // Do something with the result
        }
    });
}



*/

    @Override

    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_note_list, container, false);


    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fillRepo();



        adapter = new NoteAdapter();
        adapter.setNotes(repository.getAll());

        adapter.setOnNoteClickListener(this::onNoteClick);




        list = view.findViewById(R.id.recycler_note_list);
        list.setLayoutManager(new LinearLayoutManager(this.getContext()));
        list.setAdapter(adapter);
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
        requireActivity().getSupportFragmentManager()
                .beginTransaction()
                .add(R.id.list_fragment_holder, EditNoteFragment.newInstance(note))
                .addToBackStack(null)
                .commit();
    }
}