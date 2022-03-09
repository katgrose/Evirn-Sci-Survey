package com.example.evirn_sci_survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evirn_sci_survey.database.Answer;
import com.example.evirn_sci_survey.database.AnswerDao;
import com.example.evirn_sci_survey.database.SurveyQuestion;
import com.example.evirn_sci_survey.database.SurveyQuestionAnswer;
import com.example.evirn_sci_survey.database.SurveyQuestionAnswerDao;
import com.example.evirn_sci_survey.database.SurveyQuestionDao;
import com.example.evirn_sci_survey.database.SurveyRoomDatabase;
import com.example.evirn_sci_survey.editor.AnswerListAdapter;
import com.example.evirn_sci_survey.editor.EditList;
import com.example.evirn_sci_survey.unused.Tools_page;

import java.util.ArrayList;
import java.util.List;

public class QuestionDisplay extends AppCompatActivity implements EditList {
    public static final String EXTRA_QUESTION_ORDER = "evirn_sci_survey.EXTRA_QUESTION_ORDER";
    public static final String EXTRA_SURVEY_ID = "evirn_sci_survey.EXTRA_SURVEY_ID";
    public static final String EXTRA_IS_MAIN_ORIGIN = "evirn_sci_survey.EXTRA_IS_MAIN_ORIGIN";

    private SurveyQuestionDao questionDAO;
    private SurveyQuestionAnswerDao questionAnswerDAO;
    private AnswerDao answerDao;

    ListView mListView;
    TextView questionLbl;
    Button nextBtn;
    Button backBtn;
    AnswerListAdapter adapter;

    SurveyQuestion question;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_display);

        int questionOrder = getIntent().getIntExtra(EXTRA_QUESTION_ORDER, -1);
        int surveyId = getIntent().getIntExtra(EXTRA_SURVEY_ID, -1);
        boolean isMainOrigin = getIntent().getBooleanExtra(EXTRA_IS_MAIN_ORIGIN, true);

        questionDAO = SurveyRoomDatabase.getDatabase(getApplication()).surveyQuestionDao();
        questionAnswerDAO = SurveyRoomDatabase.getDatabase(getApplication()).surveyQuestionAnswerDao();
        answerDao = SurveyRoomDatabase.getDatabase(getApplication()).answerDao();
        question = questionDAO.getQuestionFromOrder(surveyId, questionOrder);

        if(question == null) {
            if(isMainOrigin) {
                Intent intent = Enjoyment_level.getIntent(QuestionDisplay.this, surveyId);
                startActivity(intent);
            } else {
                returnToAdmin();
            }
        } else {
            questionLbl = findViewById(R.id.question_lbl);
            mListView = findViewById(R.id.answer_choices);
            nextBtn = findViewById(R.id.nextBtn);
            backBtn = findViewById(R.id.backBtn);

            questionLbl.setText(question.getQuestionText());

            refreshDisplay();

            nextBtn.setOnClickListener(v -> {
                List<SurveyQuestionAnswer> offeredAnswers = questionAnswerDAO.getAnswersInQuestion(surveyId, question.getQuestionId());
                for(int i = 0; i < offeredAnswers.size(); i++) {
                    SurveyQuestionAnswer offeredAnswer = offeredAnswers.get(i);
                    Answer answer = new Answer(offeredAnswer.getMofferedAnsId(), surveyId, question.getQuestionId(), adapter.textBox.getText().toString());
                    answer.setSliderValue(adapter.slider.getProgress());
                    boolean checked = adapter.checkBox.isChecked();
                    answer.setCheckboxValue(checked);
                    answerDao.insert(answer);
                }
                Intent intent = getIntent(QuestionDisplay.this, questionOrder+1, surveyId, isMainOrigin);
                startActivity(intent);
            });

            backBtn.setOnClickListener(v -> {
                List<SurveyQuestionAnswer> offeredAnswers = questionAnswerDAO.getAnswersInQuestion(surveyId, question.getQuestionId());
                for(int i = 0; i < offeredAnswers.size(); i++) {
                    SurveyQuestionAnswer offeredAnswer = offeredAnswers.get(i);
                    Answer answer = new Answer(offeredAnswer.getMofferedAnsId(), surveyId, question.getQuestionId(), adapter.textBox.getText().toString());
                    answer.setSliderValue(adapter.slider.getProgress());
                    boolean checked = adapter.checkBox.isChecked();
                    answer.setCheckboxValue(checked);
                    answerDao.insert(answer);
                }
                if(questionOrder-1==0) {
                    Intent intent = new Intent(QuestionDisplay.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Intent intent = getIntent(QuestionDisplay.this, questionOrder - 1, surveyId, isMainOrigin);
                    startActivity(intent);
                }
            });
        }
    }

    public void refreshDisplay() {
        ArrayList<SurveyQuestionAnswer> itemList = new ArrayList<>(questionAnswerDAO.getAnswersInQuestion(question.getMsurveyId(), question.getQuestionId()));
        adapter = new AnswerListAdapter(this, itemList);
        mListView.setAdapter(adapter);

    }

    private void returnToAdmin() {
        Intent intent = AdminActivity.getIntent(QuestionDisplay.this);
        startActivity(intent);
    }

    public static Intent getIntent(Context packageContext, int questionOrder, int surveyId, boolean isMainOrigin) {
        Intent intent = new Intent(packageContext, QuestionDisplay.class);
        intent.putExtra(EXTRA_QUESTION_ORDER, questionOrder);
        intent.putExtra(EXTRA_SURVEY_ID, surveyId);
        intent.putExtra(EXTRA_IS_MAIN_ORIGIN, isMainOrigin);
        return intent;
    }
}