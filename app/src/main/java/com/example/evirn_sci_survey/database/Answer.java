package com.example.evirn_sci_survey.database;
/*
    Title: Answer.java
    Abstract: This class holds an entity(table) for answers.
              This table has relationships with SurveyQuestionAnswer and SurveyQuestion classes.
              It's function is: Every survey question has an answer either 1) the offered one OR another text that the user types in.

    Date: 11/10/2021
    Author:EnvironSciTeam2K21
 */
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "answer",
        //Index to not trigger full data scan of parent table upon changes.
        indices = {@Index(value = {"surveyId", "questionId"})},
        primaryKeys = {"mofferedAnsId", "surveyId", "questionId"},
        foreignKeys = {
                //Has a relationship with SurveyQuestionAnswer --> Every survey question has an answer either 1) the offered one OR
                @ForeignKey(
                        entity = SurveyQuestionAnswer.class,
                        parentColumns = {"mofferedAnsId"},
                        childColumns = {"mofferedAnsId"}
                ),
                @ForeignKey(
                        entity = SurveyQuestion.class,
                        parentColumns = {"questionId"},
                        childColumns = {"questionId"}
                )
        })
public class Answer {
    @ColumnInfo(name = "mofferedAnsId")
    private int mofferedAnsId;

    @ColumnInfo(name = "surveyId")
    private int msurveyId;

    @ColumnInfo(name = "questionId")
    private int mquestionId;

    @ColumnInfo(name = "anotherAnsText")// 2) another text that the user types in
    private String manotherAnsText;

    public Answer(int mofferedAnsId, int msurveyId, int mquestionId, String manotherAnsText){
        this.mofferedAnsId = mofferedAnsId;
        this.msurveyId = msurveyId;
        this.mquestionId = mquestionId;
        this.manotherAnsText = manotherAnsText;
    }

    public int getMofferedAnsId() {
        return mofferedAnsId;
    }

    public void setMofferedAnsId(int mofferedAnsId) {
        this.mofferedAnsId = mofferedAnsId;
    }

    public int getMsurveyId() {
        return msurveyId;
    }

    public void setMsurveyId(int msurveyId) {
        this.msurveyId = msurveyId;
    }

    public int getMquestionId() {
        return mquestionId;
    }

    public void setMquestionId(int mquestionId) {
        this.mquestionId = mquestionId;
    }

    public String getManotherAnsText() {
        return manotherAnsText;
    }

    public void setManotherAnsText(String manotherAnsText) {
        this.manotherAnsText = manotherAnsText;
    }

}
