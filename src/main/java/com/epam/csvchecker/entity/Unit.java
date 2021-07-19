package com.epam.csvchecker.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class Unit {

    private String id;
    private String parentId;
    private List<Unit> children;
    private String name;
    private String unitType;
}
