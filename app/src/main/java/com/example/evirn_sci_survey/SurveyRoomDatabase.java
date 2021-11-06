package com.example.evirn_sci_survey;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//Database setup
@Database(entities = {Survey.class, SurveyQuestion.class, SurveyQuestionAnswer.class, Answer.class}, version = 1, exportSchema = false)
public abstract class SurveyRoomDatabase extends RoomDatabase {

    public abstract SurveyDao surveyDao();
    public abstract SurveyQuestionDao surveyQuestionDao();
    public abstract SurveyQuestionAnswerDao surveyQuestionAnswerDao();
    public abstract AnswerDao answerDao();

    private static volatile SurveyRoomDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static SurveyRoomDatabase getDatabase(final Context context){
        if(INSTANCE == null){
            synchronized (SurveyRoomDatabase.class){
                if(INSTANCE == null){
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SurveyRoomDatabase.class, "survey_database")
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
