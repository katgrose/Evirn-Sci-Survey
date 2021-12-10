package com.example.evirn_sci_survey.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface SurveyQuestionDao {
    @Insert
    void Insert(SurveyQuestion... questions);

    @Update
    void Update(SurveyQuestion... questions);

    @Delete
    void Delete(SurveyQuestion question);

    @Query("SELECT * FROM surveyquestion")
    List<SurveyQuestion> getQuestions();

    @Query("SELECT * FROM surveyquestion WHERE surveyId = :surveyId AND questionOrder > -1 ORDER BY questionOrder")
    List<SurveyQuestion> getQuestionsInSurvey(int surveyId);

    @Query("SELECT * FROM surveyquestion WHERE surveyId = :surveyId ORDER BY questionOrder")
    List<SurveyQuestion> getAllQuestionsInSurvey(int surveyId);

    @Query("SELECT * FROM surveyquestion WHERE surveyId = :surveyId AND questionOrder = :questionOrder")
    SurveyQuestion getQuestionFromOrder(int surveyId, int questionOrder);

    @Query("SELECT * FROM surveyquestion WHERE questionId = :questionId")
    SurveyQuestion getQuestionFromId(int questionId);

    @Query("SELECT COUNT(surveyId) FROM surveyquestion WHERE surveyId = :surveyId AND questionOrder > -1")
    Integer getRowCount(int surveyId);
}
