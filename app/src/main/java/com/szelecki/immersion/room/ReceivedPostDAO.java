package com.szelecki.immersion.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.szelecki.immersion.models.ModelReceivedPost;

import java.util.List;

@Dao
public interface ReceivedPostDAO {

    @Insert
    public long addReceivedPost(ModelReceivedPost modelReceivedPost);

    @Update
    public void updateReceivedPost(ModelReceivedPost modelReceivedPost);

    @Delete
    public void deleteReceivedPost(ModelReceivedPost modelReceivedPost);

    @Query("select * from received_posts where language == :language")
    public List<ModelReceivedPost> getReceivedPosts(String language);
}
