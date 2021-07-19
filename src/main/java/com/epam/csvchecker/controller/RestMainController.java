package com.epam.csvchecker.controller;

import com.epam.csvchecker.entity.Unit;
import com.epam.csvchecker.entity.UnitRaw;
import com.epam.csvchecker.entity.csv.HierarchyCsv;
import com.epam.csvchecker.entity.csv.UnitCsv;
import com.epam.csvchecker.utils.CsvReaderUtils;
import com.epam.csvchecker.utils.UnitUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class RestMainController {

    @SneakyThrows
    @PostMapping(value = "/check", consumes = {"multipart/form-data"})
    public Unit uploadFiles(
            @RequestParam("unitFile") MultipartFile unitFile,
            @RequestParam("hierarchyFile") MultipartFile hierarchyFile) {

        List<UnitCsv> unitsCsv = CsvReaderUtils.readUnits(unitFile);
        List<HierarchyCsv> hierarchyCsv = CsvReaderUtils.readHierarchy(hierarchyFile);
        List<UnitRaw> unitModels = UnitUtils.createUnitModels(unitsCsv, hierarchyCsv);

        return UnitUtils.createTree(unitModels);
    }
}
