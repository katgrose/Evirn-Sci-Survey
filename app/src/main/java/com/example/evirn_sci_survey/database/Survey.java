package com.example.evirn_sci_survey.database;
/*
    Title: Survey.java
    Abstract: This is an entity(table) that houses all surveys including its Id, description, startDate, and endDate.
    Date: 11/10/2021
    Author:EnvironSciTeam2K21
 */

import android.content.Context;
import android.content.Intent;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.evirn_sci_survey.editor.EditListItem;

@Entity(tableName = "survey")
public class Survey implements EditListItem {
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

    @Ignore
    public Survey(String mdescription){
        this.mdescription = mdescription;
    }

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

    @Override
    public String getListItemText() {
        return getMstartDate() + "-" + getMendDate() + ": " + getMdescription();
    }

    @Override
    public Intent getListItemEditIntent(Context context) {
        return new Intent(); //TODO: Replace with edit intent
    }

    @Override
    public void DeleteSelf(Context context) {
        // Delete here
    }
}
