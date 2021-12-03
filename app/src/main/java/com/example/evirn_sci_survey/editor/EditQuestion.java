package com.example.evirn_sci_survey.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evirn_sci_survey.MainActivity;
import com.example.evirn_sci_survey.R;
import com.example.evirn_sci_survey.database.SurveyQuestion;
import com.example.evirn_sci_survey.database.SurveyQuestionAnswer;
import com.example.evirn_sci_survey.database.SurveyQuestionAnswerDao;
import com.example.evirn_sci_survey.database.SurveyQuestionDao;
import com.example.evirn_sci_survey.database.SurveyRoomDatabase;

import java.util.ArrayList;

public class EditQuestion extends AppCompatActivity implements EditList {
    private static final String QUESTION_ID = "SURVEY.QUESTION_ID";

    private SurveyQuestionAnswerDao questionAnswerDAO;

    private TextView mQuestionText;
    private ListView mListView;
    private ListAdapter adapter;

    private SurveyQuestion question;
    private int surveyId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_question);

        SurveyQuestionDao questionDAO = SurveyRoomDatabase.getDatabase(getApplication()).surveyQuestionDao();
        questionAnswerDAO = SurveyRoomDatabase.getDatabase(getApplication()).surveyQuestionAnswerDao();
        int questionId = getIntent().getIntExtra(QUESTION_ID, -1);
        question = questionDAO.getQuestionFromId(questionId);

        if(question == null) { startActivity(MainActivity.getIntent(EditQuestion.this)); }
        else {
            surveyId = question.getMsurveyId();

            mQuestionText = findViewById(R.id.question_text);
            mListView = findViewById(R.id.answer_list);
            Button mSaveChangesButton = findViewById(R.id.saveBtn);
            Button mCancelButton = findViewById(R.id.cancelBtn);
            Button mAddAnswerButton = findViewById(R.id.add_answer_btn);

            mQuestionText.setText(question.getQuestionText());
            refreshDisplay();

            mSaveChangesButton.setOnClickListener(v -> {
                question.setQuestionText(mQuestionText.getText().toString());
                questionDAO.Update(question);
                Toast.makeText(EditQuestion.this,"Question updated successfully", Toast.LENGTH_SHORT).show();
                returnToQuestionList();
            });

            mCancelButton.setOnClickListener(view -> returnToQuestionList());

            mAddAnswerButton.setOnClickListener(view -> {
                int answerCount = questionAnswerDAO.getAnswerCount(surveyId, question.getQuestionId());
                SurveyQuestionAnswer surveyQuestionAnswer = new SurveyQuestionAnswer(surveyId, question.getQuestionId(), answerCount, "New Answer");
                questionAnswerDAO.insert(surveyQuestionAnswer);
                refreshDisplay();
            });
        }
    }

    public void returnToQuestionList() {
        Intent intent = EditQuestions.getIntent(EditQuestion.this, question.getMsurveyId());
        startActivity(intent);
    }

    public void refreshDisplay() {
        ArrayList<EditListItem> itemList = new ArrayList<>(questionAnswerDAO.getAnswersInQuestion(surveyId, question.getQuestionId()));
        adapter = new ListAdapter(this, itemList);
        mListView.setAdapter(adapter);
    }

    public static Intent getIntent(Context packageContext, int questionId) {
        Intent intent = new Intent(packageContext, EditQuestion.class);
        intent.putExtra(QUESTION_ID, questionId);
        return intent;
    }
}