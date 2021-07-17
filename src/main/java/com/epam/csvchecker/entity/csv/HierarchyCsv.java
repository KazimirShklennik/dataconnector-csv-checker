package com.epam.csvchecker.entity.csv;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class HierarchyCsv {

    private String entityId;
    private String idOfHierarchy;
    private String entityType;
    private boolean activity;
    private String unitId;
    private String unitType;
    private String parentUnitId;
    private String parentUnitType;
    private Date updateDate;
}
