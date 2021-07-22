package com.epam.csvchecker.service.impl;

import com.epam.csvchecker.entity.csv.HierarchyCsv;
import com.epam.csvchecker.entity.csv.UnitCsv;
import com.epam.csvchecker.service.ValidationService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ValidationServiceImpl implements ValidationService {

    @Override
    public String checkUnitCsv(List<UnitCsv> units) {
        return null;
    }

    @Override
    public String checkHierarchyCsv(List<HierarchyCsv> hierarchyCsv) {
        return null;
    }
}
