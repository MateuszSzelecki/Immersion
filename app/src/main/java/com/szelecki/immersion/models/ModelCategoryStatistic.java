package com.szelecki.immersion.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "category_statistics")
public class ModelCategoryStatistic {

    @ColumnInfo(name = "id")
    @PrimaryKey
    @NonNull private String id;

    @ColumnInfo(name = "category")
    private String category;

    @ColumnInfo(name = "membership")
    private String membership;

    @ColumnInfo(name = "points")
    private int points;

    @ColumnInfo(name = "users")
    private boolean users;

    public ModelCategoryStatistic() {}

    @Ignore
    public ModelCategoryStatistic(String category, String membership) {
        this.id = membership.toUpperCase() + "_" + category.substring(0,3).toUpperCase();
        this.category = category;
        this.membership = membership;
    }

    @Ignore
    public ModelCategoryStatistic(String category, String membership, int points, boolean users) {
        this.id = membership.toUpperCase() + "_" + category.substring(0,3).toUpperCase();
        this.category = category;
        this.membership = membership;
        this.points = points;
        this.users = users;
    }

    @NonNull
    public String getId() {
        return id;
    }

    public void setId(@NonNull String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getMembership() {
        return membership;
    }

    public void setMembership(String membership) {
        this.membership = membership;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public boolean isUsers() {
        return users;
    }

    public void setUsers(boolean users) {
        this.users = users;
    }
}
