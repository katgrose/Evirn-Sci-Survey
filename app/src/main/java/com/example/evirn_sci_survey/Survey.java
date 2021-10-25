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
    public Survey(int msurveyID, String mdescription, String mstartDate, String mendDate){
        this.msurveyID = msurveyID;
        this.mdescription = mdescription;
        this.mstartDate = mstartDate;
        this.mendDate = mendDate;
    }

    public int getMsurveyID() {
        return msurveyID;
    }

    public void setMsurveyID(int msurveyID) {
        this.msurveyID = msurveyID;
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
