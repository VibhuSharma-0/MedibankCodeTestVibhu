package com.example.medibankcodingtest.SavedHeadlinesProvider;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.medibankcodingtest.HeadlinesProvider.HeadlineDao;
import com.example.medibankcodingtest.HeadlinesProvider.HeadlineDatabase;
import com.example.medibankcodingtest.HeadlinesProvider.Headlines;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {SavedHeadlines.class}, version = 1)
public abstract class SavedHeadlinesDatabase extends RoomDatabase {
    public static final String SAVED_HEADLINE_DATABASE_NAME = "saved_headline_database";

    public abstract SavedHeadlinesDao savedHeadlinesDao();

    private static volatile SavedHeadlinesDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static SavedHeadlinesDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SavedHeadlinesDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SavedHeadlinesDatabase.class, SAVED_HEADLINE_DATABASE_NAME).allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
