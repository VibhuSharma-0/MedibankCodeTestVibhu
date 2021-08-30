package com.example.medibankcodingtest.SourcesProvider;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Database(entities = {Sources.class}, version = 1)
public abstract class SourceDatabase extends RoomDatabase {
    public static final String SOURCE_DATABASE_NAME = "source_database";

    public abstract SourceDao sourceDao();

    private static volatile SourceDatabase INSTANCE;
    private static final int NUMBER_OF_THREADS = 4;
    static final ExecutorService databaseWriteExecutor =
            Executors.newFixedThreadPool(NUMBER_OF_THREADS);

    static SourceDatabase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (SourceDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            SourceDatabase.class, SOURCE_DATABASE_NAME).allowMainThreadQueries()
                            .build();
                }
            }
        }
        return INSTANCE;
    }
}
