package com.example.evirn_sci_survey;
/*
    Title: Survey.java
    Abstract: This is an entity(table) that houses all surveys including its Id, description, startDate, and endDate.
    Date: 11/10/2021
    Author:EnvironSciTeam2K21
 */
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "survey")
public class Survey {
    //Generates a primary key automatically
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "surveyId")
    private int msurveyId;

    @ColumnInfo(name = "description")
    private String mdescription;

    @ColumnInfo(name = "startDate")
    private String mstartDate;

    @ColumnInfo(name = "endDate")
    private String mendDate;
    //
    public Survey(int msurveyId, String mdescription, String mstartDate, String mendDate){
        this.msurveyId = msurveyId;
        this.mdescription = mdescription;
        this.mstartDate = mstartDate;
        this.mendDate = mendDate;
    }

    public int getMsurveyId() {
        return msurveyId;
    }

    public void setMsurveyId(int msurveyId) {
        this.msurveyId = msurveyId;
    }

    public String getMdescription() {
        return mdescription;
    }

    public void setMdescription(String mdescription) {
        this.mdescription = mdescription;
    }

    public String getMstartDate() {
        return mstartDate;
    }

    public void setMstartDate(String mstartDate) {
        this.mstartDate = mstartDate;
    }

    public String getMendDate() {
        return mendDate;
    }

    public void setMendDate(String mendDate) {
        this.mendDate = mendDate;
    }

}
