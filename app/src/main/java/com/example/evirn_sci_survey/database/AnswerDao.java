package com.example.evirn_sci_survey.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;


@Dao
public interface AnswerDao {
    @Insert
    void insert(Answer... answers);

    @Insert
    long insert(Answer answer);

    @Update
    void update(Answer answer);

    @Query("DELETE FROM answer")
    void deleteAll();

    @Query("SELECT * FROM answer WHERE surveyId = :surveyId AND mofferedAnsId = :answerId")
    List<Answer> getAnswersFromQuestionAnswer(int surveyId, int answerId);

    @Query("SELECT * FROM answer")
    List<Answer> getAllAnswers();
}
