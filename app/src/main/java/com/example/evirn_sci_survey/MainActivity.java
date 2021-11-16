package com.example.evirn_sci_survey;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultCaller;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    private SurveyViewModel mSurveyViewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
            Intent intent = new Intent(MainActivity.this, NewSurveyActivity.class);
            NewSurveyActivityResultLauncher.launch(intent);
        });
    }

    ActivityResultLauncher<Intent> NewSurveyActivityResultLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            new ActivityResultCallback<ActivityResult>(){
                @Override
                public void onActivityResult(ActivityResult result){
                    if(result.getResultCode() == Activity.RESULT_OK){
                        Intent intent = result.getData();
                        if(intent != null){
                            Survey survey = new Survey(intent.getIntExtra("surveyId", -1), intent.getStringExtra(NewSurveyActivity.EXTRA_DESCRIPTION), intent.getStringExtra(NewSurveyActivity.EXTRA_START), intent.getStringExtra(NewSurveyActivity.EXTRA_END));
                            mSurveyViewModel.insert(survey);
                        } else {
                            Toast.makeText(getApplicationContext(), R.string.empty_not_saved, Toast.LENGTH_LONG).show();
                        }
                    }
                }
            }

    );
}
