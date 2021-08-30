package com.example.medibankcodingtest.HeadlinesProvider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.medibankcodingtest.Headline;
import com.example.medibankcodingtest.SourcesProvider.Sources;

import java.util.List;

@Dao
public interface HeadlineDao {

    @Query("select * from headlines")
    LiveData<List<Headlines>> getAllHeadlines();

    @Insert
    void addHeadlines(Headlines headlines);

    @Query("delete FROM headlines")
    void deleteAllHeadlines();
}
