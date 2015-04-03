/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import java.util.Map;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 *  Ashish Jindal
 *
 */
@Controller
@RequestMapping("/")
public abstract class AbstractCRUDController <T> implements IController {

	public abstract ModelAndView create(T bean, BindingResult bindingResult, ModelMap modelMap);
	
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public final ModelAndView createInternal(@Valid @ModelAttribute T bean, BindingResult bindingResult, ModelMap modelMap) {
		return create(bean, bindingResult, modelMap);
	}

	public abstract ModelAndView delete(Long entityId);

	@RequestMapping(value = "/delete/{entityId}")
	public final  ModelAndView deleteInternal(@PathVariable Long entityId) {
		return delete(entityId);
	}

	public abstract ModelAndView get(Long entityId);

	@RequestMapping(value = "/get/{entityId}")
	public final  ModelAndView getInternal(@PathVariable Long entityId) {
		return get(entityId);
	}

	public abstract ModelAndView showCreatePage(ModelMap modelMap);
	
	@RequestMapping(value = "/create", method = RequestMethod.GET)
	public final ModelAndView showCreatePageInternal(ModelMap modelMap) {
		return showCreatePage(modelMap);
	}

	public abstract ModelAndView showFilteredListPage(Map<String, String> filterParameters, ModelMap modelMap);

	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public final  ModelAndView showFilteredListPageInternal(@Valid @RequestParam Map<String, String> filterParameters, ModelMap modelMap) {
		return showFilteredListPage(filterParameters, modelMap);
	}

	public abstract ModelAndView showListPage(ModelMap modelMap);

	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public final  ModelAndView showListPageInternal(ModelMap modelMap) {
		return showListPage(modelMap);
	}
	
	public abstract ModelAndView showUpdatePage(Long entityId, ModelMap modelMap);

	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.GET)
	public final  ModelAndView showUpdatePageInternal(@PathVariable Long entityId, ModelMap modelMap) {
		return showUpdatePage(entityId, modelMap);
	}

	public abstract ModelAndView update(Long entityId, T bean, BindingResult bindingResult, ModelMap modelMap);

	@RequestMapping(value = "/update/{entityId}", method = RequestMethod.POST)
	public final  ModelAndView updateInternal(@PathVariable Long entityId, 
												@Valid @ModelAttribute T bean, 
												BindingResult bindingResult, 
												ModelMap modelMap) {
		
		return update(entityId, bean, bindingResult, modelMap);
	}
}
