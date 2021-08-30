package com.example.medibankcodingtest.SourcesProvider;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface SourceDao {

    @Query("select * from sources")
    List<Sources> getAllSources();

    @Query("select * from sources where sourceName=:name")
    List<Sources> getSourceName(String name);

    @Query("select * from sources where sourceId=:id")
    List<Sources> getSourceId(String id);

    @Query("delete from sources where sourceId=:id")
    void deleteSource(String id);

    @Insert
    void addSource(Sources sources);

    @Query("delete FROM sources")
    void deleteAllSources();
}
