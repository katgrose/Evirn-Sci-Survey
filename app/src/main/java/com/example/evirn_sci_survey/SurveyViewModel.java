package com.example.evirn_sci_survey;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.evirn_sci_survey.database.SurveyRepository;

import java.util.List;

public class SurveyViewModel extends AndroidViewModel {
    private SurveyRepository mRepository;
    private final LiveData<List<Survey>> mAllSurveys;

    public SurveyViewModel(Application application){
        super(application);
        mRepository = new SurveyRepository(application);
        mAllSurveys = mRepository.getAllSurveys();
    }

    LiveData<List<Survey>> getAllSurveys (){return mAllSurveys;}

    public void insert(Survey survey){mRepository.insert(survey);}
}
