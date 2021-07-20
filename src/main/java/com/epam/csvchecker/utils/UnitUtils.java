package com.epam.csvchecker.utils;

import com.epam.csvchecker.entity.UnitRaw;
import com.epam.csvchecker.entity.UnitTree;
import com.epam.csvchecker.entity.csv.HierarchyCsv;
import com.epam.csvchecker.entity.csv.UnitCsv;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class UnitUtils {

    private UnitUtils() {
    }

    public static List<UnitRaw> createUnitModels(List<UnitCsv> units, List<HierarchyCsv> hierarchy) {
        List<UnitRaw> unitsRaw = units.stream()
                .map(unitCsv -> createUnitRaw(unitCsv, hierarchy))
                .collect(groupByParent())
                .values().stream()
                .peek(UnitUtils::checkStatus)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        return updateStatusParent(unitsRaw);
    }

    public static UnitTree createTree(List<UnitRaw> unitsRaw) {
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
                .isValidParent(true)
                .build();
    }

    private static List<UnitRaw> updateStatusParent(List<UnitRaw> unitsRaw) {
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

    private static UnitRaw getParent(String parentId, List<UnitRaw> unitsRaw) {
        if (parentId == null) {
            return null;
        }
        return unitsRaw.stream()
                .filter(unit -> parentId.equals(unit.getId()))
                .findFirst()
                .orElse(null);
    }

    private static void checkStatus(List<UnitRaw> unitsRaw) {
        Map<String, List<UnitRaw>> unitsWithTheSameNames = unitsRaw.stream()
                .collect(groupByName());
        unitsWithTheSameNames.values()
                .forEach(unitRaw -> {
                    if (unitRaw.size() == 1) {
                        unitRaw.forEach(unit -> unit.setValid(true));
                    }
                });
    }

    public static Collector<UnitRaw, ?, Map<String, List<UnitRaw>>> groupByParent() {
        return Collectors.groupingBy(
                UnitUtils::getParentId,
                Collectors.mapping(
                        value -> value,
                        Collectors.toList()
                )
        );
    }

    public static Collector<UnitRaw, ?, Map<String, List<UnitRaw>>> groupByName() {
        return Collectors.groupingBy(
                UnitRaw::getName,
                Collectors.mapping(
                        value -> value,
                        Collectors.toList()
                )
        );
    }

    private static String getParentId(UnitRaw value) {
        return value.getParentId() == null
                ? StringUtils.EMPTY : value.getParentId();
    }
}
