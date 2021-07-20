package com.epam.csvchecker.entity;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class UnitTree {

    private String id;
    private String parentId;
    private List<UnitTree> children;
    private String name;
    private String unitType;
    private boolean isValid;
    private boolean isValidParent;
}
