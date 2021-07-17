package com.epam.csvchecker.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonRawValue;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Unit {

    @JsonIgnore
    private String id;

    @JsonIgnore
    private String parentId;

    private List<Unit> children;
    private String name;

    @JsonRawValue
    private String type;

    @JsonIgnore
    private String unitType;
}
