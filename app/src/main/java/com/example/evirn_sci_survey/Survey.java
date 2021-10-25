package com.example.evirn_sci_survey;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "survey")
public class Survey {
    //Generates a primary key automatically
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "surveyID")
    private int msurveyID;

    @ColumnInfo(name = "description")
    private String mdescription;

    @ColumnInfo(name = "startDate")
    private String mstartDate;

    @ColumnInfo(name = "endDate")
    private String mendDate;
    //
    public Survey(int surveyID, String description, String startDate, String endDate){
        this.msurveyID = surveyID;
        this.mdescription = description;
        this.mstartDate = startDate;
        this.mendDate = endDate;
    }

    public int getSurveyID() {
        return msurveyID;
    }

    public void setSurveyID(int surveyID) {
        this.msurveyID = surveyID;
    }

    public String getDescription() {
        return mdescription;
    }

    public void setDescription(String description) {
        this.mdescription = description;
    }

    public String getStartDate() {
        return mstartDate;
    }

    public void setStartDate(String startDate) {
        this.mstartDate = startDate;
    }

    public String getEndDate() {
        return mendDate;
    }

    public void setEndDate(String endDate) {
        this.mendDate = endDate;
    }
}
