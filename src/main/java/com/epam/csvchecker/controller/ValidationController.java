package com.epam.csvchecker.controller;

import com.epam.csvchecker.entity.csv.HierarchyCsv;
import com.epam.csvchecker.entity.csv.UnitCsv;
import com.epam.csvchecker.service.ValidationService;
import com.epam.csvchecker.utils.CsvReaderUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ValidationController {

    private final ValidationService validationService;

    @PostMapping(value = "/error-units", consumes = {"multipart/form-data"})
    public List<UnitCsv> getErrors(
            @RequestParam("unitFile") MultipartFile unitFile) {
        List<UnitCsv> unitsCsv = CsvReaderUtils.readUnits(unitFile);
        List<UnitCsv> errorUnitCsv = validationService.getErrorUnitCsv(unitsCsv);
        return validationService.getErrorUnitCsv(unitsCsv);
    }

    @GetMapping(value = "/error-hierarchy")
    public List<HierarchyCsv> checkHierarchy(
            @RequestParam("hierarchyFile") MultipartFile hierarchyFile) {
        List<HierarchyCsv> hierarchy = CsvReaderUtils.readHierarchy(hierarchyFile);
        return validationService.checkHierarchyCsv(hierarchy);
    }
}
