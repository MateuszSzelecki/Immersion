package com.szelecki.immersion.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.szelecki.immersion.models.ModelFriendStatistic;

import java.util.List;

@Dao
public interface FriendStatisticDAO {

    @Insert
    public long addFriendStatistic(ModelFriendStatistic modelFriendStatistic);

    @Update
    public void updateFriendStatistic(ModelFriendStatistic modelFriendStatistic);

    @Delete
    public void deleteFriendStatistic(ModelFriendStatistic modelFriendStatistic);

    @Query("select * from friend_statistics where messages > :theWorstMessages")
    public List<ModelFriendStatistic> getTheBestFriendsStatistics(int theWorstMessages);

    @Query("select * from friend_statistics where language ==:language")
    public List<ModelFriendStatistic> getFriendsStatisticsForLanguage(String language);
}
