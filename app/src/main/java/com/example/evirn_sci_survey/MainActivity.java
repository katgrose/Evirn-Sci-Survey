package com.example.evirn_sci_survey;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
/*import android.database;
import android.database.sqlite;*/
import android.content.Intent;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import com.example.evirn_sci_survey.database.Answer;
import com.example.evirn_sci_survey.database.AnswerDao;
import com.example.evirn_sci_survey.database.Response;
import com.example.evirn_sci_survey.database.ResponseDao;
import com.example.evirn_sci_survey.database.Survey;
import com.example.evirn_sci_survey.database.SurveyDao;
import com.example.evirn_sci_survey.database.SurveyQuestion;
import com.example.evirn_sci_survey.database.SurveyQuestionAnswer;
import com.example.evirn_sci_survey.database.SurveyQuestionAnswerDao;
import com.example.evirn_sci_survey.database.SurveyQuestionDao;
import com.example.evirn_sci_survey.database.SurveyRoomDatabase;
import com.example.evirn_sci_survey.unused.Page_02;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private TextView mFirstName;
    private TextView mProjectName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences sharedPref = getSharedPreferences(getString(R.string.saved_prefs_location), Context.MODE_PRIVATE);
        int activeSurvey = sharedPref.getInt(getString(R.string.saved_active_survey_key), 1);

        mFirstName = findViewById(R.id.User_Name); // Gets the name of the user?
        mProjectName = findViewById(R.id.Project_Name);
        Button nextButton = findViewById(R.id.main_button_next);
        Button adminLoginButton = findViewById(R.id.admin_login_button);

        setDefaultValues();

        adminLoginButton.setOnClickListener(view -> {
            Intent intent = LoginActivity.getIntent(MainActivity.this);
            startActivity(intent);
        });

        nextButton.setOnClickListener(v -> {
            if(mFirstName.getText().toString().equals("admin")) {
                SharedPreferences.Editor editor = sharedPref.edit();
                editor.putBoolean(getString(R.string.saved_logged_in_key), true);
                editor.apply();
                startActivity(AdminActivity.getIntent(MainActivity.this));
            } else {
                SurveyQuestionDao questionDAO = SurveyRoomDatabase.getDatabase(getApplication()).surveyQuestionDao();
                SurveyQuestionAnswerDao questionAnswerDAO = SurveyRoomDatabase.getDatabase(getApplication()).surveyQuestionAnswerDao();
                AnswerDao answerDao = SurveyRoomDatabase.getDatabase(getApplication()).answerDao();

                SurveyQuestion question = questionDAO.getQuestionFromOrder(activeSurvey, -1);
                List<SurveyQuestionAnswer> offeredAnswers = questionAnswerDAO.getAnswersInQuestion(activeSurvey, question.getQuestionId()); // Getting all the answers in the questions?
                for(int i = 0; i < offeredAnswers.size(); i++) { // Inserting the answers
                    SurveyQuestionAnswer offeredAnswer = offeredAnswers.get(i);
                    Answer answer = new Answer(offeredAnswer.getMofferedAnsId(), activeSurvey, question.getQuestionId(), mFirstName.getText().toString()); // Inserts the name of the user
                    answerDao.insert(answer);
                }
                Intent intent = QuestionDisplay.getIntent(MainActivity.this, 1, activeSurvey, true);
                startActivity(intent);
            }
        });
    }

    public static Intent getIntent(Context packageContext) {
        return new Intent(packageContext, MainActivity.class);
    }

    // Default values added to database at first time startup
    private void setDefaultValues() {
        SurveyDao dao = SurveyRoomDatabase.getDatabase(getApplication()).surveyDao();
        if(dao.getAllSurveys().size() == 0) {
            SurveyQuestionDao questionDAO = SurveyRoomDatabase.getDatabase(getApplication()).surveyQuestionDao();
            SurveyQuestionAnswerDao questionAnswerDAO = SurveyRoomDatabase.getDatabase(getApplication()).surveyQuestionAnswerDao();
            Survey survey = new Survey(1, "EnvironSciSurveyTest1", "11/21", "12/21");
            dao.insert(survey);
            survey.generateBasicQuestions(questionDAO, questionAnswerDAO);
            SurveyQuestion question1 = new SurveyQuestion(1, "General Info", 1);
            SurveyQuestion question2 = new SurveyQuestion(1, "How did you arrive in the workshop?", 2);
            SurveyQuestion question3 = new SurveyQuestion(1, "What tools did you use?", 3);
            questionDAO.Insert(question1, question2, question3);
            SurveyQuestion q1 = questionDAO.getQuestionFromOrder(1, 1);
            SurveyQuestion q2 = questionDAO.getQuestionFromOrder(1, 2);
            SurveyQuestion q3 = questionDAO.getQuestionFromOrder(1, 3);
            SurveyQuestionAnswer answer1 = new SurveyQuestionAnswer(1, q1.getQuestionId(), 0, "How often do you come?");
            SurveyQuestionAnswer answer2 = new SurveyQuestionAnswer(1, q1.getQuestionId(), 1, "What grade are you in?");
            SurveyQuestionAnswer answer3 = new SurveyQuestionAnswer(1, q2.getQuestionId(), 0, "How did you arrive?");
            SurveyQuestionAnswer answer4 = new SurveyQuestionAnswer(1, q3.getQuestionId(), 0, "Tape");
            SurveyQuestionAnswer answer5 = new SurveyQuestionAnswer(1, q3.getQuestionId(), 1, "Pen");
            SurveyQuestionAnswer answer6 = new SurveyQuestionAnswer(1, q3.getQuestionId(), 2, "Scissors");
            SurveyQuestionAnswer answer7 = new SurveyQuestionAnswer(1, q3.getQuestionId(), 3, "Screw Driver");
            SurveyQuestionAnswer answer8 = new SurveyQuestionAnswer(1, q3.getQuestionId(), 4, "List any other tools here...");
            answer4.setQuestionType("Checkbox");
            answer5.setQuestionType("Checkbox");
            answer6.setQuestionType("Checkbox");
            answer7.setQuestionType("Checkbox");
            questionAnswerDAO.Insert(answer1, answer2, answer3, answer4, answer5, answer6, answer7, answer8);
        }
    }
}