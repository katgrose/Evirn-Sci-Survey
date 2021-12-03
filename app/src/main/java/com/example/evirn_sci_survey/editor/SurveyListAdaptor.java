package com.example.evirn_sci_survey.editor;

import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;

import com.example.evirn_sci_survey.database.Survey;
import com.example.evirn_sci_survey.editor.SurveyViewHolder;

public class SurveyListAdaptor extends ListAdapter<Survey, SurveyViewHolder> {

    public SurveyListAdaptor(@NonNull DiffUtil.ItemCallback<Survey> diffCallback){
        super(diffCallback);
    }

    @Override
    public SurveyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return SurveyViewHolder.create(parent);
    }

    @Override
    public void onBindViewHolder(SurveyViewHolder holder, int position) {
        Survey current = getItem(position);
        holder.bind(current.getMdescription(), current);
    }

    static class SurveyDiff extends DiffUtil.ItemCallback<Survey> {

        @Override
        public boolean areItemsTheSame(@NonNull Survey oldItem, @NonNull Survey newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Survey oldItem, @NonNull Survey newItem) {
            return oldItem.getMdescription().equals(newItem.getMdescription());
        }
    }



}
