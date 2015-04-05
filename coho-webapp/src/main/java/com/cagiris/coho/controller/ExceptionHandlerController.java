/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.cagiris.coho.service.exception.HierarchyServiceException;

/**
 * @author Ashish Jindal
 *
 */
@ControllerAdvice
public class ExceptionHandlerController implements IController {

	@ExceptionHandler(HierarchyServiceException.class)
	public ModelAndView handleHierarchyServiceException (HierarchyServiceException e) {
		
		ModelAndView modelAndView = new ModelAndView("error/generic-error");
		modelAndView.addObject(ATTR_ERROR_MSG, e.getMessage());
		
		return modelAndView;
	}

	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException (Exception e) {
		
		ModelAndView modelAndView = new ModelAndView("error/generic-error");
		modelAndView.addObject(ATTR_ERROR_MSG, e.getMessage());
		
		return modelAndView;
	}
}
