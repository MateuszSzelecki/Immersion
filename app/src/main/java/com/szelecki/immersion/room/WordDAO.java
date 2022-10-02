package com.szelecki.immersion.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.szelecki.immersion.models.ModelFriendStatistic;
import com.szelecki.immersion.models.ModelLanguageStatistic;
import com.szelecki.immersion.models.ModelWord;

import java.util.List;

@Dao
public interface WordDAO {

    @Insert
    public long addWord(ModelWord modelWord);

    @Update
    public void updateWord(ModelWord modelWord);

    @Delete
    public void deleteWord(ModelWord modelWord);

    @Query("select * from words where language == :language and recently < :extremeTime and level == :level ORDER BY recently ASC limit :limit")
    public List<ModelWord> getWordsAtTheLevel(String language, long extremeTime, int level, int limit);

    @Query("select * from words where language == :language")
    public List<ModelWord> getAllWordsForLanguage(String language);
}
