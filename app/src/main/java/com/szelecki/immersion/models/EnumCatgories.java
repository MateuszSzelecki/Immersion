package com.szelecki.immersion.models;

public enum EnumCatgories {
    FOOTBALL("football");

    private String representation;

    EnumCatgories(String representation) {
        this.representation = representation;
    }

    public String getRepresentation() {
        return representation;
    }

    public void setRepresentation(String representation) {
        this.representation = representation;
    }
}
