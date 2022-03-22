package com.example.evirn_sci_survey.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//TODO
@Dao
public interface SurveyQuestionAnswerDao {
    @Insert
    void Insert(SurveyQuestionAnswer... answers);

    @Update
    void update(SurveyQuestionAnswer surveyQuestionAnswer);

    @Delete
    void Delete(SurveyQuestionAnswer surveyQuestionAnswer);

    @Query("DELETE FROM surveyquestionanswer")
    void deleteAll();

    @Query("SELECT * FROM surveyquestionanswer ORDER BY surveyId DESC")
    LiveData<List<SurveyQuestionAnswer>> getAllSurveyOfferedAnswers();

    @Query("SELECT * FROM surveyquestionanswer ORDER BY surveyId DESC")
    List<SurveyQuestionAnswer> getAllSurveyAnswers();

    @Query("SELECT * FROM surveyquestionanswer WHERE surveyId = :surveyId AND questionId = :questionId ORDER BY ansOrder")
    List<SurveyQuestionAnswer> getAnswersInQuestion(int surveyId, int questionId);

    @Query("SELECT * FROM surveyquestionanswer WHERE mofferedAnsId = :offeredAnswerId")
    SurveyQuestionAnswer getAnswerFromId(int offeredAnswerId);

    @Query("SELECT COUNT(surveyId) FROM surveyquestionanswer WHERE surveyId = :surveyId AND questionId = :questionId")
    Integer getAnswerCount(int surveyId, int questionId);
}
