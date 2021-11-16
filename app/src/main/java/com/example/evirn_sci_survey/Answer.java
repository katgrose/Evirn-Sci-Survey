package com.example.evirn_sci_survey;
/*
    Title: Answer.java
    Abstract: This class holds an entity(table) for answers with anonNum field being the member to keep track of question order in place of a personId.
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
        primaryKeys = {"anonNum", "surveyId", "questionId"},
        foreignKeys = {
//                //Has a relationship with SurveyQuestionAnswer --> Every survey question has an answer either 1) the offered one OR
                @ForeignKey(
                        entity = SurveyQuestionAnswer.class,
                        parentColumns = {"surveyId", "questionId"},
                        childColumns = {"surveyId", "questionId"}
                ),
                @ForeignKey(
                        entity = SurveyQuestion.class,
                        parentColumns = {"surveyId", "questionId"},
                        childColumns = {"surveyId", "questionId"}
                )
        })
public class Answer {
    //anonNum is what you use to keep track of question nums
    @ColumnInfo(name = "anonNum")
    private int manonNum;

    @ColumnInfo(name = "surveyId")
    private int msurveyId;

    @ColumnInfo(name = "questionId")
    private int mquestionId;

    @ColumnInfo(name = "anotherAnsText")// 2) another text that the user types in
    private String manotherAnsText;

    public Answer(int manonNum, int msurveyId, int mquestionId, String manotherAnsText){
        this.manonNum = manonNum;
        this.msurveyId = msurveyId;
        this.mquestionId = mquestionId;
        this.manotherAnsText = manotherAnsText;
    }

    public int getManonNum() {
        return manonNum;
    }

    public void setManonNum(int manonNum) {
        this.manonNum = manonNum;
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
