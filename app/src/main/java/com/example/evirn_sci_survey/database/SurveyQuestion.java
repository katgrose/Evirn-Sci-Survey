package com.example.evirn_sci_survey.database;
/*
    Title: SurveyQuestion.java
    Abstract: This is an entity(table) that houses the questionId
    Date: 11/10/2021
    Author:EnvironSciTeam2K21
 */
import android.content.Context;
import android.content.Intent;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Ignore;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.example.evirn_sci_survey.editor.EditListItem;
import com.example.evirn_sci_survey.editor.EditQuestion;

import java.util.List;

@Entity(tableName = "surveyquestion",
        //Index to not trigger full data scan of parent table upon changes.
        indices = {@Index(value = "surveyId")},

        foreignKeys = {
        @ForeignKey(entity = Survey.class,
        parentColumns = "surveyId",
        childColumns = "surveyId"
        )
})

public class SurveyQuestion implements EditListItem {
    @PrimaryKey(autoGenerate = true)
    private int questionId;

    @ColumnInfo(name = "surveyId")
    private int msurveyId;

    private String questionText;
    private int questionOrder;

    public SurveyQuestion(int msurveyId, int questionOrder) {
        this.questionText = "";
        this.msurveyId = msurveyId;
        this.questionOrder = questionOrder;
    }

    @Ignore
    public SurveyQuestion(int msurveyId, String questionText, int order) {
        this.questionText = questionText;
        this.msurveyId = msurveyId;
        this.questionOrder = order;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public int getMsurveyId() {
        return msurveyId;
    }

    public void setMsurveyId(int msurveyId) {
        this.msurveyId = msurveyId;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public int getQuestionOrder() {
        return questionOrder;
    }

    public void setQuestionOrder(int questionOrder) {
        this.questionOrder = questionOrder;
    }

    @Override
    public String getListItemText() {
        return getQuestionOrder() + ": " + getQuestionText();
    }

    @Override
    public Intent getListItemEditIntent(Context context) {
        return EditQuestion.getIntent(context, getQuestionId());
    }

    @Override
    public void DeleteSelf(Context context) {
        SurveyQuestionDao questionDAO = SurveyRoomDatabase.getDatabase(context.getApplicationContext()).surveyQuestionDao();
        questionDAO.Delete(this);
        // Update order for all other questions
        List<SurveyQuestion> questionList = questionDAO.getQuestionsInSurvey(msurveyId);
        for(int i = 0; i < questionList.size(); i++) {
            questionList.get(i).setQuestionOrder(i + 1);
            questionDAO.Update(questionList.get(i));
        }
    }
}
