package com.epam.csvchecker.entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UnitRaw {

    private String id;
    private String parentId;
    private String name;
    private String type;
}
