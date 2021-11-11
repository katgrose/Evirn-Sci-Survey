package com.example.evirn_sci_survey;
/*
    Title: SurveyQuestion.java
    Abstract: This is an entity(table) that houses the questionId
    Date: 11/10/2021
    Author:EnvironSciTeam2K21
 */
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "surveyquestion",
        //Index to not trigger full data scan of parent table upon changes.
        indices = {@Index(value = "surveyId")},
        //Composite Primary Key
        primaryKeys = {"surveyId", "questionId"},

        foreignKeys = {
        @ForeignKey(entity = Survey.class,
        parentColumns = "surveyId",
        childColumns = "surveyId"
        )
})

public class SurveyQuestion {

    @ColumnInfo(name = "surveyId")
    public int msurveyId;

    @ColumnInfo(name = "questionId")
    public int mquestionId;


    SurveyQuestion(int msurveyId, int mquestionId){
        this.msurveyId = msurveyId;
        this.mquestionId = mquestionId;
    }

    public int getMquestionId() {
        return mquestionId;
    }

    public void setMquestionId(int mquestionId) {
        this.mquestionId = mquestionId;
    }

    public int getMsurveyId() {
        return msurveyId;
    }

    public void setMsurveyId(int msurveyId) {
        this.msurveyId = msurveyId;
    }
}
