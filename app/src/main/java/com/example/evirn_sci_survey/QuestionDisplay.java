package com.example.evirn_sci_survey;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.evirn_sci_survey.database.AppDatabase;
import com.example.evirn_sci_survey.database.QuestionDAO;

public class QuestionDisplay extends AppCompatActivity {
    public static final String QUESTION_ID = "evirn_sci_survey.QuestionID";
    QuestionDAO questionDAO;

    TextView questionLbl;
    Button nextBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_display);

        int questionOrder = getIntent().getIntExtra(getString(R.string.saved_question_order), -1);
        questionDAO = Room.databaseBuilder(this, AppDatabase.class, AppDatabase.DB_NAME).allowMainThreadQueries().build().getQuestionDAO();
        Question question = questionDAO.getQuestionFromOrder(questionOrder);

        if(question == null) {
            returnToMain();
        } else {
            questionLbl = findViewById(R.id.question_lbl);
            questionLbl.setText(question.getQuestionText());

            nextBtn = findViewById(R.id.nextBtn);
            nextBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = getIntent(QuestionDisplay.this);
                    intent.putExtra(getString(R.string.saved_question_order), questionOrder+1);
                    startActivity(intent);
                }
            });
        }
    }

    private void returnToMain() {
        Intent intent = MainActivity.getIntent(QuestionDisplay.this);
        startActivity(intent);
    }

    public static Intent getIntent(Context packageContext) {
        return new Intent(packageContext, QuestionDisplay.class);
    }
}