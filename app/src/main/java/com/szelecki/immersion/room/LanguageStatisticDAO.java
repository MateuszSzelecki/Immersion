package com.szelecki.immersion.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.szelecki.immersion.models.ModelLanguageStatistic;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface LanguageStatisticDAO {

    @Insert
    public long addLanguageStatistic(ModelLanguageStatistic modelLanguageStatistic);

    @Update
    public void updateLanguageStatistic(ModelLanguageStatistic modelLanguageStatistic);

    @Delete
    public void deleteLanguageStatistic(ModelLanguageStatistic modelLanguageStatistic);

    @Query("select * from language_statistics")
    public List<ModelLanguageStatistic> getLanguagesStatistics();
}
