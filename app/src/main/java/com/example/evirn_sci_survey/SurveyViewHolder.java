package com.example.evirn_sci_survey;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

public class SurveyViewHolder extends RecyclerView.ViewHolder {
    private final TextView surveyItemView;

    private SurveyViewHolder(View itemView){
        super(itemView);
        surveyItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(String text){
        surveyItemView.setText(text);
    }

    static SurveyViewHolder create(ViewGroup parent){
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_item, parent, false );
        return new SurveyViewHolder(view);
    }
}
