package com.example.evirn_sci_survey.editor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evirn_sci_survey.R;
import com.example.evirn_sci_survey.database.Survey;
import com.example.evirn_sci_survey.database.SurveyQuestionAnswerDao;
import com.example.evirn_sci_survey.database.SurveyQuestionDao;
import com.example.evirn_sci_survey.database.SurveyRoomDatabase;

public class EditSurvey extends AppCompatActivity {
    private static final String EXTRA_ID = "com.example.evirn_sci_survey.EXTRA_ID";
    public static String EXTRA_DESCRIPTION = "com.example.evirn_sci_survey.EXTRA_DESCRIPTION";
    public static String EXTRA_START = "com.example.evirn_sci_survey.EXTRA_START";
    public static String EXTRA_END = "com.example.evirn_sci_survey.EXTRA_END";

    private TextView mSurveyDescription, mSurveyStart, mSurveyEnd;
    private SurveyViewModel mSurveyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_survey);

        mSurveyDescription = findViewById(R.id.survey_description);
        mSurveyStart = findViewById(R.id.survey_start);
        mSurveyEnd = findViewById(R.id.survey_end);
        Button mSaveBtn = findViewById(R.id.saveBtn);
        Button mCancelButton = findViewById(R.id.cancelBtn);
        Button mEditQuestionsButton = findViewById(R.id.edit_questions_btn);
        Button mSetActiveButton = findViewById(R.id.set_active_btn);
        Button mIsActiveButton = findViewById(R.id.is_active);

        mSurveyDescription.setText(getIntent().getStringExtra(EXTRA_DESCRIPTION));
        mSurveyStart.setText(getIntent().getStringExtra(EXTRA_START));
        mSurveyEnd.setText(getIntent().getStringExtra(EXTRA_END));
        int surveyId = getIntent().getIntExtra(EXTRA_ID, -1);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.saved_prefs_location), Context.MODE_PRIVATE);
        int activeSurvey = sharedPref.getInt(getString(R.string.saved_active_survey_key), 1);
        updateButtonVisibility(activeSurvey, surveyId, mSetActiveButton, mIsActiveButton);

        mSurveyViewModel = new ViewModelProvider(this).get(SurveyViewModel.class);

        mSaveBtn.setOnClickListener(view -> {
            String description = mSurveyDescription.getText().toString();
            String startDate = mSurveyStart.getText().toString();
            String endDate = mSurveyEnd.getText().toString();

            SurveyQuestionDao questionDAO = SurveyRoomDatabase.getDatabase(getApplication()).surveyQuestionDao();
            SurveyQuestionAnswerDao questionAnswerDAO = SurveyRoomDatabase.getDatabase(getApplication()).surveyQuestionAnswerDao();
            Survey survey = new Survey(surveyId, description, startDate, endDate);
            survey.generateBasicQuestions(questionDAO, questionAnswerDAO);
            Toast.makeText(EditSurvey.this,"Survey updated successfully", Toast.LENGTH_SHORT).show();
            mSurveyViewModel.update(survey);
            returnToSurveyList();
        });

        mCancelButton.setOnClickListener(view -> returnToSurveyList());
        mEditQuestionsButton.setOnClickListener(view -> startActivity(EditQuestions.getIntent(EditSurvey.this, surveyId)));
        mSetActiveButton.setOnClickListener(view -> {
            SharedPreferences.Editor editor = sharedPref.edit();
            editor.putInt(getString(R.string.saved_active_survey_key), surveyId);
            editor.apply();
            startActivity(getIntent());
        });
    }

    public void returnToSurveyList() {
        Intent intent = SurveyListActivity.getIntent(EditSurvey.this);
        startActivity(intent);
    }

    public static Intent getIntent(Context packageContext, Survey survey) {
        Intent intent = new Intent(packageContext, EditSurvey.class);
        intent.putExtra(EXTRA_ID, survey.getMsurveyId());
        intent.putExtra(EXTRA_DESCRIPTION, survey.getMdescription());
        intent.putExtra(EXTRA_START, survey.getMstartDate());
        intent.putExtra(EXTRA_END, survey.getMendDate());
        return intent;
    }

    public void updateButtonVisibility(int activeSurvey, int surveyId, Button setActiveButton, Button isActiveButton) {
        if(activeSurvey == surveyId) {
            setActiveButton.setVisibility(View.GONE);
            isActiveButton.setVisibility(View.VISIBLE);
        } else {
            setActiveButton.setVisibility(View.VISIBLE);
            isActiveButton.setVisibility(View.GONE);
        }
    }
}