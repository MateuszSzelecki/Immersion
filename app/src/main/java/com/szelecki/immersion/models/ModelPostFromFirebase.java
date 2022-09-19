package com.szelecki.immersion.models;

import java.util.ArrayList;

public class ModelPostFromFirebase {

    private String userName;
    private String profileImageURL;
    private String timeAgo;
    private String contentText;
    private String contentImageURL;
    private String category1;
    private String category2;
    private String category3;
    private int likesAmount;
    private ArrayList<String> words;

    public ModelPostFromFirebase(String userName, String profileImageURL, String timeAgo, String contentText, String contentImageURL, String category1, String category2, String category3, int likesAmount) {
        this.userName = userName;
        this.profileImageURL = profileImageURL;
        this.timeAgo = timeAgo;
        this.contentText = contentText;
        this.contentImageURL = contentImageURL;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.likesAmount = likesAmount;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }

    public String getContentText() {
        return contentText;
    }

    public void setContentText(String contentText) {
        this.contentText = contentText;
    }

    public String getContentImageURL() {
        return contentImageURL;
    }

    public void setContentImageURL(String contentImageURL) {
        this.contentImageURL = contentImageURL;
    }

    public String getCategory1() {
        return category1;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public String getCategory2() {
        return category2;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public String getCategory3() {
        return category3;
    }

    public void setCategory3(String category3) {
        this.category3 = category3;
    }

    public int getLikesAmount() {
        return likesAmount;
    }

    public void setLikesAmount(int likesAmount) {
        this.likesAmount = likesAmount;
    }

    public ArrayList<String> getWords() {
        return words;
    }

    public void setWords(ArrayList<String> words) {
        this.words = words;
    }
}
