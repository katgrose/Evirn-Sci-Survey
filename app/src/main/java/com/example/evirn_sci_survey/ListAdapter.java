package com.example.evirn_sci_survey;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import com.example.evirn_sci_survey.editor.EditQuestions;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<EditListItem> {
    public ListAdapter(Context context, ArrayList<EditListItem> arrayList) { super(context, 0, arrayList); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // TODO: Switch view to edit_list_layout
        View currentView = convertView;

        if (currentView == null) {
            currentView = LayoutInflater.from(getContext()).inflate(R.layout.list_question_layout, parent, false);
        }

        EditListItem currentListItem = getItem(position);
        String text = currentListItem.getListItemText();

        TextView questionTextView = currentView.findViewById(R.id.question_lbl);
        questionTextView.setText(text);

        Button editButton = currentView.findViewById(R.id.editBtn);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getContext().startActivity(currentListItem.getListItemEditIntent(getContext()));
            }
        });

        Button deleteButton = currentView.findViewById(R.id.deleteBtn);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Are you sure you want to delete the " + currentListItem.getClass() + "?");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        currentListItem.DeleteSelf(getContext());
                        Toast.makeText(getContext(), currentListItem.getClass() + " successfully removed", Toast.LENGTH_SHORT).show();
                        ((EditQuestions)getContext()).refreshDisplay();
                        // TODO: update to use EditList class
                    }
                });

                builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                builder.show();
            }
        });

        return currentView;
    }
}
