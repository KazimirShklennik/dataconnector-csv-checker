package com.epam.csvchecker.controller;

public enum AttributeName {

    EXCEPTION("exception"),
    DATE_TIME("date"),
    URL("url"),
    CODE("code"),
    HOSTS("hosts"),
    SUCCESSFULLY("successfully");

    private String name;

    AttributeName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
