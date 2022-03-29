package com.example.evirn_sci_survey;


import static org.junit.Assert.assertEquals;

import android.content.Context;
import android.util.Log;

import androidx.room.Room;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import com.example.evirn_sci_survey.database.Response;
import com.example.evirn_sci_survey.database.ResponseDao;
import com.example.evirn_sci_survey.database.SurveyRoomDatabase;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ResponseDaoTest {
    private static final String TAG = "ResponseDAOTest";

    private ResponseDao responseDao;
    private SurveyRoomDatabase responseDaoTest;

    @Before
    public void createDB(){
        Context context = ApplicationProvider.getApplicationContext();
        responseDaoTest = Room.inMemoryDatabaseBuilder(context, SurveyRoomDatabase.class).build();
        responseDao = responseDaoTest.responseDao();
    }

    @After
    public void closeDB() throws IOException {
        responseDaoTest.close();
    }

    @Test
    public void insertAResponse() throws Exception{
        responseDao.insertResponse(new Response(0));
        responseDao.insertResponse(new Response(0));
        responseDao.insertResponse(new Response(0));

        List<Response> result = responseDao.getResponses();
        assertEquals(result.size(), 3);
        Log.i(TAG, "insertAResponse: " + result.get(0).getSurveyID());
    }
}
