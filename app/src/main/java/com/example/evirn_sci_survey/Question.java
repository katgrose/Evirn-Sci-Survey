package com.example.evirn_sci_survey;

import android.content.Context;
import android.content.Intent;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;
import androidx.room.Room;

import com.example.evirn_sci_survey.database.AppDatabase;
import com.example.evirn_sci_survey.database.QuestionDAO;
import com.example.evirn_sci_survey.editor.EditQuestion;

import java.util.List;

@Entity(tableName = AppDatabase.QUESTION_TABLE)
public class Question implements EditListItem {
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
        QuestionDAO questionDAO = Room.databaseBuilder(context, AppDatabase.class, AppDatabase.DB_NAME).allowMainThreadQueries().build().getQuestionDAO();
        questionDAO.Delete(this);
        // Update order for all other questions
        List<Question> questionList = questionDAO.getQuestions();
        for(int i = 0; i < questionList.size(); i++) {
            questionList.get(i).setQuestionOrder(i + 1);
            questionDAO.Update(questionList.get(i));
        }
    }
}
