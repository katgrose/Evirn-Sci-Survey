package com.example.evirn_sci_survey.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.evirn_sci_survey.Survey;

import java.util.List;
@Dao
public interface SurveyDao {
        @Insert
        void insert(Survey survey);

        @Update
        void update(Survey survey);

        @Query("DELETE FROM survey")
        void deleteAll();

        @Query(" SELECT * FROM survey ORDER BY startDate, endDate")
        LiveData<List<Survey>> getSurveyByDate();

}
