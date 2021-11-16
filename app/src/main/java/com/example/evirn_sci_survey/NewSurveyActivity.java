package com.example.evirn_sci_survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.widget.Button;
import android.widget.EditText;

public class NewSurveyActivity extends AppCompatActivity {
    public static String EXTRA_DESCRIPTION = "com.example.evirn_sci_survey.EXTRA_DESCRIPTION";
    public static String EXTRA_START = "com.example.evirn_sci_survey.EXTRA_START";
    public static String EXTRA_END = "com.example.evirn_sci_survey.EXTRA_END";
    private EditText mEditSurveySurveyView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_survey);
        mEditSurveySurveyView = findViewById(R.id.edit_survey);

        final Button button = findViewById(R.id.button_save);
        button.setOnClickListener(view -> {
//          Intent replyIntent = new Intent();
//          if(TextUtils.isEmpty(mEditSurveySurveyView.getText())){
//              setResult(RESULT_CANCELED, replyIntent);
//          } else {
//              int surveyId = mEditSurveySurveyView.getId();
//              String description = mEditSurveySurveyView.getText().toString();
//              String startDate = mEditSurveySurveyView.getText().toString();
//              String endDate = mEditSurveySurveyView.getText().toString();
//
//              replyIntent.putExtra(EXTRA_SURVEYID, surveyId);
//              replyIntent.putExtra(EXTRA_DESCRIPTION, description);
//              replyIntent.putExtra(EXTRA_START, startDate);
//              replyIntent.putExtra(EXTRA_END, endDate);
//
//              setResult(RESULT_OK);
//          }
          finish();
        });
    }
}