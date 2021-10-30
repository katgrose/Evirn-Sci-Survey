package com.example.evirn_sci_survey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.evirn_sci_survey.database.AppDatabase;
import com.example.evirn_sci_survey.database.QuestionDAO;
import com.example.evirn_sci_survey.editor.EditQuestions;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button startSurveyBtn, editQuestionsBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        QuestionDAO questionDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME).allowMainThreadQueries().build().getQuestionDAO();

        // Create Predefined Questions
        List<Question> questionList = questionDAO.getQuestions();
        if(questionList.size() == 0) {
            Question q1 = new Question("This is test question 1...", 1);
            Question q2 = new Question("This is test question 2...", 2);
            Question q3 = new Question("This is test question 3...", 3);
            questionDAO.Insert(q1, q2, q3);
        }

        startSurveyBtn = findViewById(R.id.start_survey_btn);
        editQuestionsBtn = findViewById(R.id.edit_question_btn);

        startSurveyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Load question display
                Intent intent = QuestionDisplay.getIntent(MainActivity.this);
                intent.putExtra(getString(R.string.saved_question_order), 1); // 1: first question order
                startActivity(intent);
            }
        });

        editQuestionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = EditQuestions.getIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }

    public static Intent getIntent(Context packageContext) {
        return new Intent(packageContext, MainActivity.class);
    }
}