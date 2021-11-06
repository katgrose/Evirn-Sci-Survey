package com.example.evirn_sci_survey;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "answer",
        primaryKeys = {"anonNum", "surveyID", "questionID"})
//        foreignKeys = {
//                //Has a relationship with SurveyQuestionAnswer --> Every survey question has an answer either 1) the offered one OR
//                @ForeignKey(
//                        entity = SurveyQuestionAnswer.class,
//                        parentColumns = {"surveyID_fkSQA", "questionID_fkSQA"},
//                        childColumns = {"surveyID_fkA", "questionID_fkA"}
//                ),
//                @ForeignKey(
//                        entity = SurveyQuestion.class,
//                        parentColumns = {"surveyID", "questionID"},
//                        childColumns = {"surveyID_fkAS", "questionID_fkAS"}
//                )
//        })
public class Answer {
    //anonNum is what you use to keep track of question nums
    @ColumnInfo(name = "anonNum")
    private int manonNum;

    @ColumnInfo(name = "surveyID")
    private int msurveyID;

    @ColumnInfo(name = "questionID")
    private int mquestionID;

    @ColumnInfo(name = "surveyID_fkA")
    private int msurveyID_fkA;

    @ColumnInfo(name = "questionID_fkA")
    private int mquestionID_fkA;

    @ColumnInfo(name = "surveyID_fkAS")
    private int msurveyID_fkAS;

    @ColumnInfo(name = "questionID_fkAS")
    private int mquestionID_fkAS;
                                            // 2) another text that the user types in
    @ColumnInfo(name = "anotherAnsText")
    private String manotherAnsText;

    public Answer(int manonNum, int msurveyID, int mquestionID, int mquestionID_fkA, int mquestionID_fkAS, int msurveyID_fkA, int msurveyID_fkAS, String manotherAnsText){
        this.manonNum = manonNum;
        this.msurveyID = msurveyID;
        this.mquestionID = mquestionID;
        this.mquestionID_fkA = mquestionID_fkA;
        this.msurveyID_fkA = msurveyID_fkA;
        this.msurveyID_fkAS = msurveyID_fkAS;
        this.mquestionID_fkAS = mquestionID_fkAS;
        this.manotherAnsText = manotherAnsText;
    }

    public int getManonNum() {
        return manonNum;
    }

    public void setManonNum(int manonNum) {
        this.manonNum = manonNum;
    }

    public int getMsurveyID() {
        return msurveyID;
    }

    public void setMsurveyID(int msurveyID) {
        this.msurveyID = msurveyID;
    }

    public int getMquestionID() {
        return mquestionID;
    }

    public void setMquestionID(int mquestionID) {
        this.mquestionID = mquestionID;
    }

    public int getMsurveyID_fkA() {
        return msurveyID_fkA;
    }

    public void setMsurveyID_fkA(int msurveyID_fkA) {
        this.msurveyID_fkA = msurveyID_fkA;
    }

    public void setMquestionID_fkA(int mquestionID_fkA) {
        this.mquestionID_fkA = mquestionID_fkA;
    }

    public int getMquestionID_fkA() {
        return mquestionID_fkA;
    }

    public int getMquestionID_fkAS() {
        return mquestionID_fkAS;
    }

    public void setMquestionID_fkAS(int mquestionID_fkAS) {
        this.mquestionID_fkAS = mquestionID_fkAS;
    }

    public void setMsurveyID_fkAS(int msurveyID_fkAS) {
        this.msurveyID_fkAS = msurveyID_fkAS;
    }

    public int getMsurveyID_fkAS() {
        return msurveyID_fkAS;
    }

    public void setManotherAnsText(String manotherAnsText) {
        this.manotherAnsText = manotherAnsText;
    }

    public String getManotherAnsText() {
        return manotherAnsText;
    }


}
