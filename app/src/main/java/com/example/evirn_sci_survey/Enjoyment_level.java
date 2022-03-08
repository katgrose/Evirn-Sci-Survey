package com.example.evirn_sci_survey;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import com.example.evirn_sci_survey.database.Answer;
import com.example.evirn_sci_survey.database.AnswerDao;
import com.example.evirn_sci_survey.database.SurveyQuestion;
import com.example.evirn_sci_survey.database.SurveyQuestionAnswer;
import com.example.evirn_sci_survey.database.SurveyQuestionAnswerDao;
import com.example.evirn_sci_survey.database.SurveyQuestionDao;
import com.example.evirn_sci_survey.database.SurveyRoomDatabase;

import java.util.List;

public class Enjoyment_level extends AppCompatActivity {
    public static final String EXTRA_SURVEY_ID = "evirn_sci_survey.EXTRA_SURVEY_ID";

    TextView number_string;
    String status;
    String emotion;

    private Button enjoyButton;
    private SeekBar seekBar;
    private Button backButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enjoyment_level);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.saved_prefs_location), Context.MODE_PRIVATE);
        int activeSurvey = sharedPref.getInt(getString(R.string.saved_active_survey_key), 1);

        seekBar = (SeekBar) findViewById(R.id.seekBar_0_5);
        number_string = (TextView) findViewById(R.id.number_string);
        enjoyButton = (Button) findViewById(R.id.Enjoyment_button);
        backButton = (Button) findViewById(R.id.backBtnEnjoy);

        int surveyId = getIntent().getIntExtra(EXTRA_SURVEY_ID, -1);
        if(surveyId == -1) {
            Intent intent = MainActivity.getIntent(Enjoyment_level.this);
            startActivity(intent);
        }

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                if (progress == 5){
                    status = "5";
                    emotion = "Awesome";
                }else if (progress >= 4){
                    status = "4";
                    emotion = "Good";
                }else if (progress >= 3){
                    status = "3";
                    emotion = "Neutral";
                }else if (progress >= 2){
                    status = "2";
                    emotion = "Ok";
                }else if(progress >= 1){
                    status = "1";
                    emotion = "Poor";
                }else{
                    status = "0";
                    emotion = "Bad";
                }
                number_string.setText(emotion + " : " + status);
                number_string.setVisibility(View.VISIBLE);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        enjoyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v){
                SurveyQuestionDao questionDAO = SurveyRoomDatabase.getDatabase(getApplication()).surveyQuestionDao();
                SurveyQuestionAnswerDao questionAnswerDAO = SurveyRoomDatabase.getDatabase(getApplication()).surveyQuestionAnswerDao();
                AnswerDao answerDao = SurveyRoomDatabase.getDatabase(getApplication()).answerDao();

                SurveyQuestion question = questionDAO.getQuestionFromOrder(surveyId, -2);
                List<SurveyQuestionAnswer> offeredAnswers = questionAnswerDAO.getAnswersInQuestion(surveyId, question.getQuestionId());
                for(int i = 0; i < offeredAnswers.size(); i++) {
                    SurveyQuestionAnswer offeredAnswer = offeredAnswers.get(i);
                    Answer answer = new Answer(offeredAnswer.getMofferedAnsId(), surveyId, question.getQuestionId(), "");
                    answer.setSliderValue(seekBar.getProgress());
                    answerDao.insert(answer);
                }

                Intent intent_enjoy = new Intent(Enjoyment_level.this, Camera_Interaction.class);
                startActivity(intent_enjoy);
            }
        });

        backButton.setOnClickListener(v -> {
            Intent intent = QuestionDisplay.getIntent(Enjoyment_level.this, 3, activeSurvey, true);
            startActivity(intent);
        });

    }

    public static Intent getIntent(Context packageContext, int surveyId) {
        Intent intent = new Intent(packageContext, Enjoyment_level.class);
        intent.putExtra(EXTRA_SURVEY_ID, surveyId);
        return intent;
    }


}