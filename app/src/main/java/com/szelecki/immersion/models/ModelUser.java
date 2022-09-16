package com.szelecki.immersion.models;

import android.app.Application;

public class ModelUser extends Application {
    private String authentication;
    private String firstname;
    private String surname;
    private String uid;
    private String imageURL;
    private EnumLanguages language;

    private static ModelUser instance;

    public static ModelUser getInstance() {
        if (instance == null) {
            instance = new ModelUser();
        }
        return instance;
    }

    public ModelUser() {
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

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public EnumLanguages getLanguage() {
        return language;
    }

    public void setLanguage(EnumLanguages language) {
        this.language = language;
    }
}
