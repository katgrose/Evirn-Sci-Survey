package com.example.evirn_sci_survey;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.room.Room;

import com.example.evirn_sci_survey.database.AppDatabase;
import com.example.evirn_sci_survey.database.QuestionDAO;
import com.example.evirn_sci_survey.editor.EditQuestion;
import com.example.evirn_sci_survey.editor.EditQuestions;

import java.util.ArrayList;
import java.util.List;

public class QuestionAdapter extends ArrayAdapter<Question> {
    public QuestionAdapter(Context context, ArrayList<Question> arrayList) { super(context, 0, arrayList); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View currentView = convertView;

        if (currentView == null) {
            currentView = LayoutInflater.from(getContext()).inflate(R.layout.list_question_layout, parent, false);
        }

        Question currentQuestion = getItem(position);
        String text = currentQuestion.getQuestionOrder() + ": " + currentQuestion.getQuestionText();

        TextView questionTextView = currentView.findViewById(R.id.question_lbl);
        questionTextView.setText(text);

        Button editButton = currentView.findViewById(R.id.editBtn);
        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = EditQuestion.getIntent(getContext(), currentQuestion.getQuestionId());
                getContext().startActivity(intent);
            }
        });

        Button deleteButton = currentView.findViewById(R.id.deleteBtn);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("Are you sure you want to delete question " + currentQuestion.getQuestionOrder() + "?");

                builder.setPositiveButton("Delete", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        QuestionDAO questionDAO = Room.databaseBuilder(getContext(), AppDatabase.class, AppDatabase.DB_NAME).allowMainThreadQueries().build().getQuestionDAO();
                        questionDAO.Delete(currentQuestion);
                        // Update order for all other questions
                        List<Question> questionList = questionDAO.getQuestions();
                        for(int i = 0; i < questionList.size(); i++) {
                            questionList.get(i).setQuestionOrder(i + 1);
                            questionDAO.Update(questionList.get(i));
                        }
                        Toast.makeText(getContext(), "Question successfully removed", Toast.LENGTH_SHORT).show();
                        ((EditQuestions)getContext()).refreshDisplay();
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
