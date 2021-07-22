package com.epam.csvchecker.service.impl;

import com.epam.csvchecker.entity.UnitRaw;
import com.epam.csvchecker.entity.UnitTree;
import com.epam.csvchecker.service.HierarchyService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class HierarchyServiceImpl implements HierarchyService {

    @Override
    public UnitTree createHierarchy(List<UnitRaw> unitsRaw) {
        Map<String, UnitTree> mapUnits = unitsRaw.stream()
                .collect(Collectors.toMap(
                        UnitRaw::getId,
                        unitRaw -> UnitTree.builder()
                                .id(unitRaw.getId())
                                .parentId(unitRaw.getParentId())
                                .children(new ArrayList<>())
                                .unitType(unitRaw.getType())
                                .name(unitRaw.getName())
                                .isValid(unitRaw.isValid())
                                .isValidParent(unitRaw.isValidParent())
                                .build()
                        )
                );

        List<UnitTree> roots = new ArrayList<>();
        unitsRaw.forEach(nodeRaw -> {
            UnitTree unitTree = mapUnits.get(nodeRaw.getId());
            if (nodeRaw.getParentId() == null) {
                roots.add(unitTree);
            } else {
                UnitTree parent = mapUnits.get(nodeRaw.getParentId());
                if (parent != null) {
                    parent.getChildren().add(unitTree);
                }
            }
        });
        return roots.stream()
                .findFirst()
                .orElse(null);
    }
}
