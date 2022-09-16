package com.szelecki.immersion.models;

public class ModelPostToFirebase {

    private String postId;
    private String language;
    private String userName;
    private String authentication;
    private String profileImageURL;
    private long published;
    private String contentText;
    private String contentImageURL;
    private String category1;
    private String category2;
    private String category3;
    private int likesAmount;
    private Object comments;

    public ModelPostToFirebase(String language, String userName, String authentication, long published, String contentText, String contentImageURL, String category1, String category2, String category3, Object comments) {
        this.postId = language.substring(0,3) + authentication.substring(0,3) + String.valueOf(published);
        this.language = language;
        this.userName = userName;
        this.authentication = authentication;
        this.published = published;
        this.contentText = contentText;
        this.contentImageURL = contentImageURL;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.likesAmount = 0;
        this.comments = comments;
    }

    public String getPostId() {
        return postId;
    }

    public void setPostId(String postId) {
        this.postId = postId;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
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

    public long getPublished() {
        return published;
    }

    public void setPublished(long published) {
        this.published = published;
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
