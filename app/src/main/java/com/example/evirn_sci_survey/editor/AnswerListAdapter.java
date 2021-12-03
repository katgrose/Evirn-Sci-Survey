package com.example.evirn_sci_survey.editor;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.example.evirn_sci_survey.R;
import com.example.evirn_sci_survey.database.SurveyQuestionAnswer;

import java.util.ArrayList;

public class AnswerListAdapter extends ArrayAdapter<SurveyQuestionAnswer> {
    public AnswerListAdapter(Context context, ArrayList<SurveyQuestionAnswer> arrayList) { super(context, 0, arrayList); }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View currentView = convertView;

        if (currentView == null) {
            currentView = LayoutInflater.from(getContext()).inflate(R.layout.list_answer_layout, parent, false);
        }

        SurveyQuestionAnswer currentAnswer = getItem(position);
        String answerText = currentAnswer.getMofferedAnsText();

        CheckBox checkBox = currentView.findViewById(R.id.checkbox);
        EditText textView = currentView.findViewById(R.id.answer_lbl);

        if(currentAnswer.isCheckbox()) {
            checkBox.setVisibility(View.VISIBLE);
            textView.setVisibility(View.GONE);
            checkBox.setText(answerText);
        } else {
            checkBox.setVisibility(View.GONE);
            textView.setVisibility(View.VISIBLE);
            textView.setHint(answerText);
        }
        return currentView;
    }
}
