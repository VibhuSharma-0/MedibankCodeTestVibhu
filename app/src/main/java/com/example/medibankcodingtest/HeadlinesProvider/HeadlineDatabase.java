package com.example.medibankcodingtest.HeadlinesProvider;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.medibankcodingtest.SourcesProvider.SourceDao;
import com.example.medibankcodingtest.SourcesProvider.SourceDatabase;
import com.example.medibankcodingtest.SourcesProvider.Sources;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Headlines.class}, version = 1)
public abstract class HeadlineDatabase extends RoomDatabase {
    public static final String HEADLINE_DATABASE_NAME = "headline_database";

    public abstract HeadlineDao HeadlineDao();

    private static volatile HeadlineDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static HeadlineDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (HeadlineDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            HeadlineDatabase.class, HEADLINE_DATABASE_NAME).allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
