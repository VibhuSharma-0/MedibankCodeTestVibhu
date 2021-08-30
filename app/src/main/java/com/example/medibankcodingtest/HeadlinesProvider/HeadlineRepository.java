package com.example.medibankcodingtest.HeadlinesProvider;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.medibankcodingtest.Headline;
import com.example.medibankcodingtest.SourcesProvider.SourceDao;
import com.example.medibankcodingtest.SourcesProvider.SourceDatabase;
import com.example.medibankcodingtest.SourcesProvider.Sources;

import java.util.List;

public class HeadlineRepository {

    private HeadlineDao mHeadlineDao;
    private LiveData<List<Headlines>> mAllHeadline;

    HeadlineRepository(Application application) {
        HeadlineDatabase db = HeadlineDatabase.getDatabase(application);
        mHeadlineDao = db.HeadlineDao();
        mAllHeadline = mHeadlineDao.getAllHeadlines();
    }
    LiveData<List<Headlines>> getAllHeadlines() {
        return mAllHeadline;
    }

    void insert(Headlines headlines) {
        HeadlineDatabase.databaseWriteExecutor.execute(() -> mHeadlineDao.addHeadlines(headlines));
    }

    void deleteAllHeadlines(){
        mHeadlineDao.deleteAllHeadlines();
    }
}
