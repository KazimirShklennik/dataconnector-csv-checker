package com.epam.csvchecker.service.impl;

import com.epam.csvchecker.entity.csv.HierarchyCsv;
import com.epam.csvchecker.entity.csv.UnitCsv;
import com.epam.csvchecker.service.ValidationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class ValidationServiceImpl implements ValidationService {
    @Override
    public List<UnitCsv> getErrorUnitCsv(List<UnitCsv> units) {
        return units.stream()
                .filter(this::getErrorUnits)
                .collect(Collectors.toList());
    }

    private boolean getErrorUnits(UnitCsv unitCsv) {
        boolean isAnyNull = Stream.of(unitCsv.getUnitId(),
                unitCsv.getEntityType(),
                unitCsv.getUnitName(),
                unitCsv.getMapOfOtherUnitAttributes(),
                unitCsv.getUpdateDate())
                .anyMatch(Objects::isNull);
        return isAnyNull || !unitCsv.getEntityType().equals("unit");
    }

    @Override
    public List<HierarchyCsv> checkHierarchyCsv(List<HierarchyCsv> hierarchyCsv) {
        return null;
    }
}
