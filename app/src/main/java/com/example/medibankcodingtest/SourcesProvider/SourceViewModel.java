package com.example.medibankcodingtest.SourcesProvider;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;

public class SourceViewModel extends AndroidViewModel {

    private SourceRepository mRepository;
    private List<Sources> mAllSources;

    public SourceViewModel(@NonNull Application application) {
        super(application);
        mRepository = new SourceRepository(application);
        mAllSources = mRepository.getAllSources();
    }

    public List<Sources> getmAllSources() {
        return mAllSources;
    }

    public void insert(Sources sources) {
        mRepository.insert(sources);
    }
    public void deleteSource(String id){
        mRepository.deleteSource(id);
    }
    public void deleteAllSources(){mRepository.deleteAllSources();}
}
