package com.example.evirn_sci_survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.evirn_sci_survey.editor.EditQuestions;
import com.example.evirn_sci_survey.editor.SurveyListActivity;

public class MainActivity extends AppCompatActivity {
    Button startSurveyBtn, editQuestionsBtn, surveyListBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.saved_prefs_location), Context.MODE_PRIVATE);
        int activeSurvey = sharedPref.getInt(getString(R.string.saved_active_survey_key), 1);

        startSurveyBtn = findViewById(R.id.start_survey_btn);
        editQuestionsBtn = findViewById(R.id.edit_question_btn);
        surveyListBtn = findViewById(R.id.survey_list_btn);

        startSurveyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Load question display
                Intent intent = QuestionDisplay.getIntent(MainActivity.this, 1, activeSurvey);
                intent.putExtra(getString(R.string.saved_question_order), 1); // 1: first question order
                startActivity(intent);
            }
        });

        editQuestionsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = EditQuestions.getIntent(MainActivity.this, activeSurvey);
                startActivity(intent);
            }
        });

        surveyListBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = SurveyListActivity.getIntent(MainActivity.this);
                startActivity(intent);
            }
        });
    }

    public static Intent getIntent(Context packageContext) {
        return new Intent(packageContext, MainActivity.class);
    }
}
