package com.epam.csvchecker.utils;

import com.epam.csvchecker.entity.Unit;
import com.epam.csvchecker.entity.UnitRaw;
import com.epam.csvchecker.entity.csv.HierarchyCsv;
import com.epam.csvchecker.entity.csv.UnitCsv;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UnitUtils {

    private static final String JSON_FIELD_TYPE = "Tree.FOLDER";
    private static final String UNIT_NAME_TEMPLATE = "%s (%s)";

    private UnitUtils() {
    }

    public static List<UnitRaw> createUnitModels(List<UnitCsv> units, List<HierarchyCsv> hierarchy) {
        return units.stream()
                .map(unitCsv -> createUnitRaw(unitCsv, hierarchy))
                .collect(Collectors.toList());
    }

    private static UnitRaw createUnitRaw(UnitCsv unitCsv, List<HierarchyCsv> hierarchy) {

        String parentUnitId = hierarchy.stream()
                .filter(hierarchyCsv -> hierarchyCsv.getUnitId().equals(unitCsv.getUnitId())
                        && hierarchyCsv.getUnitType().equals(unitCsv.getUnitType()))
                .findFirst()
                .map(HierarchyCsv::getParentUnitId)
                .orElse(null);

        return UnitRaw.builder()
                .id(unitCsv.getUnitId())
                .parentId(parentUnitId)
                .name(unitCsv.getUnitName())
                .type(unitCsv.getUnitType())
                .build();
    }

    public static Unit createTree(List<UnitRaw> unitRaws) {
        Map<String, Unit> mapUnits = unitRaws.stream()
                .collect(Collectors.toMap(
                        UnitRaw::getId,
                        unitRaw -> Unit.builder()
                                .id(unitRaw.getId())
                                .parentId(unitRaw.getParentId())
                                .children(new ArrayList<>())
                                .unitType(unitRaw.getType())
                                .name(String.format(UNIT_NAME_TEMPLATE, unitRaw.getName(), unitRaw.getType()))
                                .build()
                        )
                );

        List<Unit> roots = new ArrayList<>();
        unitRaws.forEach(nodeRaw -> {
            Unit unit = mapUnits.get(nodeRaw.getId());
            if (nodeRaw.getParentId() == null) {
                roots.add(unit);
            } else {
                Unit parent = mapUnits.get(nodeRaw.getParentId());
                if (parent != null) {
                    parent.getChildren().add(unit);
                }
            }
        });

        updateTree(roots);
        return roots.stream()
                .findFirst()
                .orElse(null);
    }

    private static void updateTree(List<Unit> unitTree) {
        unitTree.forEach(unit -> {
            if (!unit.getChildren().isEmpty()) {
                unit.setType(JSON_FIELD_TYPE);
                updateTree(unit);
            }
        });
    }

    private static void updateTree(Unit unitTree) {
        if (!unitTree.getChildren().isEmpty()) {
            updateTree(unitTree.getChildren());
        } else {
            unitTree.setType(JSON_FIELD_TYPE);
        }
    }
}
