package com.example.evirn_sci_survey;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import com.example.evirn_sci_survey.database.AnswerDao;
import com.example.evirn_sci_survey.database.Survey;
import com.example.evirn_sci_survey.database.SurveyDao;
import com.example.evirn_sci_survey.database.SurveyQuestion;
import com.example.evirn_sci_survey.database.SurveyQuestionAnswerDao;
import com.example.evirn_sci_survey.database.SurveyQuestionDao;
import com.example.evirn_sci_survey.database.SurveyRoomDatabase;

import java.io.IOException;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class SurveryDaoTest {
    private SurveyDao surveyDao;
    private SurveyRoomDatabase surveyDaoTest;


    @Before
    public void createDB() {
        Context context = ApplicationProvider.getApplicationContext(); // Allows us to get the information about the test
        surveyDaoTest = Room.inMemoryDatabaseBuilder(context, SurveyRoomDatabase.class).build();
        surveyDao = surveyDaoTest.surveyDao();
    }

    @After
    public void closeDb() throws IOException {
        surveyDaoTest.close();
    }

    @Test
    public void insertASurvey() throws Exception {
        Survey temp = new Survey(0, "Random Q", "03-08-2022", "03-09-2022");
        // Insert the record
        surveyDao.insert(temp);

        // Getting the results
        List<Survey> result = surveyDao.getAllSurveys();

        assertEquals(result.size(), 1);
    }

//    public Survey(int msurveyId, String mdescription, String mstartDate, String mendDate){
//        this.msurveyId = msurveyId;
//        this.mdescription = mdescription;
//        this.mstartDate = mstartDate;
//        this.mendDate = mendDate;
//    }

}
