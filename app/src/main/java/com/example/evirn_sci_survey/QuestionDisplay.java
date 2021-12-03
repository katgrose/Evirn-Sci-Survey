package com.example.evirn_sci_survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.evirn_sci_survey.database.QuestionDAO;
import com.example.evirn_sci_survey.database.SurveyQuestion;
import com.example.evirn_sci_survey.database.SurveyQuestionDao;
import com.example.evirn_sci_survey.database.SurveyRoomDatabase;

public class QuestionDisplay extends AppCompatActivity {
    public static final String EXTRA_QUESTION_ORDER = "evirn_sci_survey.EXTRA_QUESTION_ORDER";
    public static final String EXTRA_SURVEY_ID = "evirn_sci_survey.EXTRA_SURVEY_ID";
    QuestionDAO questionDAO;

    TextView questionLbl;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_display);

        int questionOrder = getIntent().getIntExtra(EXTRA_QUESTION_ORDER, -1);
        int surveyId = getIntent().getIntExtra(EXTRA_SURVEY_ID, -1);
        SurveyQuestionDao questionDAO = SurveyRoomDatabase.getDatabase(getApplication()).surveyQuestionDao();
        SurveyQuestion question = questionDAO.getQuestionFromOrder(surveyId, questionOrder);

        if(question == null) {
            returnToMain();
        } else {
            questionLbl = findViewById(R.id.question_lbl);
            questionLbl.setText(question.getQuestionText());

            nextBtn = findViewById(R.id.nextBtn);
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = getIntent(QuestionDisplay.this, questionOrder+1, surveyId);
                    startActivity(intent);
                }
            });
        }
    }

    private void returnToMain() {
        Intent intent = MainActivity.getIntent(QuestionDisplay.this);
        startActivity(intent);
    }

    public static Intent getIntent(Context packageContext, int questionOrder, int surveyId) {
        Intent intent = new Intent(packageContext, QuestionDisplay.class);
        intent.putExtra(EXTRA_QUESTION_ORDER, questionOrder);
        intent.putExtra(EXTRA_SURVEY_ID, surveyId);
        return intent;
    }
}