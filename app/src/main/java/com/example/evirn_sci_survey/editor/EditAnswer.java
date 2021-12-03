package com.example.evirn_sci_survey.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evirn_sci_survey.MainActivity;
import com.example.evirn_sci_survey.R;
import com.example.evirn_sci_survey.database.SurveyQuestionAnswer;
import com.example.evirn_sci_survey.database.SurveyQuestionAnswerDao;
import com.example.evirn_sci_survey.database.SurveyRoomDatabase;

public class EditAnswer extends AppCompatActivity {
    private static final String EXTRA_ANSWER_ID = "SURVEY.EXTRA_ANSWER_ID";

    private SurveyQuestionAnswerDao questionAnswerDAO;

    private TextView mAnswerText;

    private SurveyQuestionAnswer answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_answer);
        questionAnswerDAO = SurveyRoomDatabase.getDatabase(getApplication()).surveyQuestionAnswerDao();
        int answerId = getIntent().getIntExtra(EXTRA_ANSWER_ID, -1);
        answer = questionAnswerDAO.getAnswerFromId(answerId);

        if(answer == null) { startActivity(MainActivity.getIntent(EditAnswer.this)); }
        else {
            mAnswerText = findViewById(R.id.answer_text);
            Button mSaveChangesButton = findViewById(R.id.saveBtn);
            Button mCancelButton = findViewById(R.id.cancelBtn);

            mAnswerText.setText(answer.getMofferedAnsText());

            mSaveChangesButton.setOnClickListener(v -> {
                answer.setMofferedAnsText(mAnswerText.getText().toString());
                questionAnswerDAO.update(answer);
                Toast.makeText(EditAnswer.this,"Answer updated successfully", Toast.LENGTH_SHORT).show();
                returnToEditQuestion();
            });

            mCancelButton.setOnClickListener(view -> returnToEditQuestion());
        }
    }

    public void returnToEditQuestion() {
        Intent intent = EditQuestion.getIntent(EditAnswer.this, answer.getMquestionId());
        startActivity(intent);
    }

    public static Intent getIntent(Context packageContext, int answerId) {
        Intent intent = new Intent(packageContext, EditAnswer.class);
        intent.putExtra(EXTRA_ANSWER_ID, answerId);
        return intent;
    }
}