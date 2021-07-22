package com.epam.csvchecker.service.impl;

import com.epam.csvchecker.entity.UnitRaw;
import com.epam.csvchecker.service.UnitStatusService;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.epam.csvchecker.utils.GroupUtils.groupByName;

@Service
public class UnitStatusServiceImpl implements UnitStatusService {

    @Override
    public List<UnitRaw> updateValidStatusUnit(List<UnitRaw> unitsRaw) {
        unitsRaw.stream()
                .collect(groupByName()).values()
                .forEach(unitRaw -> {
                    if (unitRaw.size() == 1) {
                        unitRaw.forEach(unit -> unit.setValid(true));
                    }
                });
        return unitsRaw;
    }

    @Override
    public List<UnitRaw> updateValidStatusParentUnit(List<UnitRaw> unitsRaw) {
        unitsRaw.forEach(unitRaw -> {
            if (!unitRaw.isValid()) {
                unitRaw.setValidParent(true);
                UnitRaw parent = getParent(unitRaw.getParentId(), unitsRaw);
                while (parent != null) {
                    parent.setValidParent(false);
                    parent = getParent(parent.getParentId(), unitsRaw);
                }
            }
        });
        return unitsRaw;
    }

    private UnitRaw getParent(String parentId, List<UnitRaw> unitsRaw) {
        if (parentId == null) {
            return null;
        }
        return unitsRaw.stream()
                .filter(unit -> parentId.equals(unit.getId()))
                .findFirst()
                .orElse(null);
    }
}
