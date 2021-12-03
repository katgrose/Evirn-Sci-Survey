package com.example.evirn_sci_survey.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;

import com.example.evirn_sci_survey.AdminActivity;
import com.example.evirn_sci_survey.R;
import com.example.evirn_sci_survey.database.SurveyQuestion;
import com.example.evirn_sci_survey.database.SurveyQuestionDao;
import com.example.evirn_sci_survey.database.SurveyRoomDatabase;

import java.util.ArrayList;

public class EditQuestions extends AppCompatActivity implements EditList {
    public static final String EXTRA_SURVEY_ID = "evirn_sci_survey.EXTRA_SURVEY_ID";

    ListView mListView;
    Button mDoneBtn, mNewBtn;

    SurveyQuestionDao questionDAO;

    ListAdapter adapter;
    int surveyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_questions);

        surveyId = getIntent().getIntExtra(EXTRA_SURVEY_ID, -1);

        mListView = findViewById(R.id.question_list);
        mDoneBtn = findViewById(R.id.done_btn);
        mNewBtn = findViewById(R.id.new_btn);

        questionDAO = SurveyRoomDatabase.getDatabase(getApplication()).surveyQuestionDao();

        refreshDisplay();

        mDoneBtn.setOnClickListener(v -> {
            Intent intent = AdminActivity.getIntent(EditQuestions.this);
            startActivity(intent);
        });

        mNewBtn.setOnClickListener(v -> {
            SurveyQuestion q = new SurveyQuestion(surveyId,"New Question", questionDAO.getRowCount(surveyId)+1);
            questionDAO.Insert(q);
            refreshDisplay();
        });
    }

    public void refreshDisplay() {
        ArrayList<EditListItem> itemList = new ArrayList<>(questionDAO.getQuestionsInSurvey(surveyId));
        adapter = new ListAdapter(this, itemList);
        mListView.setAdapter(adapter);
    }

    public static Intent getIntent(Context packageContext, int surveyId) {
        Intent intent = new Intent(packageContext, EditQuestions.class);
        intent.putExtra(EXTRA_SURVEY_ID, surveyId);
        return intent;
    }
}