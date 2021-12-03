package com.example.evirn_sci_survey.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface QuestionDAO {
    /**@Insert
    void Insert(Question... questions);

    @Update
    void Update(Question... questions);

    @Delete
    void Delete(Question question);

    @Query("SELECT * FROM " + AppDatabase.QUESTION_TABLE)
    List<Question> getQuestions();

    @Query("SELECT * FROM " + AppDatabase.QUESTION_TABLE + " WHERE questionOrder = :questionOrder")
    Question getQuestionFromOrder(int questionOrder);

    @Query("SELECT * FROM " + AppDatabase.QUESTION_TABLE + " WHERE questionId = :questionId")
    Question getQuestionFromId(int questionId);

    @Query("SELECT COUNT(questionId) FROM " + AppDatabase.QUESTION_TABLE)
    Integer getRowCount();*/
}
