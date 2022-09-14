package com.szelecki.immersion.models;

public class ModelPostFirebase {

    private String name;
    private String profileImageURL;
    private String authentication;
    private long published;
    private String timeAgo;
    private String contentText;
    private String contentImageURL;
    private String category1;
    private String category2;
    private String category3;
    private int likesAmount;
    private Object comments;

    //wstawiane
    public ModelPostFirebase(String name, String authentication, long published, String contentText, String contentImageURL, String category1, String category2, String category3, Object comments) {
        this.name = name;
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

    //do RecyclerView
    public ModelPostFirebase(String name, String profileImageURL, String timeAgo, String contentText, String contentImageURL, String category1, String category2, String category3, int likesAmount) {
        this.name = name;
        this.profileImageURL = profileImageURL;
        this.timeAgo = timeAgo;
        this.contentText = contentText;
        this.contentImageURL = contentImageURL;
        this.category1 = category1;
        this.category2 = category2;
        this.category3 = category3;
        this.likesAmount = likesAmount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfileImageURL() {
        return profileImageURL;
    }

    public void setProfileImageURL(String profileImageURL) {
        this.profileImageURL = profileImageURL;
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

    public String getAuthentication() {
        return authentication;
    }

    public void setAuthentication(String authentication) {
        this.authentication = authentication;
    }

    public String getTimeAgo() {
        return timeAgo;
    }

    public void setTimeAgo(String timeAgo) {
        this.timeAgo = timeAgo;
    }
}
