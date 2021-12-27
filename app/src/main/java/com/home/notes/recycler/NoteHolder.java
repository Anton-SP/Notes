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
    private Note note;


    public NoteHolder(@NonNull View itemView, NoteAdapter.OnNoteClickListener listener) {
        super(itemView);
        title = itemView.findViewById(R.id.note_title);
        description = itemView.findViewById(R.id.note_description);
       itemView.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               listener.onNoteClick(note);
           }
       });

    }

    void bind(Note note) {
        this.note = note;
        title.setText(note.getTitle());
        description.setText(note.getDescription());
    }

}