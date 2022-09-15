package com.szelecki.immersion.models;

public class ModelPostFromFirebase {

    private String postId;
    private String userName;
    private String authentication;
    private String profileImageURL;
    private String timeAgo;
    private String contentText;
    private String contentImageURL;
    private String category1;
    private String category2;
    private String category3;
    private int likesAmount;

    public ModelPostFromFirebase(String postId, String userName, String authentication, String profileImageURL, String timeAgo, String contentText, String contentImageURL, String category1, String category2, String category3, int likesAmount) {
        this.postId = postId;
        this.userName = userName;
        this.authentication = authentication;
        this.profileImageURL = profileImageURL;
        this.timeAgo = timeAgo;
        this.contentText = contentText;
        this.contentImageURL = contentImageURL;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.likesAmount = likesAmount;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
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
}
