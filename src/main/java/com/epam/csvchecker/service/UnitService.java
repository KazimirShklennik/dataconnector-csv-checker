package com.epam.csvchecker.service;

import com.epam.csvchecker.entity.UnitRaw;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UnitService {

    List<UnitRaw> getUnitsFromFile(MultipartFile unitFile, MultipartFile hierarchyFile);
}
