package com.example.evirn_sci_survey;

import androidx.room.Dao;
import androidx.room.Insert;

@Dao
public interface AnswerDao {
    @Insert
    void insert(Answer answer);

}
