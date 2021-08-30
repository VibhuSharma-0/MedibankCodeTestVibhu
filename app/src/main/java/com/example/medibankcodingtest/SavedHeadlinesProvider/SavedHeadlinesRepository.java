package com.example.medibankcodingtest.SavedHeadlinesProvider;

import android.app.Application;

import androidx.lifecycle.LiveData;


import java.util.List;

public class SavedHeadlinesRepository {

    private SavedHeadlinesDao mSavedHeadlineDao;
    private LiveData<List<SavedHeadlines>> mAllSavedHeadline;

    SavedHeadlinesRepository(Application application) {
        SavedHeadlinesDatabase db = SavedHeadlinesDatabase.getDatabase(application);
        mSavedHeadlineDao = db.savedHeadlinesDao();
        mAllSavedHeadline = mSavedHeadlineDao.getAllSavedHeadlines();
    }
    LiveData<List<SavedHeadlines>> getAllSavedHeadlines() {
        return mAllSavedHeadline;
    }

    void insert(SavedHeadlines savedHeadlines) {
        SavedHeadlinesDatabase.databaseWriteExecutor.execute(() -> mSavedHeadlineDao.addSavedHeadlines(savedHeadlines));
    }

    void deleteAllSavedHeadlines(){
        mSavedHeadlineDao.deleteAllSavedHeadlines();
    }
    void  deleteSavedHeadlines(int id) {mSavedHeadlineDao.deleteSavedHeadline(id);}
}

