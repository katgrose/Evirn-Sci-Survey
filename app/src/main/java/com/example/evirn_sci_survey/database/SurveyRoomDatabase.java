package com.example.evirn_sci_survey.database;
/*
    Title: SurveyRoomDatabase.java
    Abstract: This is a Java class that houses the database set up. All entities declared are in their final forms.
    Date: 11/10/2021
    Author:EnvironSciTeam2K21
 */

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import androidx.room.TypeConverter;
import androidx.room.TypeConverters;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Survey.class, SurveyQuestion.class, SurveyQuestionAnswer.class, Answer.class, Response.class}, version = 7, exportSchema = false)
@TypeConverters({Converters.class})
public abstract class SurveyRoomDatabase extends RoomDatabase {

    public abstract SurveyDao surveyDao();
    public abstract SurveyQuestionDao surveyQuestionDao();
    public abstract SurveyQuestionAnswerDao surveyQuestionAnswerDao();
    public abstract AnswerDao answerDao();
    public abstract ResponseDao responseDao();

    private static volatile SurveyRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    public static SurveyRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (SurveyRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SurveyRoomDatabase.class, "survey_database")
                            .addCallback(sRoomDatabaseCallback).allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }


    /**
     * Override the onCreate method to populate the database.
     * For this sample, we clear the database every time it is created.
     * it is a test
     */
    private static RoomDatabase.Callback sRoomDatabaseCallback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            // If you want to keep data through app restarts,
            // comment out the following block
            /**databaseWriteExecutor.execute(() -> {
                // Populate the database in the background.
                // If you want to start with more surveys, just add them.
                SurveyDao dao = INSTANCE.surveyDao();
                dao.deleteAll();

                Survey survey = new Survey(1, "EnvironSciSurveyTest1", "11/21", "12/21");
                dao.insert(survey);
            });*/
        }
    };
}
