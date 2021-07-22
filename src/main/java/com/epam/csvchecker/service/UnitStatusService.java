package com.epam.csvchecker.service;

import com.epam.csvchecker.entity.UnitRaw;

import java.util.List;

public interface UnitStatusService {

    List<UnitRaw> updateValidStatusUnit(List<UnitRaw> unitsRaw);

    List<UnitRaw> updateValidStatusParentUnit(List<UnitRaw> unitsRaw);
}
