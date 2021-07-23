package com.epam.csvchecker.utils;

import com.epam.csvchecker.entity.csv.HierarchyCsv;
import com.epam.csvchecker.entity.csv.UnitCsv;
import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
public class CsvReaderUtils {

    private static final CSVParser csvParser;
    private static final DateFormat dateFormat;
    private static final int UNIT_FIELDS_SIZE = 7;
    private static final int HIERARCHY_FIELDS_SIZE = 9;
    private static final char CSV_SEPARATOR = ';';
    private static final String DATE_PATTERN = "yyyy-MM-dd HH:mm";

    private CsvReaderUtils() {
    }

    static {
        dateFormat = new SimpleDateFormat(DATE_PATTERN);
        csvParser = new CSVParserBuilder()
                .withSeparator(CSV_SEPARATOR)
                .build();
    }

    @SneakyThrows
    public static List<UnitCsv> readUnits(MultipartFile unitsFile) {

        try (CSVReader reader = new CSVReaderBuilder(
                new FileReader(getFile(unitsFile)))
                .withCSVParser(csvParser)
                .withSkipLines(1)
                .build()) {

            return reader.readAll()
                    .stream()
                    .map(Arrays::asList)
                    .map(CsvReaderUtils::createUnitCsvModel)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
    }

    @SneakyThrows
    public static List<HierarchyCsv> readHierarchy(MultipartFile hierarchyFile) {

        try (CSVReader reader = new CSVReaderBuilder(
                new FileReader(getFile(hierarchyFile)))
                .withCSVParser(csvParser)
                .withSkipLines(1)
                .build()) {

            return reader.readAll()
                    .stream()
                    .map(Arrays::asList)
                    .map(CsvReaderUtils::createHierarchyCsvModel)
                    .filter(Objects::nonNull)
                    .collect(Collectors.toList());
        }
    }

    private static UnitCsv createUnitCsvModel(List<String> unit) {

        if (unit.size() < UNIT_FIELDS_SIZE) {
            log.warn("Check unit.scv. Line: {}", unit);
            return null;
        }
        Date date=null;
        try {
           date=dateFormat.parse(unit.get(6).trim());
        } catch (ParseException e) {
            log.warn("Check date. Line: {}", unit);
        }
        return UnitCsv.builder()
                .unitId(unit.get(0).trim())
                .entityType(unit.get(1).trim())
                .unitType(unit.get(2).trim())
                .unitName(unit.get(3).trim())
                .activity(Boolean.parseBoolean(unit.get(4).trim()))
                .mapOfOtherUnitAttributes(unit.get(5).trim())
                .updateDate(date)
                .build();
    }

    @SneakyThrows
    private static HierarchyCsv createHierarchyCsvModel(List<String> hierarchy) {

        if (hierarchy.size() < HIERARCHY_FIELDS_SIZE) {
            log.warn("Check unithierarchy.scv. Line: {}", hierarchy);
            return null;
        }

        return HierarchyCsv.builder()
                .entityId(hierarchy.get(0).trim())
                .idOfHierarchy(hierarchy.get(1).trim())
                .entityType(hierarchy.get(2).trim())
                .activity(Boolean.parseBoolean(hierarchy.get(3).trim()))
                .unitId(hierarchy.get(4).trim())
                .unitType(hierarchy.get(5).trim())
                .parentUnitId(hierarchy.get(6).trim())
                .parentUnitType(hierarchy.get(7).trim())
                .updateDate(dateFormat.parse(hierarchy.get(8).trim()))
                .build();
    }

    private static File getFile(MultipartFile file) throws IOException {
        InputStream initialStream = file.getInputStream();
        byte[] buffer = new byte[initialStream.available()];
        initialStream.read(buffer);
        File targetFile = new File(file.getName());
        OutputStream outStream = new FileOutputStream(targetFile);
        outStream.write(buffer);
        return targetFile;
    }
}
