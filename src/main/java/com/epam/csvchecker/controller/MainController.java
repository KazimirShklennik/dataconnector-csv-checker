package com.epam.csvchecker.controller;

import com.epam.csvchecker.entity.UnitTree;
import com.epam.csvchecker.entity.UnitRaw;
import com.epam.csvchecker.entity.csv.HierarchyCsv;
import com.epam.csvchecker.entity.csv.UnitCsv;
import com.epam.csvchecker.utils.CsvReaderUtils;
import com.epam.csvchecker.utils.UnitUtils;
import lombok.SneakyThrows;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
public class MainController {

    @SneakyThrows
    @PostMapping(value = "/showStructure", consumes = {"multipart/form-data"})
    public UnitTree showStructure(
            @RequestParam("unitFile") MultipartFile unitFile,
            @RequestParam("hierarchyFile") MultipartFile hierarchyFile) {

        List<UnitCsv> unitsCsv = CsvReaderUtils.readUnits(unitFile);
        List<HierarchyCsv> hierarchyCsv = CsvReaderUtils.readHierarchy(hierarchyFile);
        List<UnitRaw> unitModels = UnitUtils.createUnitModels(unitsCsv, hierarchyCsv);
        UnitTree tree = UnitUtils.createTree(unitModels);
        return UnitUtils.createTree(unitModels);
    }
}
