/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

import com.cagiris.coho.service.exception.NoActiveShiftForUserException;

/**
 * @author Ashish Jindal
 *
 */
@ControllerAdvice
public class ExceptionHandlerController extends AbstractController {
    private Logger logger = LoggerFactory.getLogger(ExceptionHandlerController.class);

    @ExceptionHandler(AccessDeniedException.class)
    public ModelAndView handleAccessDeniedException(AccessDeniedException e) {
        logger.error("Error:{}", e.getMessage(), e);
        ModelAndView modelAndView = new ModelAndView("error/access-denied");
        modelAndView.addObject(ATTR_ERROR_MSG, e.getMessage());

        return modelAndView;
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView handleAllException(Exception e) {
        logger.error("Error:{}", e.getMessage(), e);
        ModelAndView modelAndView = new ModelAndView("error/generic-error");
        modelAndView.addObject(ATTR_ERROR_MSG, "Application just ran into some trouble, "
                + "please inform the admin if this issue persists");

        return modelAndView;
    }

    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(NoActiveShiftForUserException.class)
    public void handleNoShiftForUserException(NoActiveShiftForUserException e) {
        logger.error("Error:{}", e.getMessage(), e);
    }
}
