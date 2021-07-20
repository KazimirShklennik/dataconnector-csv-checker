package com.epam.csvchecker.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.time.LocalDate;

@Slf4j
@ControllerAdvice(basePackages = {"com.epam.cdvchecker"})
public class ExceptionHandlingController {

    @ExceptionHandler(value = {Exception.class})
    public ModelAndView defaultErrorHandler(HttpServletRequest request, Exception e) {
        ModelAndView modelAndView = new ModelAndView(ViewName.DEFAULT_ERROR_VIEW.getName());
        modelAndView.addObject(AttributeName.DATE_TIME.getName(), LocalDate.now());
        modelAndView.addObject(AttributeName.EXCEPTION.getName(), e.getMessage());
        modelAndView.addObject(AttributeName.CODE.getName(), HttpStatus.INTERNAL_SERVER_ERROR);
        modelAndView.addObject(AttributeName.URL.getName(), request.getRequestURL());
        log.error(e.getMessage());
        return modelAndView;
    }
}
