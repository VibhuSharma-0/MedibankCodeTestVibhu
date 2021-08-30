package com.example.medibankcodingtest.SavedHeadlinesProvider;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "saved_data")
public class SavedHeadlines {
    public SavedHeadlines(String headline_title, String headline_desc, String headline_author, String headline_thumbnail, String headline_url) {
        this.headline_title = headline_title;
        this.headline_desc = headline_desc;
        this.headline_author = headline_author;
        this.headline_thumbnail = headline_thumbnail;
        this.headline_url = headline_url;
    }

    public int getHeadline_id() {
        return headline_id;
    }

    public void setHeadline_id(int headline_id) {
        this.headline_id = headline_id;
    }

    public String getHeadline_title() {
        return headline_title;
    }

    public void setHeadline_title(String headline_title) {
        this.headline_title = headline_title;
    }

    public String getHeadline_desc() {
        return headline_desc;
    }

    public void setHeadline_desc(String headline_desc) {
        this.headline_desc = headline_desc;
    }

    public String getHeadline_author() {
        return headline_author;
    }

    public void setHeadline_author(String headline_author) {
        this.headline_author = headline_author;
    }

    public String getHeadline_thumbnail() {
        return headline_thumbnail;
    }

    public void setHeadline_thumbnail(String headline_thumbnail) {
        this.headline_thumbnail = headline_thumbnail;
    }

    public String getHeadline_url() {
        return headline_url;
    }

    public void setHeadline_url(String headline_url) {
        this.headline_url = headline_url;
    }

    @NonNull
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "headlineId")
    private int headline_id;
    @ColumnInfo(name = "headlineTitle")
    private String headline_title;
    @ColumnInfo(name = "headlineDesc")
    private String headline_desc;
    @ColumnInfo(name = "headlineAuthor")
    private String headline_author;
    @ColumnInfo(name = "headlineThumbnail")
    private String headline_thumbnail;
    @ColumnInfo(name = "headlineUrl")
    private String headline_url;
}
