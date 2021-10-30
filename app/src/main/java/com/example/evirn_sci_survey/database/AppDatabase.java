package com.example.evirn_sci_survey.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.evirn_sci_survey.Question;

@Database(entities = {Question.class}, version = 2, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public static final String DB_NAME = "DATABASE";
    public static final String QUESTION_TABLE = "QUESTION_TABLE";

    public abstract QuestionDAO getQuestionDAO();
}
