package com.example.evirn_sci_survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.evirn_sci_survey.editor.EditQuestions;
import com.example.evirn_sci_survey.editor.SurveyListActivity;

public class AdminActivity extends AppCompatActivity {
    Button logoutBtn, startSurveyBtn, viewResultsBtn, surveyListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.saved_prefs_location), Context.MODE_PRIVATE);
        int activeSurvey = sharedPref.getInt(getString(R.string.saved_active_survey_key), 1);
        boolean loggedIn = sharedPref.getBoolean(getString(R.string.saved_logged_in_key), false);

        if(!loggedIn) {
            logout(sharedPref);
        } else {
            logoutBtn = findViewById(R.id.logout);
            startSurveyBtn = findViewById(R.id.start_survey_btn);
            viewResultsBtn = findViewById(R.id.view_results_btn);
            surveyListBtn = findViewById(R.id.survey_list_btn);

            logoutBtn.setOnClickListener(v -> {
                logout(sharedPref);
            });

            startSurveyBtn.setOnClickListener(v -> {
                // Load question display
                Intent intent = QuestionDisplay.getIntent(AdminActivity.this, 1, activeSurvey);
                intent.putExtra(getString(R.string.saved_question_order), 1); // 1: first question order
                startActivity(intent);
            });

            viewResultsBtn.setOnClickListener(v -> {
                // View Results here
                // Intent intent = ViewResults.getIntent(AdminActivity.this, activeSurvey);
                // startActivity(intent);
            });

            surveyListBtn.setOnClickListener(v -> {
                Intent intent = SurveyListActivity.getIntent(AdminActivity.this);
                startActivity(intent);
            });
        }
    }

    public static Intent getIntent(Context packageContext) {
        return new Intent(packageContext, AdminActivity.class);
    }

    private void logout(SharedPreferences sharedPref) {
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putBoolean(getString(R.string.saved_logged_in_key), false);
        editor.apply();
        Intent intent = MainActivity.getIntent(AdminActivity.this);
        finishAffinity();
        startActivity(intent);
    }
}
