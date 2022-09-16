package com.szelecki.immersion.models;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;


@Entity(tableName = "language_statistics")
public class ModelLanguageStatistic {

    @ColumnInfo(name = "name")
    @PrimaryKey
    @NonNull private String name;

    @ColumnInfo(name = "started")
    private long started;

    @ColumnInfo(name = "recently")
    private long recently;

    @ColumnInfo(name = "words")
    private int words;

    private int image;
    private String daysFrom;
    private String daysRecently;
    private String dictionaryWords;

    public ModelLanguageStatistic() {
    }

    @Ignore
    public ModelLanguageStatistic(String name, long started, long recently, int words) {
        this.name = name;
        this.started = started;
        this.recently = recently;
        this.words = words;
    }

    @Ignore
    public ModelLanguageStatistic(String name, long started, long recently, int words, int image, String daysFrom, String daysRecently, String dictionaryWords) {
        this.name = name;
        this.started = started;
        this.recently = recently;
        this.words = words;
        this.image = image;
        this.daysFrom = daysFrom;
        this.daysRecently = daysRecently;
        this.dictionaryWords = dictionaryWords;
    }

    @Ignore
    public ModelLanguageStatistic(@NonNull String name, int image, int words) {
        this.name = name;
        this.image = image;
        this.words = words;
    }

    @Ignore
    public ModelLanguageStatistic(@NonNull String name) {
        this.name = name;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }

    public String getDaysFrom() {
        return daysFrom;
    }

    public void setDaysFrom(String daysFrom) {
        this.daysFrom = daysFrom;
    }

    public String getDaysRecently() {
        return daysRecently;
    }

    public void setDaysRecently(String daysRecently) {
        this.daysRecently = daysRecently;
    }

    public String getDictionaryWords() {
        return dictionaryWords;
    }

    public void setDictionaryWords(String dictionaryWords) {
        this.dictionaryWords = dictionaryWords;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getStarted() {
        return started;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public long getRecently() {
        return recently;
    }

    public void setRecently(long recently) {
        this.recently = recently;
    }

    public int getWords() {
        return words;
    }

    public void setWords(int words) {
        this.words = words;
    }
}
