package com.home.notes.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputLayout;
import com.home.notes.R;
import com.home.notes.data.Constans;
import com.home.notes.data.Note;

import java.util.Date;

public class NoteDialog extends DialogFragment {

    public interface NoteDialogController {
        void update(Note note);
        void create(String title, String description, String importance, String date);
    }

    private NoteDialogController controller;

    private Note note;

    @Override
    public void onAttach(@NonNull Context context) {

        controller = (NoteDialogController) context;
        super.onAttach(context);
    }

    public static NoteDialog getInstance(Note note)
    {
        NoteDialog dialog = new NoteDialog();
        Bundle args = new Bundle();
        args.putSerializable(Constans.DIALOG_NOTE,note);
        dialog.setArguments(args);
        return dialog;
    }


    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {


        Bundle args = getArguments();
        note = (Note) args.getSerializable(Constans.DIALOG_NOTE);
        View dialog = LayoutInflater.from(requireContext()).inflate(R.layout.note_dialog,null);

        TextInputLayout dialogTitle = dialog.findViewById(R.id.note_dialog_title);
        TextInputLayout dialogDescription=dialog.findViewById(R.id.note_dialog_description);
        DatePicker datePicker = dialog.findViewById(R.id.dialog_date_picker);


        MaterialButton cancel = dialog.findViewById(R.id.dialog_cancel_button);
        MaterialButton confirm = dialog.findViewById(R.id.dialog_confirm_button);


        if (note != null)
        {
            confirm.setText(R.string.update_button);
            dialogTitle.getEditText().setText(note.getTitle());
            dialogDescription.getEditText().setText(note.getDescription());

          //TODO deal with data  datePicker.updateDate();
        } else
        {
            confirm.setText(R.string.create_button);
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
        String buttonText;
        if (note == null)
        {
            //TODO string
            builder.setTitle("Create note");
            buttonText = "Create note";
        } else
        {
            builder.setTitle("Update note");
            buttonText = "Update note";
        }
        builder.setView(dialog)
                .setCancelable(true)
                .setNegativeButton("Cancel", (dialog1, which) -> dialog1.cancel())
                .setPositiveButton(buttonText, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                          if (note ==null)
                          {
                              controller.create(
                                      dialogTitle.getEditText().getText().toString(),
                                      dialogDescription.getEditText().getText().toString(),
                                      "very hight",
                                      "01.01.2032"
                              );
                          }
                          else
                          {
                              note.setTitle(dialogTitle.getEditText().getText().toString());
                              note.setDescription(dialogDescription.getEditText().getText().toString());
                              note.setImportance("very hight");
                              note.setDate("01.01.2032");
                              controller.update(note);
                          }
                          dialog.dismiss();
                    }

                })
        ;


        return builder.create();
    }
}
