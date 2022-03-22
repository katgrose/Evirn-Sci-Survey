package com.example.evirn_sci_survey;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.evirn_sci_survey.database.Answer;
import com.example.evirn_sci_survey.database.AnswerDao;
import com.example.evirn_sci_survey.database.Survey;
import com.example.evirn_sci_survey.database.SurveyDao;
import com.example.evirn_sci_survey.database.SurveyQuestion;
import com.example.evirn_sci_survey.database.SurveyQuestionAnswer;
import com.example.evirn_sci_survey.database.SurveyQuestionAnswerDao;
import com.example.evirn_sci_survey.database.SurveyQuestionDao;
import com.example.evirn_sci_survey.database.SurveyRoomDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class ExcelExporterTest {
    private SurveyRoomDatabase surveyDaoTest;
    private SurveyDao surveyDao;
    private SurveyQuestionDao surveyQuestionDao;
    private SurveyQuestionAnswerDao surveyQuestionAnswerDao;
    private AnswerDao answerDao;
    private int activeSurvey = 1;

    private static final String TAG = "ExcelExporterTest";

    @Before
    public void createDB() {

        Log.i(TAG, "createDB started");

        Context context = ApplicationProvider.getApplicationContext(); // Allows us to get the information about the test
        // Create a temp DB
        surveyDaoTest = Room.inMemoryDatabaseBuilder(context, SurveyRoomDatabase.class).build();

        // Create the three db's needed for excel exporter
        surveyDao = surveyDaoTest.surveyDao();
        surveyQuestionDao = surveyDaoTest.surveyQuestionDao();
        surveyQuestionAnswerDao = surveyDaoTest.surveyQuestionAnswerDao();
        answerDao = surveyDaoTest.answerDao();

        // ---------------- Inserting data ----------------
        // ------------------------------------------------

        // ------------------ Insert into surveyDao ------------------
        Survey survey1 = new Survey(activeSurvey, "Description of survey typed here", "03-08-2022", "03-09-2022");
        surveyDao.insert(survey1);

        // Getting the survey ID from the table itself (It should be the same as the activeSurvey Variable)
        int survey1Id = surveyDao.getAllSurveys().get(0).getMsurveyId();

        Log.i(TAG, "Creating Survey Table: " + survey1Id);

        // ------------------ Insert into surveyQuestionDao ------------------
        // Creating 3 new questions
        SurveyQuestion q1 = new SurveyQuestion(survey1Id, "First Name and Project Name", -1);
        SurveyQuestion q2 = new SurveyQuestion(survey1Id, "Project Rating", -2);
        SurveyQuestion q3 = new SurveyQuestion(survey1Id, "Picture", -3);

        Log.i(TAG, "createDB: " + q1.getMsurveyId());
        Log.i(TAG, "createDB: " + q2.getMsurveyId());
        Log.i(TAG, "createDB: " + q3.getMsurveyId());

        surveyQuestionDao.Insert(q1, q2, q3);

        // ------------------ Insert into surveyQuestionAnswerDao ------------------

        // Getting the questions from the order in which they appear (not necessary)
        q1 = surveyQuestionDao.getQuestionFromOrder(survey1Id, -1);
        q2 = surveyQuestionDao.getQuestionFromOrder(survey1Id, -2);
        q3 = surveyQuestionDao.getQuestionFromOrder(survey1Id, -3);

        // When you answer a question, you pass it the survey ID, the question ID, the order in which the you answer the question, and your answer
        SurveyQuestionAnswer answer1 = new SurveyQuestionAnswer(survey1Id, q1.getQuestionId(), 0, "Enter First Name"); // First Name and Project Name
        SurveyQuestionAnswer answer2 = new SurveyQuestionAnswer(survey1Id, q1.getQuestionId(), 1, "Enter Project Name"); // First Name and Project Name
        SurveyQuestionAnswer answer3 = new SurveyQuestionAnswer(survey1Id, q2.getQuestionId(), 0, "Project Rating"); // Project Rating
        SurveyQuestionAnswer answer4 = new SurveyQuestionAnswer(survey1Id, q3.getQuestionId(), 0, "Picture"); // Picture

        // Setting answer 3 to be a slider option
        answer3.setQuestionType("Slider");
        // Inserting the answers
        surveyQuestionAnswerDao.Insert(answer1, answer2, answer3, answer4);

        // ------------------ Insert into answerDao ------------------
        // Question 1
        List<SurveyQuestionAnswer> offeredAnswers = surveyQuestionAnswerDao.getAnswersInQuestion(activeSurvey, q1.getQuestionId()); // Getting all the answers in the questions?
        for (int i = 0; i < offeredAnswers.size(); i++) { // Inserting the answers
            SurveyQuestionAnswer offeredAnswer = offeredAnswers.get(i);
            Answer answer = new Answer(offeredAnswer.getMofferedAnsId(), activeSurvey, q1.getQuestionId(), "User name here (Derek) q1");
            answerDao.insert(answer);
        }
        // Question 2
        offeredAnswers = surveyQuestionAnswerDao.getAnswersInQuestion(activeSurvey, q2.getQuestionId()); // Getting all the answers in the questions?
        for (int i = 0; i < offeredAnswers.size(); i++) { // Inserting the answers
            SurveyQuestionAnswer offeredAnswer = offeredAnswers.get(i);
            Answer answer = new Answer(offeredAnswer.getMofferedAnsId(), activeSurvey, q2.getQuestionId(), "User name here (Derek) q2");
            answerDao.insert(answer);
        }
        // Question 3
        offeredAnswers = surveyQuestionAnswerDao.getAnswersInQuestion(activeSurvey, q3.getQuestionId()); // Getting all the answers in the questions?
        for (int i = 0; i < offeredAnswers.size(); i++) { // Inserting the answers
            SurveyQuestionAnswer offeredAnswer = offeredAnswers.get(i);
            Answer answer = new Answer(offeredAnswer.getMofferedAnsId(), activeSurvey, q3.getQuestionId(), "User name here (Derek) q3");
            answerDao.insert(answer);
        }

    }


    @After
    public void closeDb() throws IOException {
        surveyDaoTest.close();
    }

    @Test
    public void printTables() throws Exception {
        // Printing the Survey table
        String r = "";
        List<Survey> survey = surveyDao.getAllSurveys();
        for (int i = 0; i<survey.size(); i++){
            r = r + "Survey ID: " + survey.get(i).getMsurveyId() + ", ";
            r = r + "Description: " + survey.get(i).getMdescription() + ", ";
            r = r + "Start Date: " + survey.get(i).getMstartDate() + ", ";
            r = r + "End Date: " + survey.get(i).getMendDate() + "\n";
        }
        Log.i(TAG, "Printing Survey Table\n"+r+"\n");

        // Printing SurveyQuestion Table
        r = "";
        List<SurveyQuestion> surveyQuestions = surveyQuestionDao.getAllQuestionsInSurvey(1);
        for (int i = 0; i<surveyQuestions.size(); i++){
            r = r + "Survey ID: " + surveyQuestions.get(i).getMsurveyId() + ", ";
            r = r + "Question Text: " + surveyQuestions.get(i).getQuestionText() + ", ";
            r = r + "Question Order: " + surveyQuestions.get(i).getQuestionOrder() + ", ";
            r = r + "Question ID: " + surveyQuestions.get(i).getQuestionId() + "\n";
        }
        Log.i(TAG, "Printing SurveyQuestions Table\n"+r);

        // Printing SurveyQuestionAnswers table
        r = "";
        List<SurveyQuestionAnswer> surveyQuestionAnwsers = surveyQuestionAnswerDao.getAllSurveyAnswers();
        Log.i(TAG, ""+surveyQuestionAnwsers.size());
        for (int i = 0; i<surveyQuestionAnwsers.size(); i++){
            r = r + "Survey ID: " + surveyQuestionAnwsers.get(i).getMsurveyId() + ", ";
            r = r + "Question Text: " + surveyQuestionAnwsers.get(i).getMquestionId() + ", ";
            r = r + "Question Order: " + surveyQuestionAnwsers.get(i).getMansOrder() + ", ";
            r = r + "Question Answer: " + surveyQuestionAnwsers.get(i).getMofferedAnsText() + "\n";
        }
        Log.i(TAG, "Printing SurveyQuestionAnswer Table\n"+r);

        // Printing the Answer Table
        r = "";
        List<Answer> answers = answerDao.getAllAnswers();
        Log.i(TAG, "Size of Answer table: "+answers.size());
        for (int i = 0; i<answers.size(); i++){
            r = r + "Answer ID: "+ answers.get(i).getAnswerId() + ", ";
            r = r + "Offer Answer ID: "+ answers.get(i).getMofferedAnsId() + ", "; // Don't know
            r = r + "Survey ID: " + answers.get(i).getMsurveyId() + ", ";
            r = r + "Question Text: " + answers.get(i).getMquestionId() + ", ";
            r = r + "Another Answer Text: " + answers.get(i).getManotherAnsText() + "\n";
        }
        Log.i(TAG, "Printing SurveyQuestionAnswer Table\n"+r);
    }

    @Test
    public void testExporter() throws Exception  {
        String results = ExcelExporter.export(surveyQuestionDao, surveyQuestionAnswerDao, answerDao, activeSurvey);
        Log.i(TAG, results);
        // Check to make sure they are not equal
        assertFalse(results.equals("Error writing to file"));
    }


}
