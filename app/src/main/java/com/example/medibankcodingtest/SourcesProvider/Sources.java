package com.example.medibankcodingtest.SourcesProvider;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "sources")
public class Sources {

    public String getSource_id() {
        return source_id;
    }

    public void setSource_id(String source_id) {
        this.source_id = source_id;
    }

    public String getSource_name() {
        return source_name;
    }

    public void setSource_name(String source_name) {
        this.source_name = source_name;
    }

    public Sources(String source_id, String source_name) {
        this.source_id = source_id;
        this.source_name = source_name;
    }

    @NonNull
    @PrimaryKey
    @ColumnInfo(name = "sourceId")
    private String source_id;
    @ColumnInfo(name = "sourceName")
    private String source_name;
}
