package com.example.evirn_sci_survey.editor;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.evirn_sci_survey.R;
import com.example.evirn_sci_survey.database.Survey;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SurveyListActivity extends AppCompatActivity {
    private SurveyViewModel mSurveyViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey_list);

        //Implementation of the RecyclerView and ListAdapter.
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final SurveyListAdaptor adapter = new SurveyListAdaptor(new SurveyListAdaptor.SurveyDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSurveyViewModel = new ViewModelProvider(this).get(SurveyViewModel.class);

        //Update the cached copy of words in the adapter
        mSurveyViewModel.getAllSurveys().observe(this, adapter::submitList);

        //Launch activity to add survey in new activity using a fab
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> {
            Survey survey = new Survey("New Survey");
            mSurveyViewModel.insert(survey);
        });
    }

    public static Intent getIntent(Context packageContext) {
        return new Intent(packageContext, SurveyListActivity.class);
    }
}