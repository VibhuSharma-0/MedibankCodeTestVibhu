package com.example.medibankcodingtest.HeadlinesProvider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.medibankcodingtest.SourcesProvider.SourceRepository;
import com.example.medibankcodingtest.SourcesProvider.Sources;

import java.util.List;

public class HeadlineViewModel extends AndroidViewModel {

    private HeadlineRepository mRepository;
    private LiveData<List<Headlines>> mAllHeadlines;

    public HeadlineViewModel(@NonNull Application application) {
        super(application);
        mRepository = new HeadlineRepository(application);
        mAllHeadlines = mRepository.getAllHeadlines();
    }
    public LiveData<List<Headlines>> getmAllHeadlines() {
        return mAllHeadlines;
    }

    public void insert(Headlines headlines) {
        mRepository.insert(headlines);
    }
    public void deleteAllHeadlines(){mRepository.deleteAllHeadlines();}
}
