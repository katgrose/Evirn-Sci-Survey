package com.example.evirn_sci_survey;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "surveyquestion",
        //Composite Primary Key
        primaryKeys = {"surveyID_fkSQ", "questionID"})
//        foreignKeys = {
//        @ForeignKey(entity = Survey.class,
//        parentColumns = "surveyID",
//        childColumns = "surveyID_fkSQ"
//        )
//})

public class SurveyQuestion {

    @ColumnInfo(name = "surveyID_fkSQ")
    public int msurveyID_fk;

    @ColumnInfo(name = "questionID")
    public int mquestionID;


    SurveyQuestion(int msurveyID_fk, int mquestionID){
        this.msurveyID_fk = msurveyID_fk;
        this.mquestionID = mquestionID;
    }

    public int getMsurveyID_fk() {
        return msurveyID_fk;
    }

    public void setMsurveyID_fk(int msurveyID_fk) {
        this.msurveyID_fk = msurveyID_fk;
    }

    public int getMquestionID() {
        return mquestionID;
    }

    public void setMquestionID(int mquestionID) {
        this.mquestionID = mquestionID;
    }
}
