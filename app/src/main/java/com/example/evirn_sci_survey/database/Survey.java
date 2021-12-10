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
    public Survey(String mdescription) {
        this.mdescription = mdescription;
    }

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

    public void generateBasicQuestions(SurveyQuestionDao questionDAO, SurveyQuestionAnswerDao questionAnswerDAO) {
        // For creating placeholder database questions for questions that are built in manually.
        // This is so that done so that we have a questionId to create answers for.
        // Kind of a hacky workaround...
        SurveyQuestion q1 = new SurveyQuestion(msurveyId, "First Name and Project Name", -1);
        SurveyQuestion q2 = new SurveyQuestion(msurveyId, "Project Rating", -2);
        SurveyQuestion q3 = new SurveyQuestion(msurveyId, "Picture", -3);
        questionDAO.Insert(q1, q2, q3);
        q1 = questionDAO.getQuestionFromOrder(msurveyId, -1);
        q2 = questionDAO.getQuestionFromOrder(msurveyId, -2);
        q3 = questionDAO.getQuestionFromOrder(msurveyId, -3);
        SurveyQuestionAnswer answer1 = new SurveyQuestionAnswer(msurveyId, q1.getQuestionId(), 0, "Enter First Name");
        SurveyQuestionAnswer answer2 = new SurveyQuestionAnswer(msurveyId, q1.getQuestionId(), 1, "Enter Project Name");
        SurveyQuestionAnswer answer3 = new SurveyQuestionAnswer(msurveyId, q2.getQuestionId(), 0, "Project Rating");
        SurveyQuestionAnswer answer4 = new SurveyQuestionAnswer(msurveyId, q3.getQuestionId(), 0, "Picture");
        answer3.setQuestionType("Slider");
        questionAnswerDAO.Insert(answer1, answer2, answer3, answer4);
    }

    @Override
    public String getListItemText() {
        return getMstartDate() + "-" + getMendDate() + ": " + getMdescription();
    }

    @Override
    public Intent getListItemEditIntent(Context context) {
        return new Intent(); // Unused
    }

    @Override
    public void DeleteSelf(Context context) {
        // Delete here
    }
}
