package com.epam.csvchecker.entity;

import com.fasterxml.jackson.annotation.JsonRawValue;

import java.util.List;


public class UnitJson {

    private List<Unit> children;
    private String name;

    @JsonRawValue
    private String type;
}


