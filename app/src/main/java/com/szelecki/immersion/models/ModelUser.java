package com.szelecki.immersion.models;

import android.app.Application;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ModelUser extends Application {
    private String authentication;
    private String firstname;
    private String surname;
    private String email;
    private String password;
    private String imageURL;
    private String rotation;
    private EnumLanguages language;
    private EnumLanguages motherLanguage;
    private ArrayList<String> hobbies; //TODO: tablica modeli categories
    private ArrayList<String> languages;

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

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getRotation() {
        return rotation;
    }

    public void setRotation(String rotation) {
        this.rotation = rotation;
    }

    public EnumLanguages getLanguage() {
        return language;
    }

    public void setLanguage(EnumLanguages language) {
        this.language = language;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public EnumLanguages getMotherLanguage() {
        return motherLanguage;
    }

    public void setMotherLanguage(EnumLanguages motherLanguage) {
        this.motherLanguage = motherLanguage;
    }

    public ArrayList<String> getHobbies() {
        return hobbies;
    }

    public void setHobbies(ArrayList<String> hobbies) {
        this.hobbies = hobbies;
    }

    public ArrayList<String> getLanguages() {
        return languages;
    }

    public void setLanguages(ArrayList<String> languages) {
        this.languages = languages;
    }
}
