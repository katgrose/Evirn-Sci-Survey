package com.example.evirn_sci_survey.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.ArrayList;

/*
    Title: Response.java
    Abstract: Generic object for tracking a user's responses in a survey.
              Allows for querying based on user which will result in a better
              formatted spreadsheet. May have to Google's GSON library for type
              conversion.
    Date: 3/15/2022
    Author:EnvironSciTeam2K22
 */

@Entity(tableName = "response")
public class Response {

    public Response(ArrayList<Integer> answers) {
        this.answers = answers;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "responseId")
    private int mResponseId;

    @ColumnInfo(name = "answers")
    private ArrayList<Integer> answers;

    public int getResponseId() {
        return mResponseId;
    }

    public void setResponseId(int mResponseId) {
        this.mResponseId = mResponseId;
    }

    public ArrayList<Integer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Integer> answers) {
        this.answers = answers;
    }

    public void addAnswer(int answerId){
        this.answers.add(answerId);
    }
}
