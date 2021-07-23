package com.epam.csvchecker.service.impl;

import com.epam.csvchecker.entity.csv.HierarchyCsv;
import com.epam.csvchecker.entity.csv.UnitCsv;
import com.epam.csvchecker.service.ValidationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public List<String> checkUnitCsv(List<UnitCsv> units) {

        return units.stream()
                .map(UnitCsv::getUnitName)
                .limit(5)
                .collect(Collectors.toList());
    }

    @Override
    public String checkHierarchyCsv(List<HierarchyCsv> hierarchyCsv) {
        return null;
    }
}
