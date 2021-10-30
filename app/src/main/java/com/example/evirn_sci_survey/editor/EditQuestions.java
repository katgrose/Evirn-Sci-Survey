package com.example.evirn_sci_survey.editor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import com.example.evirn_sci_survey.MainActivity;
import com.example.evirn_sci_survey.Question;
import com.example.evirn_sci_survey.QuestionAdapter;
import com.example.evirn_sci_survey.R;
import com.example.evirn_sci_survey.database.AppDatabase;
import com.example.evirn_sci_survey.database.QuestionDAO;

import java.util.ArrayList;

public class EditQuestions extends AppCompatActivity {

    ListView mListView;
    Button mDoneBtn, mNewBtn;

    QuestionDAO questionDAO;

    QuestionAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_questions);

        mListView = findViewById(R.id.question_list);
        mDoneBtn = findViewById(R.id.done_btn);
        mNewBtn = findViewById(R.id.new_btn);

        questionDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME).allowMainThreadQueries().build().getQuestionDAO();

        refreshDisplay();

        mDoneBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = MainActivity.getIntent(EditQuestions.this);
                startActivity(intent);
            }
        });

        mNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Question q = new Question("New Question", questionDAO.getRowCount());
                questionDAO.Insert(q);
                refreshDisplay();
            }
        });
    }

    public void refreshDisplay() {
        ArrayList<Question> itemList = new ArrayList<>(questionDAO.getQuestions());
        adapter = new QuestionAdapter(this, itemList);
        mListView.setAdapter(adapter);
    }

    public static Intent getIntent(Context packageContext) {
        return new Intent(packageContext, EditQuestions.class);
    }
}