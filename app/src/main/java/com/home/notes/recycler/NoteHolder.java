package com.home.notes.recycler;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.home.notes.R;
import com.home.notes.data.Note;

public class NoteHolder extends RecyclerView.ViewHolder {

    private TextView title;
    private TextView description;


    public NoteHolder(@NonNull View itemView) {
        super(itemView);
        title = itemView.findViewById(R.id.note_title);
        description = itemView.findViewById(R.id.note_description);
    }

    void bind(Note note) {
        title.setText(note.getTitle());
        description.setText(note.getDescription());
    }

}
