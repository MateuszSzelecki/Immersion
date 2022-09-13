package com.szelecki.immersion.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "words")
public class ModelWord {

    @ColumnInfo(name = "global_id")
    @PrimaryKey
    @NonNull private String global_id; //id z przedrostkiem języka

    @ColumnInfo(name = "id")
    private String id; //id będące słowem z dużych liter

    @ColumnInfo(name = "word")
    private String word;

    @ColumnInfo(name = "meaning")
    private String meaning;

    @ColumnInfo(name = "language")
    private String language;

    @ColumnInfo(name = "recently")
    private long recently;

    @ColumnInfo(name = "level")
    private int level;

    public ModelWord() {}

    public ModelWord(String word, String meaning, String language, long recently, int level) {
        this.global_id = language.substring(0,3) + word.toUpperCase();
        this.id = word.toUpperCase();
        this.word = word;
        this.meaning = meaning;
        this.language = language;
        this.recently = recently;
        this.level = level;
    }

    public ModelWord(String word, String language) {
        this.global_id = language.substring(0,3) + word.toUpperCase();
    }

    public String getGlobal_id() {
        return global_id;
    }

    public void setGlobal_id(String global_id) {
        this.global_id = global_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public long getRecently() {
        return recently;
    }

    public void setRecently(long recently) {
        this.recently = recently;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }
}
