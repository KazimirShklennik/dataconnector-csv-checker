package com.epam.csvchecker.entity.csv;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class UnitCsv {

    private String unitId;
    private String entityType;
    private String unitType;
    private String unitName;
    private boolean activity;
    private String mapOfOtherUnitAttributes;
    private Date updateDate;
}
