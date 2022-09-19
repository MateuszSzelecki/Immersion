package com.szelecki.immersion.models;

public class ModelPostToFirebase {

    private String authentication;
    private String userName;
    private String profileImageURL;
    private long publishedDate;
    private String contentText;
    private String contentImageURL;
    private String category1;
    private String category2;
    private String category3;
    private int likesAmount;
    private Object comments;

    public ModelPostToFirebase(String authentication, String userName, String profileImageURL, long publishedDate, String contentText, String contentImageURL, String category1, String category2, String category3) {
        this.authentication = authentication;
        this.userName = userName;
        this.profileImageURL = profileImageURL;
        this.publishedDate = publishedDate;
        this.contentText = contentText;
        this.contentImageURL = contentImageURL;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.likesAmount = 0;
        this.comments = "";
    }

    public String getPostId(String language) {
        return language.substring(0,3) + this.authentication.substring(0,3) + String.valueOf(this.publishedDate);
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
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

    public long getPublishedDate() {
        return publishedDate;
    }

    public void setPublishedDate(long publishedDate) {
        this.publishedDate = publishedDate;
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

    public Object getComments() {
        return comments;
    }

    public void setComments(Object comments) {
        this.comments = comments;
    }
}
