package com.szelecki.immersion.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.szelecki.immersion.models.ModelFriendStatistic;
import com.szelecki.immersion.models.ModelLanguageStatistic;

import java.util.List;

@Dao
public interface FriendStatisticDAO {

    @Insert
    public long addFriendStatistic(ModelFriendStatistic modelFriendStatistic);

    @Update
    public void updateFriendStatistic(ModelFriendStatistic modelFriendStatistic);

    @Delete
    public void deleteFriendStatistic(ModelFriendStatistic modelFriendStatistic);

    @Query("select * from friend_statistics ORDER BY messages DESC limit 6") //ASC - rosnąco
    public List<ModelFriendStatistic> getTheBestFriendsStatistics();

    @Query("select * from friend_statistics where language ==:language")
    public List<ModelFriendStatistic> getFriendsStatisticsForLanguage(String language);
}
