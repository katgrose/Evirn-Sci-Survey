package com.example.evirn_sci_survey;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
@Entity(tableName = "surveyquestionanswer",
        //Primary Composite key
        primaryKeys = {"surveyID_fkSQA", "questionID_fkSQ", "surveyQA_ID"},
        //Foreign Composite key
        foreignKeys = {
//        @ForeignKey(
//                entity = SurveyQuestion.class,
//                parentColumns = {"surveyID_fkSQ", "questionID_fkSQ"},
//                childColumns = {"surveyID_fkSQA", "questionID_fkSQA"}
//
//        )
})
public class SurveyQuestionAnswer {
/*
    If PrimaryKey annotation is used on a Embedded field,
    all columns inherited from that embedded field
    becomes the composite primary key
     (including its grand children fields).
 */
    @ColumnInfo
    private int surveyID_fkSQA;

    @ColumnInfo
    private int questionID_fkSQ;

    @ColumnInfo
    private int questionID_fkSQA;

    @ColumnInfo
    private int surveyQA_ID;

    @ColumnInfo(name = "offeredAnsText")
    private String mofferedAnsText;

    public SurveyQuestionAnswer(int surveyID_fkSQA, int questionID_fkSQ, int surveyQA_ID, String mofferedAnsText ){
        this.surveyID_fkSQA = surveyID_fkSQA;
        this.questionID_fkSQ = questionID_fkSQ;
        this.surveyQA_ID = surveyQA_ID;
        this.mofferedAnsText = mofferedAnsText;
    }

    public int getQuestionID_fkSQ() {
        return questionID_fkSQ;
    }

    public void setQuestionID_fkSQ(int questionID_fkSQ) { this.questionID_fkSQ = questionID_fkSQ; }

    public int getSurveyID_fkSQA() {
        return surveyID_fkSQA;
    }

    public void setSurveyID_fkSQA(int surveyID_fkSQA) {
        this.surveyID_fkSQA = surveyID_fkSQA;
    }

    public int getQuestionID_fkSQA() { return questionID_fkSQA; }

    public void setQuestionID_fkSQA(int questionID_fkSQA) {
        this.questionID_fkSQA = questionID_fkSQA;
    }

    public int getSurveyQA_ID() {
        return surveyQA_ID;
    }

    public void setSurveyQA_ID(int surveyQA_ID) {
        this.surveyQA_ID = surveyQA_ID;
    }

    public String getMofferedAnsText() { return mofferedAnsText; }

    public void setMofferedAnsText(String mofferedAnsText) { this.mofferedAnsText = mofferedAnsText; }

}