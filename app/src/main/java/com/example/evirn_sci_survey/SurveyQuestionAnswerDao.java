package com.example.evirn_sci_survey;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//TODO
@Dao
public interface SurveyQuestionAnswerDao {
    @Insert
    void insert(SurveyQuestionAnswer surveyQuestionAnswer);

    @Update
    void update(SurveyQuestionAnswer surveyQuestionAnswer);

    @Query("DELETE FROM surveyquestionanswer")
    void deleteAll();

    @Query("SELECT * FROM surveyquestionanswer ORDER BY surveyId DESC")
    LiveData<List<SurveyQuestionAnswer>> getAllSurveyOfferedAnswers();
}
