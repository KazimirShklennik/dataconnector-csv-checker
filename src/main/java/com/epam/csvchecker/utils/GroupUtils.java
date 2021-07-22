package com.epam.csvchecker.utils;

import com.epam.csvchecker.entity.UnitRaw;
import org.apache.commons.lang3.StringUtils;

import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class GroupUtils {

    private GroupUtils() {
    }

    public static Collector<UnitRaw, ?, Map<String, List<UnitRaw>>> groupByParent() {
        return Collectors.groupingBy(
                GroupUtils::getParentId,
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
