package com.epam.csvchecker.controller;

import com.epam.csvchecker.entity.UnitRaw;
import com.epam.csvchecker.entity.UnitTree;
import com.epam.csvchecker.service.HierarchyService;
import com.epam.csvchecker.service.UnitService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class StructureController {

    private final UnitService unitService;
    private final HierarchyService hierarchyService;

    @PostMapping(value = "/structure", consumes = {"multipart/form-data"})
    public UnitTree showStructure(
            @RequestParam("unitFile") MultipartFile unitFile,
            @RequestParam("hierarchyFile") MultipartFile hierarchyFile) {
        List<UnitRaw> unitModels = unitService.getUnitsFromFile(unitFile, hierarchyFile);
        return hierarchyService.createHierarchy(unitModels);
    }
}
