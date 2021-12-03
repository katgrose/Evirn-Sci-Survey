package com.example.evirn_sci_survey.editor;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.evirn_sci_survey.R;
import com.example.evirn_sci_survey.database.Survey;

public class SurveyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    private final TextView surveyItemView;
    private Survey survey;

    private SurveyViewHolder(View itemView){
        super(itemView);
        surveyItemView = itemView.findViewById(R.id.textView);
        surveyItemView.setOnClickListener(this);
    }

    public void bind(String text, Survey survey){
        surveyItemView.setText(text);
        this.survey = survey;
    }

    static SurveyViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false );
        return new SurveyViewHolder(view);
    }

    @Override
    public void onClick(View v) {
        itemView.getContext().startActivity(EditSurvey.getIntent(itemView.getContext(), survey));
    }
}
