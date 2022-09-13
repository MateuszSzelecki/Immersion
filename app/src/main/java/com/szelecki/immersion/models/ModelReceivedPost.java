package com.szelecki.immersion.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "received_posts")
public class ModelReceivedPost {

    @ColumnInfo(name = "post_id")
    @PrimaryKey
    @NonNull private String post_id;

    @ColumnInfo(name = "language")
    private String language;

    public ModelReceivedPost() {}

    @Ignore
    public ModelReceivedPost(@NonNull String post_id, String language) {
        this.post_id = post_id;
        this.language = language;
    }

    public String getPost_id() {
        return post_id;
    }

    public void setPost_id(String post_id) {
        this.post_id = post_id;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
