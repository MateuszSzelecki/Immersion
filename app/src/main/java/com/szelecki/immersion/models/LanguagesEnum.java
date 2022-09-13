package com.szelecki.immersion.models;

public enum LanguagesEnum {
    DANISH("danish", "Dansk"),
    ENGLISH("english", "English"),
    FINNISH("finnish", "Suomalainen"),
    FRENCH("french", "Français"),
    GERMAN("german", "Deutsch"),
    GREEK("greek", "Ελληνικά"),
    ITALIAN("italian", "Italiano"),
    NORWEGIAN("norwegian", "Norsk"),
    POLISH("polish", "Polski"),
    SPANISH("spanish", "Español"),
    SWEDISH("swedish", "Svenska"),
    DEFAULT("default", "default");

    private String description;
    private String representation;

    LanguagesEnum(String description, String representation) {
        this.description = description;
        this.representation = representation;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }
}
