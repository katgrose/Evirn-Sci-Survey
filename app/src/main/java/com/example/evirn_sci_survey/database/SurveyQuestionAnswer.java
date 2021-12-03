package com.example.evirn_sci_survey.database;
/*
    Title: SurveyQuestionAnswer.java
    Abstract: This is an entity(table) includes the ability to fill in the offered answer.
    Date: 11/10/2021
    Author:EnvironSciTeam2K21
 */
import android.content.Context;
import android.content.Intent;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.evirn_sci_survey.editor.EditAnswer;
import com.example.evirn_sci_survey.editor.EditListItem;

import java.util.List;

@Entity(tableName = "surveyquestionanswer",
        //Index to not trigger full data scan of parent table upon changes
        indices = {@Index(value = {"surveyId", "questionId"})},
        //Foreign Composite key
        foreignKeys = {
        @ForeignKey(
                entity = SurveyQuestion.class,
                parentColumns = {"questionId"},
                childColumns = {"questionId"}
        )
})
public class SurveyQuestionAnswer implements EditListItem {
/*
    If PrimaryKey annotation is used on a Embedded field,
    all columns inherited from that embedded field
    becomes the composite primary key
     (including its grand children fields).
 */
    @PrimaryKey(autoGenerate = true)
    private int mofferedAnsId;

    @ColumnInfo(name = "surveyId")
    private int msurveyId;

    @ColumnInfo(name = "questionId")
    private int mquestionId;

    @ColumnInfo(name = "ansOrder")
    private  int mansOrder;

    @ColumnInfo(name = "offeredAnsText")
    private String mofferedAnsText;

    public void setCheckbox(boolean checkbox) {
        this.checkbox = checkbox;
    }

    public boolean isCheckbox() {
        return checkbox;
    }

    private boolean checkbox;

    public SurveyQuestionAnswer(int msurveyId, int mquestionId, int mansOrder, String mofferedAnsText ){
        this.msurveyId = msurveyId;
        this.mquestionId = mquestionId;
        this.mansOrder = mansOrder;
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

    public int getMansOrder() { return mansOrder; }

    public void setMansOrder(int mansOrder) { this.mansOrder = mansOrder; }

    @Override
    public String getListItemText() {
        return getMansOrder() + ": " + getMofferedAnsText();
    }

    @Override
    public Intent getListItemEditIntent(Context context) {
        return EditAnswer.getIntent(context, getMofferedAnsId());
    }

    @Override
    public void DeleteSelf(Context context) {
        SurveyQuestionAnswerDao questionAnswerDAO = SurveyRoomDatabase.getDatabase(context.getApplicationContext()).surveyQuestionAnswerDao();
        questionAnswerDAO.Delete(this);
        // Update id for all other answers
        List<SurveyQuestionAnswer> answerList = questionAnswerDAO.getAnswersInQuestion(msurveyId, mquestionId);
        for(int i = 0; i < answerList.size(); i++) {
            answerList.get(i).setMansOrder(i);
            questionAnswerDAO.update(answerList.get(i));
        }
    }

}