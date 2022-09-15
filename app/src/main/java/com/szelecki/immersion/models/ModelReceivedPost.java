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
    @NonNull private String postId;

    @ColumnInfo(name = "language")
    private String language;

    public ModelReceivedPost() {}

    @Ignore
    public ModelReceivedPost(String postId, String language) {
        this.postId = postId;
        this.language = language;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
