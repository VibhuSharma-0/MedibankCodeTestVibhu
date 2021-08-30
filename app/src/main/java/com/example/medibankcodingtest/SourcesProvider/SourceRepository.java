package com.example.medibankcodingtest.SourcesProvider;

import android.app.Application;
import java.util.List;


public class SourceRepository {

    private SourceDao mSourceDao;
    private List<Sources> mAllSources;

    SourceRepository(Application application) {
        SourceDatabase db = SourceDatabase.getDatabase(application);
        mSourceDao = db.sourceDao();
        mAllSources = mSourceDao.getAllSources();
    }
    List<Sources> getAllSources() {
        return mAllSources;
    }

    void insert(Sources sources) {
        SourceDatabase.databaseWriteExecutor.execute(() -> mSourceDao.addSource(sources));
    }

    void deleteSource(String id){
        SourceDatabase.databaseWriteExecutor.execute(()->{
            mSourceDao.deleteSource(id);
        });
    }
    void deleteAllSources(){
        mSourceDao.deleteAllSources();
    }

}
