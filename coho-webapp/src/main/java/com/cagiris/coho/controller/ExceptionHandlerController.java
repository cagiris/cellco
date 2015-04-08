/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import com.cagiris.coho.service.exception.CohoException;
import com.cagiris.coho.service.exception.HierarchyServiceException;

/**
 * @author Ashish Jindal
 *
 */
@ControllerAdvice
public class ExceptionHandlerController extends AbstractController {

	@ExceptionHandler(AccessDeniedException.class)
	public ModelAndView handleAccessDeniedException(AccessDeniedException e) {
		
		ModelAndView modelAndView = new ModelAndView("error/access-denied");
		modelAndView.addObject(ATTR_ERROR_MSG, e.getMessage());
		
		return modelAndView;
	}
	
	@ExceptionHandler(Exception.class)
	public ModelAndView handleAllException (Exception e) {
		
		ModelAndView modelAndView = new ModelAndView("error/generic-error");
		modelAndView.addObject(ATTR_ERROR_MSG, "Application just ran into some trouble, "
												+ "please inform the admin if this issue persists");
		
		return modelAndView;
	}
}
