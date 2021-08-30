package com.example.medibankcodingtest.SavedHeadlinesProvider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.medibankcodingtest.HeadlinesProvider.HeadlineRepository;
import com.example.medibankcodingtest.HeadlinesProvider.Headlines;

import java.util.List;

public class SavedHeadlinesViewModel extends AndroidViewModel {

    private SavedHeadlinesRepository mRepository;
    private LiveData<List<SavedHeadlines>> mAllHeadlines;

    public SavedHeadlinesViewModel(@NonNull Application application) {
        super(application);
        mRepository = new SavedHeadlinesRepository(application);
        mAllHeadlines = mRepository.getAllSavedHeadlines();
    }
    public LiveData<List<SavedHeadlines>> getmAllSavedHeadlines() {
        return mAllHeadlines;
    }

    public void insert(SavedHeadlines savedHeadlines) {
        mRepository.insert(savedHeadlines);
    }
    public void deleteAllSavedHeadlines(){mRepository.deleteAllSavedHeadlines();}
    public void deleteSavedHeadlines(int id){mRepository.deleteSavedHeadlines(id);}
}
