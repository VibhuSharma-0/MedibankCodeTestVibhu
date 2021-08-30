package com.example.medibankcodingtest.SavedHeadlinesProvider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.medibankcodingtest.HeadlinesProvider.Headlines;
import com.example.medibankcodingtest.Saved;

import java.util.List;
@Dao
public interface SavedHeadlinesDao {
    @Query("select * from saved_data")
    LiveData<List<SavedHeadlines>> getAllSavedHeadlines();

    @Insert
    void addSavedHeadlines(SavedHeadlines savedHeadlines);

    @Query("delete FROM saved_data")
    void deleteAllSavedHeadlines();

    @Query("delete FROM saved_data where headlineId=:id")
    void deleteSavedHeadline(int id);
}
