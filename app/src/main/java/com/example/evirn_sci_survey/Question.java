package com.example.evirn_sci_survey;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.evirn_sci_survey.database.AppDatabase;

@Entity(tableName = AppDatabase.QUESTION_TABLE)
public class Question {
    @PrimaryKey(autoGenerate = true)
    private int questionId;

    private int questionOrder;
    private String questionText;

    public Question(int questionOrder) {
        this.questionText = "";
        this.questionOrder = questionOrder;
    }

    @Ignore
    public Question(String questionText, int order) {
        this.questionText = questionText;
        this.questionOrder = order;
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
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
}
