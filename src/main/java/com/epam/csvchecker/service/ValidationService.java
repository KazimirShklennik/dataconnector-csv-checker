package com.epam.csvchecker.service;

import com.epam.csvchecker.entity.csv.HierarchyCsv;
import com.epam.csvchecker.entity.csv.UnitCsv;

import java.util.List;

public interface ValidationService {

    List<String> checkUnitCsv(List<UnitCsv> units);

    String checkHierarchyCsv(List<HierarchyCsv> hierarchyCsv);

//    String checkUserCsv(List<UnitCsv> units);
//
//    String checkAssignmentCsv(List<UnitCsv> units);
}
