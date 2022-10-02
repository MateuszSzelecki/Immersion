package com.szelecki.immersion.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.szelecki.immersion.models.ModelCategoryStatistic;
import com.szelecki.immersion.models.ModelWord;

import java.util.List;

@Dao
public interface CategoryStatisticDAO {

    @Insert
    public long addCategoryStatistic(ModelCategoryStatistic modelCategoryStatistic);

    @Update
    public void updateCategoryStatistic(ModelCategoryStatistic modelCategoryStatistic);

    @Delete
    public void deleteCategory(ModelCategoryStatistic modelCategoryStatistic);

    @Query("select * from category_statistics where membership == :membership")
    public List<ModelCategoryStatistic> getCategoriesForMembership(String membership);

    @Query("select * from category_statistics")
    public List<ModelCategoryStatistic> getAllCategories();

    @Query("select * from category_statistics where users == :users")
    public List<ModelCategoryStatistic> getUsersCategories(boolean users);
}
