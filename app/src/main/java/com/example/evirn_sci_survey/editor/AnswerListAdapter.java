package com.example.evirn_sci_survey.editor;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import com.example.evirn_sci_survey.R;
import com.example.evirn_sci_survey.database.SurveyQuestionAnswer;

import java.util.ArrayList;

public class AnswerListAdapter extends ArrayAdapter<SurveyQuestionAnswer> {
    public AnswerListAdapter(Context context, ArrayList<SurveyQuestionAnswer> arrayList) { super(context, 0, arrayList); }

    public CheckBox checkBox;
    public EditText textBox;
    public SeekBar slider;

    private TextView sliderLbl;

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View currentView = convertView;

        if (currentView == null) {
            currentView = LayoutInflater.from(getContext()).inflate(R.layout.list_answer_layout, parent, false);
        }

        SurveyQuestionAnswer currentAnswer = getItem(position);
        String answerText = currentAnswer.getMofferedAnsText();

        checkBox = currentView.findViewById(R.id.checkbox);
        textBox = currentView.findViewById(R.id.textbox);
        slider = currentView.findViewById(R.id.slider);
        sliderLbl = currentView.findViewById(R.id.slider_lbl);

        String questionType = currentAnswer.getQuestionType();
        if(questionType != null) {
            switch (questionType) {
                case "Checkbox":
                    checkBox.setVisibility(View.VISIBLE);
                    textBox.setVisibility(View.GONE);
                    slider.setVisibility(View.GONE);
                    sliderLbl.setVisibility(View.GONE);
                    checkBox.setText(answerText);
                    break;
                case "Slider":
                    checkBox.setVisibility(View.GONE);
                    textBox.setVisibility(View.GONE);
                    sliderLbl.setVisibility(View.VISIBLE);
                    slider.setVisibility(View.VISIBLE);
                    sliderLbl.setText(answerText);
                    slider.setMax(currentAnswer.getSliderMax());
                    slider.setMin(currentAnswer.getSliderMin());
                    break;
                default:
                    defaultSetup(answerText);
                    break;
            }
        } else { defaultSetup(answerText); }

        return currentView;
    }

    private void defaultSetup(String answerText) {
        checkBox.setVisibility(View.GONE);
        textBox.setVisibility(View.VISIBLE);
        slider.setVisibility(View.GONE);
        sliderLbl.setVisibility(View.GONE);
        textBox.setHint(answerText);
    }
}
