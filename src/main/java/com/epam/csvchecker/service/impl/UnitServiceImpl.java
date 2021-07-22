package com.epam.csvchecker.service.impl;

import com.epam.csvchecker.entity.UnitRaw;
import com.epam.csvchecker.entity.csv.HierarchyCsv;
import com.epam.csvchecker.entity.csv.UnitCsv;
import com.epam.csvchecker.service.UnitService;
import com.epam.csvchecker.service.UnitStatusService;
import com.epam.csvchecker.utils.CsvReaderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.stream.Collectors;

import static com.epam.csvchecker.utils.GroupUtils.groupByParent;

@Service
@RequiredArgsConstructor
public class UnitServiceImpl implements UnitService {

    private final UnitStatusService unitStatusService;

    @Override
    public List<UnitRaw> getUnitsFromFile(MultipartFile unitFile, MultipartFile hierarchyFile) {
        List<UnitCsv> unitsCsv = CsvReaderUtils.readUnits(unitFile);
        List<HierarchyCsv> hierarchyCsv = CsvReaderUtils.readHierarchy(hierarchyFile);
        return populateUnits(unitsCsv, hierarchyCsv);
    }

    private List<UnitRaw> populateUnits(List<UnitCsv> units, List<HierarchyCsv> hierarchy) {
        List<UnitRaw> unitsRaw = units.stream()
                .map(unitCsv -> createUnitRaw(unitCsv, hierarchy))
                .collect(groupByParent())
                .values().stream()
                .peek(unitStatusService::updateValidStatusUnit)
                .flatMap(List::stream)
                .collect(Collectors.toList());

        return unitStatusService.updateValidStatusParentUnit(unitsRaw);
    }

    private UnitRaw createUnitRaw(UnitCsv unitCsv, List<HierarchyCsv> hierarchy) {

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

}

