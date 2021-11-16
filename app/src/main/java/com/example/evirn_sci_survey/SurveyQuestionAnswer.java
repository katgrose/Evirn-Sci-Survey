package com.example.evirn_sci_survey;
/*
    Title: SurveyQuestionAnswer.java
    Abstract: This is an entity(table) includes the ability to fill in the offered answer.
    Date: 11/10/2021
    Author:EnvironSciTeam2K21
 */
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;

@Entity(tableName = "surveyquestionanswer",
        //Index to not trigger full data scan of parent table upon changes
        //unique property : prevents a table from having two rows that contain the same set of values in columns.
        indices = {@Index(value = {"surveyId", "questionId"}, unique = true)},
        //Primary Composite key
        primaryKeys = {"offeredAnsId", "questionId", "surveyId"},
        //Foreign Composite key
        foreignKeys = {
        @ForeignKey(
                entity = SurveyQuestion.class,
                parentColumns = {"surveyId", "questionId"},
                childColumns = {"surveyId", "questionId"}

       )
})
public class SurveyQuestionAnswer {
/*
    If PrimaryKey annotation is used on a Embedded field,
    all columns inherited from that embedded field
    becomes the composite primary key
     (including its grand children fields).
 */
    @ColumnInfo(name = "surveyId")
    private int msurveyId;

    @ColumnInfo(name = "questionId")
    private int mquestionId;

    @ColumnInfo(name = "offeredAnsId")
    private  int mofferedAnsId;

    @ColumnInfo(name = "offeredAnsText")
    private String mofferedAnsText;

    public SurveyQuestionAnswer(int msurveyId, int mquestionId, int mofferedAnsId, String mofferedAnsText ){
        this.msurveyId = msurveyId;
        this.mquestionId = mquestionId;
        this.mofferedAnsId = mofferedAnsId;
        this.mofferedAnsText = mofferedAnsText;
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

    public int getMofferedAnsId() {
        return mofferedAnsId;
    }

    public void setMofferedAnsId(int mofferedAnsId) {
        this.mofferedAnsId = mofferedAnsId;
    }

    public String getMofferedAnsText() { return mofferedAnsText; }

    public void setMofferedAnsText(String mofferedAnsText) { this.mofferedAnsText = mofferedAnsText; }

}