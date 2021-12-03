package com.example.evirn_sci_survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.evirn_sci_survey.database.QuestionDAO;
import com.example.evirn_sci_survey.database.SurveyQuestion;
import com.example.evirn_sci_survey.database.SurveyQuestionAnswer;
import com.example.evirn_sci_survey.database.SurveyQuestionAnswerDao;
import com.example.evirn_sci_survey.database.SurveyQuestionDao;
import com.example.evirn_sci_survey.database.SurveyRoomDatabase;
import com.example.evirn_sci_survey.editor.AnswerListAdapter;
import com.example.evirn_sci_survey.editor.EditList;
import com.example.evirn_sci_survey.editor.EditListItem;
import com.example.evirn_sci_survey.editor.ListAdapter;

import java.util.ArrayList;

public class QuestionDisplay extends AppCompatActivity implements EditList {
    public static final String EXTRA_QUESTION_ORDER = "evirn_sci_survey.EXTRA_QUESTION_ORDER";
    public static final String EXTRA_SURVEY_ID = "evirn_sci_survey.EXTRA_SURVEY_ID";

    private SurveyQuestionDao questionDAO;
    private SurveyQuestionAnswerDao questionAnswerDAO;

    ListView mListView;
    TextView questionLbl;
    Button nextBtn;

    SurveyQuestion question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_display);

        int questionOrder = getIntent().getIntExtra(EXTRA_QUESTION_ORDER, -1);
        int surveyId = getIntent().getIntExtra(EXTRA_SURVEY_ID, -1);

        questionDAO = SurveyRoomDatabase.getDatabase(getApplication()).surveyQuestionDao();
        questionAnswerDAO = SurveyRoomDatabase.getDatabase(getApplication()).surveyQuestionAnswerDao();
        question = questionDAO.getQuestionFromOrder(surveyId, questionOrder);

        if(question == null) {
            returnToMain();
        } else {
            questionLbl = findViewById(R.id.question_lbl);
            mListView = findViewById(R.id.answer_choices);
            nextBtn = findViewById(R.id.nextBtn);

            questionLbl.setText(question.getQuestionText());

            refreshDisplay();

            nextBtn.setOnClickListener(v -> {
                Intent intent = getIntent(QuestionDisplay.this, questionOrder+1, surveyId);
                startActivity(intent);
            });
        }
    }

    public void refreshDisplay() {
        ArrayList<SurveyQuestionAnswer> itemList = new ArrayList<>(questionAnswerDAO.getAnswersInQuestion(question.getMsurveyId(), question.getQuestionId()));
        AnswerListAdapter adapter = new AnswerListAdapter(this, itemList);
        mListView.setAdapter(adapter);
    }

    private void returnToMain() {
        Intent intent = AdminActivity.getIntent(QuestionDisplay.this);
        startActivity(intent);
    }

    public static Intent getIntent(Context packageContext, int questionOrder, int surveyId) {
        Intent intent = new Intent(packageContext, QuestionDisplay.class);
        intent.putExtra(EXTRA_QUESTION_ORDER, questionOrder);
        intent.putExtra(EXTRA_SURVEY_ID, surveyId);
        return intent;
    }
}