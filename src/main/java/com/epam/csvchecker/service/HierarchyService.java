package com.epam.csvchecker.service;

import com.epam.csvchecker.entity.UnitRaw;
import com.epam.csvchecker.entity.UnitTree;

import java.util.List;

public interface HierarchyService {

    UnitTree createHierarchy(List<UnitRaw> unitsRaw);
}
