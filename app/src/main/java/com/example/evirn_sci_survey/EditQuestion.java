package com.example.evirn_sci_survey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evirn_sci_survey.database.AppDatabase;
import com.example.evirn_sci_survey.database.QuestionDAO;

public class EditQuestion extends AppCompatActivity {
    private static final String QUESTION_ID = "SURVEY.QUESTION_ID";

    private TextView mQuestionText;

    private QuestionDAO questionDAO;

    private Question question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_question);

        questionDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME).allowMainThreadQueries().build().getQuestionDAO();
        int questionId = getIntent().getIntExtra(QUESTION_ID, -1);
        question = questionDAO.getQuestionFromId(questionId);

        if(question == null) { returnToQuestionList(); }
        else {
            mQuestionText = findViewById(R.id.question_text);
            Button mSaveChangesButton = findViewById(R.id.saveBtn);
            Button mCancelButton = findViewById(R.id.cancelBtn);

            mQuestionText.setText(question.getQuestionText());

            mSaveChangesButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    question.setQuestionText(mQuestionText.getText().toString());
                    questionDAO.Update(question);
                    Toast.makeText(EditQuestion.this,"Question updated successfully", Toast.LENGTH_SHORT).show();
                    returnToQuestionList();
                }
            });

            mCancelButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    returnToQuestionList();
                }
            });
        }
    }

    public void returnToQuestionList() {
        Intent intent = EditQuestions.getIntent(EditQuestion.this);
        startActivity(intent);
    }

    public static Intent getIntent(Context packageContext, int questionId) {
        Intent intent = new Intent(packageContext, EditQuestion.class);
        intent.putExtra(QUESTION_ID, questionId);
        return intent;
    }
}