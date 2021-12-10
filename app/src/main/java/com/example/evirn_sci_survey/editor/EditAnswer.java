package com.example.evirn_sci_survey.editor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evirn_sci_survey.AdminActivity;
import com.example.evirn_sci_survey.R;
import com.example.evirn_sci_survey.database.SurveyQuestionAnswer;
import com.example.evirn_sci_survey.database.SurveyQuestionAnswerDao;
import com.example.evirn_sci_survey.database.SurveyRoomDatabase;

import java.util.ArrayList;

public class EditAnswer extends AppCompatActivity {
    private static final String EXTRA_ANSWER_ID = "SURVEY.EXTRA_ANSWER_ID";

    private SurveyQuestionAnswerDao questionAnswerDAO;

    private TextView mAnswerText;
    private Spinner mQuestionType;
    private TextView mMinLbl, mMaxLbl;
    private TextView mMinText, mMaxText;

    private SurveyQuestionAnswer answer;

    boolean sliderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_answer);
        questionAnswerDAO = SurveyRoomDatabase.getDatabase(getApplication()).surveyQuestionAnswerDao();
        int answerId = getIntent().getIntExtra(EXTRA_ANSWER_ID, -1);
        answer = questionAnswerDAO.getAnswerFromId(answerId);

        if(answer == null) { startActivity(AdminActivity.getIntent(EditAnswer.this)); }
        else {
            mAnswerText = findViewById(R.id.answer_text);
            Button mSaveChangesButton = findViewById(R.id.saveBtn);
            Button mCancelButton = findViewById(R.id.cancelBtn);
            mQuestionType = findViewById(R.id.questionType);
            mMinLbl = findViewById(R.id.min_lbl);
            mMaxLbl = findViewById(R.id.max_lbl);
            mMinText = findViewById(R.id.slider_min);
            mMaxText = findViewById(R.id.slider_max);

            mAnswerText.setText(answer.getMofferedAnsText());
            ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this, R.array.question_types, android.R.layout.simple_spinner_item);
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
            mQuestionType.setAdapter(adapter);
            mQuestionType.setSelection(answer.getQuestionIndex());

            if(answer.getQuestionType() != null && answer.getQuestionType().equals("Slider")) {
                mMinLbl.setVisibility(View.VISIBLE);
                mMaxLbl.setVisibility(View.VISIBLE);
                mMinText.setVisibility(View.VISIBLE);
                mMaxText.setVisibility(View.VISIBLE);
                mMinText.setText(String.valueOf(answer.getSliderMin()));
                mMaxText.setText(String.valueOf(answer.getSliderMax()));
                sliderView = true;
            } else {
                mMinLbl.setVisibility(View.GONE);
                mMaxLbl.setVisibility(View.GONE);
                mMinText.setVisibility(View.GONE);
                mMaxText.setVisibility(View.GONE);
                sliderView = false;
            }

            mSaveChangesButton.setOnClickListener(v -> {
                Save();
                returnToEditQuestion();
            });

            mCancelButton.setOnClickListener(view -> returnToEditQuestion());

            mQuestionType.setOnItemSelectedListener(
                new AdapterView.OnItemSelectedListener() {
                    @Override
                    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                        if((sliderView && !mQuestionType.getSelectedItem().toString().equals("Slider")) ||
                                (!sliderView && mQuestionType.getSelectedItem().toString().equals("Slider"))) {
                            Save();
                            startActivity(getIntent());
                        }
                    }

                    @Override
                    public void onNothingSelected(AdapterView<?> parent) { }
                }
            );
        }
    }

    public void Save() {
        answer.setMofferedAnsText(mAnswerText.getText().toString());
        answer.setQuestionType(mQuestionType.getSelectedItem().toString());
        String min = mMinText.getText().toString();
        String max = mMaxText.getText().toString();
        if(!min.equals("") && !max.equals("")) {
            answer.setSliderMin(Integer.parseInt(min));
            answer.setSliderMax(Integer.parseInt(max));
        }
        questionAnswerDAO.update(answer);
        Toast.makeText(EditAnswer.this,"Answer updated successfully", Toast.LENGTH_SHORT).show();
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