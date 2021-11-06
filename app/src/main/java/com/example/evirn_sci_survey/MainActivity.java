package com.example.evirn_sci_survey;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Implementation of the RecyclerView and ListAdapter.
        RecyclerView recyclerView = findViewById(R.id.recyclerview);
        final SurveyListAdaptor adapter = new SurveyListAdaptor(new SurveyListAdaptor.SurveyDiff());
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}