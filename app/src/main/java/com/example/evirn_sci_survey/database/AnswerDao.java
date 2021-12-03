package com.example.evirn_sci_survey.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

//TODO
@Dao
public interface AnswerDao {
    @Insert
    void insert(Answer answer);

    @Update
    void update(Answer answer);

    @Query("DELETE FROM answer")
    void deleteAll();

}
