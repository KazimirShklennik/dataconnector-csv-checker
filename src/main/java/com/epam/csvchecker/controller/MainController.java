package com.epam.csvchecker.controller;

import com.epam.csvchecker.entity.UnitRaw;
import com.epam.csvchecker.entity.csv.HierarchyCsv;
import com.epam.csvchecker.entity.csv.UnitCsv;
import com.epam.csvchecker.utils.CsvReaderUtils;
import com.epam.csvchecker.utils.UnitUtils;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Slf4j
@Controller
public class MainController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index() {
        return ViewName.INDEX.getName();
    }

    @SneakyThrows
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public ModelAndView uploadFiles(
            ModelAndView modelAndView,
            @RequestParam("unitFile") MultipartFile units,
            @RequestParam("hierarchyFile") MultipartFile hierarchy) {
        modelAndView.setViewName(ViewName.INDEX.getName());

        List<UnitCsv> unitsCsv = CsvReaderUtils.readUnits(units);
        List<HierarchyCsv> hierarchyCsv = CsvReaderUtils.readHierarchy(hierarchy);
        List<UnitRaw> unitModels = UnitUtils.createUnitModels(unitsCsv, hierarchyCsv);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        modelAndView.addObject("tree", ow.writeValueAsString(UnitUtils.createTree(unitModels)));

        return modelAndView;
    }

}
