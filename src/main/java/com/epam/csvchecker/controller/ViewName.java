package com.epam.csvchecker.controller;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ViewName {

    INDEX("index"),
    INDEX_REDIRECT("redirect:/"),
    DEFAULT_ERROR_VIEW("error");

    private final String name;
}
