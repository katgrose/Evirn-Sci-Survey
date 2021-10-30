package com.example.evirn_sci_survey.database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.evirn_sci_survey.Survey;
import com.example.evirn_sci_survey.database.SurveyDao;
import com.example.evirn_sci_survey.database.SurveyRoomDatabase;

import java.util.List;

public class SurveyRepository {
    private SurveyDao mSurveyDao;
    private LiveData<List<Survey>> mAllSurveys;

    // Note that in order to unit test the WordRepository, you have to remove the Application
    // dependency. This adds complexity and much more code, and this sample is not about testing.
    // See the BasicSample in the android-architecture-components repository at
    // https://github.com/googlesamples
    public SurveyRepository(Application application) {
       SurveyRoomDatabase db = SurveyRoomDatabase.getDatabase(application);
        mSurveyDao = db.surveyDao();
        mAllSurveys = mSurveyDao.getSurveyByDate();
    }

    // Room executes all queries on a separate thread.
    // Observed LiveData will notify the observer when the data has changed.
    public LiveData<List<Survey>> getAllSurveys() {
        return mAllSurveys;
    }

    // You must call this on a non-UI thread or your app will throw an exception. Room ensures
    // that you're not doing any long running operations on the main thread, blocking the UI.
    public void insert(Survey survey) {
        SurveyRoomDatabase.databaseWriteExecutor.execute(() -> {
            mSurveyDao.insert(survey);
        });
    }

}
