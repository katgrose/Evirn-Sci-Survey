package com.example.evirn_sci_survey;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.Settings;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.evirn_sci_survey.database.AnswerDao;
import com.example.evirn_sci_survey.database.SurveyQuestionAnswerDao;
import com.example.evirn_sci_survey.database.SurveyQuestionDao;
import com.example.evirn_sci_survey.database.SurveyRoomDatabase;
import com.example.evirn_sci_survey.editor.EditQuestions;
import com.example.evirn_sci_survey.editor.SurveyListActivity;

public class AdminActivity extends AppCompatActivity {
    Button logoutBtn, startSurveyBtn, viewResultsBtn, surveyListBtn;

    AnswerDao answerDao;
    SurveyQuestionDao questionDao;
    SurveyQuestionAnswerDao questionAnswerDao;

    @RequiresApi(api = Build.VERSION_CODES.R)
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

            answerDao = SurveyRoomDatabase.getDatabase(getApplication()).answerDao();
            questionDao = SurveyRoomDatabase.getDatabase(getApplication()).surveyQuestionDao();
            questionAnswerDao = SurveyRoomDatabase.getDatabase(getApplication()).surveyQuestionAnswerDao();

            logoutBtn.setOnClickListener(v -> {
                logout(sharedPref);
            });

            startSurveyBtn.setOnClickListener(v -> {
                // Load question display
                Intent intent = QuestionDisplay.getIntent(AdminActivity.this, 1, activeSurvey, false);
                startActivity(intent);
            });

            viewResultsBtn.setOnClickListener(v -> {
                // Export Results here
                ActivityCompat.requestPermissions(AdminActivity.this, new String[] {Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.READ_EXTERNAL_STORAGE},
                        PackageManager.PERMISSION_GRANTED);
                if(!Environment.isExternalStorageManager()) {
                    Intent intent = new Intent(Settings.ACTION_MANAGE_ALL_FILES_ACCESS_PERMISSION);
                    startActivity(intent);
                    return;
                }
                String result = ExcelExporter.export(questionDao, questionAnswerDao, answerDao, activeSurvey);
                Toast.makeText(this,  result, Toast.LENGTH_SHORT).show();
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
