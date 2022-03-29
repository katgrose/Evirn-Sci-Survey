package com.example.evirn_sci_survey.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface ResponseDao {

    @Insert
    void insertResponse(Response response);

    @Update
    void updateResponse(Response response);

    @Query("SELECT * FROM response")

    List<Response> getResponses();

    @Query("SELECT * FROM response where responseId = :responseId")
    Response getResponsesByID(int responseId);
}
