package com.example.evirn_sci_survey.editor;

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

import com.example.evirn_sci_survey.R;
import com.example.evirn_sci_survey.editor.EditList;
import com.example.evirn_sci_survey.editor.EditListItem;

import java.util.ArrayList;

public class ListAdapter extends ArrayAdapter<EditListItem> {
    public ListAdapter(Context context, ArrayList<EditListItem> arrayList) { super(context, 0, arrayList); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
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
                builder.setTitle("Are you sure you want to delete the " + currentListItem.getClass().getSimpleName() + "?");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        currentListItem.DeleteSelf(getContext());
                        Toast.makeText(getContext(),  currentListItem.getClass().getSimpleName() + " successfully removed", Toast.LENGTH_SHORT).show();
                        ((EditList)getContext()).refreshDisplay();
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
