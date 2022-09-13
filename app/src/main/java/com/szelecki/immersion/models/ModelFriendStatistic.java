package com.szelecki.immersion.models;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "friend_statistics")
public class ModelFriendStatistic {

    @ColumnInfo(name = "id")
    @PrimaryKey
    @NonNull private String id;

    @ColumnInfo(name = "authentication")
    private String authentication;

    @ColumnInfo(name = "firstname")
    private String firstname;

    @ColumnInfo(name = "surname")
    private String surname;

    @ColumnInfo(name = "language")
    private String language;

    @ColumnInfo(name = "started")
    private long started;

    @ColumnInfo(name = "messages")
    private int messages;

    private String immersionIn;
    private String daysFrom;
    private String chatMessages;
    private String imageURL;

    public ModelFriendStatistic() {}

    @Ignore
    public ModelFriendStatistic(String authentication, String firstname, String surname, String language, long started, int messages) {
        this.id = language.substring(0,3) + authentication;
        this.authentication = authentication;
        this.firstname = firstname;
        this.surname = surname;
        this.language = language;
        this.started = started;
        this.messages = messages;
    }

    @Ignore
    public ModelFriendStatistic(String id, String authentication, String firstname, String surname, String language, long started, int messages, String immersionIn, String daysFrom, String chatMessages, String imageURL) {
        this.id = id;
        this.authentication = authentication;
        this.firstname = firstname;
        this.surname = surname;
        this.language = language;
        this.started = started;
        this.messages = messages;
        this.immersionIn = immersionIn;
        this.daysFrom = daysFrom;
        this.chatMessages = chatMessages;
        this.imageURL = imageURL;
    }

    @Ignore
    public ModelFriendStatistic(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public long getStarted() {
        return started;
    }

    public void setStarted(long started) {
        this.started = started;
    }

    public int getMessages() {
        return messages;
    }

    public void setMessages(int messages) {
        this.messages = messages;
    }

    public String getImmersionIn() {
        return immersionIn;
    }

    public void setImmersionIn(String immersionIn) {
        this.immersionIn = immersionIn;
    }

    public String getDaysFrom() {
        return daysFrom;
    }

    public void setDaysFrom(String daysFrom) {
        this.daysFrom = daysFrom;
    }

    public String getChatMessages() {
        return chatMessages;
    }

    public void setChatMessages(String chatMessages) {
        this.chatMessages = chatMessages;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    //    String countryId = LanguagesEnum.FINNISH.getDescription().substring(0, 3);
//    String name = countryId + "Mateusz";
//    Log.d("MATEUSZ", name);
//    Log.d("MATEUSZ", name.substring(3));
}
