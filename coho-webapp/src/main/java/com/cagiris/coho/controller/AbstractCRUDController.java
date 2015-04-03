/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import javax.validation.Valid;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 *  Ashish Jindal
 *
 */
public abstract class AbstractCRUDController <T> implements IController {

	public static final String CREATE_URL_MAPPING = "/create";
	public static final String GET_URL_MAPPING = "/get";
	public static final String UPDATE_URL_MAPPING = "/update";
	public static final String DELETE_URL_MAPPING = "/delete";
	public static final String LIST_URL_MAPPING = "/list";
	
	/**
	 * Handle the service call for creating a entity.
	 * @param bean - Bean which contains the data entered in form.
	 * @param bindingResult - Any validation errors while putting data in bean.
	 * @param modelMap
	 * @return - A model map containing attributes that need to be brought to view.
	 */
	public abstract ModelMap create(T bean, BindingResult bindingResult, ModelMap modelMap);
	
	@RequestMapping(value = CREATE_URL_MAPPING, method = RequestMethod.POST)
	public final ModelAndView createInternal(@Valid @ModelAttribute T bean, BindingResult bindingResult, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(getURLMapping() + CREATE_URL_MAPPING);
		
		if (!bindingResult.hasErrors()) {
			modelAndView.addAllObjects(create(bean, bindingResult, modelMap));
		} else {
			modelAndView.addObject(bean);
		}
		
		return modelAndView;
	}
	
	/**
	 * Handle the service call for delete.
	 * 
	 * @param entityId - ID of the entity to be deleted.
	 * @return - An instance of the bean containing the deleted data, which will be served as JSON to view.
	 */
	public abstract T delete(Long entityId);

	@RequestMapping(value = (DELETE_URL_MAPPING + "/{entityId}"), method = RequestMethod.POST)
	@ResponseBody
	public final  T deleteInternal(@PathVariable Long entityId) {
		return delete(entityId);
	}

	/**
	 * Get the data related to an entity.
	 * 
	 * @param entityId - ID of the entity to be loaded.
	 * @return - A model map containing an attribute with bean data (From entity).
	 */
	public abstract ModelMap get(Long entityId);

	@RequestMapping(value = (GET_URL_MAPPING + "/{entityId}"))
	public final  ModelAndView getInternal(@PathVariable Long entityId) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(getURLMapping() + GET_URL_MAPPING);
		modelAndView.addAllObjects(get(entityId));
		
		return modelAndView;
	}

	/**
	 * @return The parent view name.
	 */
	public abstract String getURLMapping();

	/**
	 * Show the form for creating a new entity.
	 * 
	 * @param modelMap - Data in the model map of request.
	 * @return - A model map containing an instance of the bean used to map the form.
	 */
	public abstract ModelMap showCreatePage(ModelMap modelMap);
	
	@RequestMapping(value = CREATE_URL_MAPPING, method = RequestMethod.GET)
	public final ModelAndView showCreatePageInternal(ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(getURLMapping() + CREATE_URL_MAPPING);
		modelAndView.addAllObjects(showCreatePage(modelMap));
		
		return modelAndView;
	}

	
	/**
	 * Show a list after handling the post request to filter the data in list.
	 * 
	 * @param bean - Bean containing the filter parameters.
	 * 
	 * @param bindingResult - Any errors while mapping the filter parameters in the bean to the form data.
	 * @param modelMap - Request scoped data.
	 * @return - A model map containing the filtered list of data.
	 */
	public abstract ModelMap showFilteredListPage(T bean, BindingResult bindingResult, ModelMap modelMap);

	@RequestMapping(value = LIST_URL_MAPPING, method = RequestMethod.POST)
	public final  ModelAndView showFilteredListPageInternal(@Valid @ModelAttribute T bean, BindingResult bindingResult, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(getURLMapping() + LIST_URL_MAPPING);
		modelAndView.addAllObjects(showFilteredListPage(bean, bindingResult, modelMap));
		
		return modelAndView;
	}

	/**
	 * Show the list page.
	 * 
	 * @param modelMap - Data in request scope.
	 * @return - A model map containing the list of data.
	 */
	public abstract ModelMap showListPage(ModelMap modelMap);

	@RequestMapping(value = LIST_URL_MAPPING, method = RequestMethod.GET)
	public final  ModelAndView showListPageInternal(ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(getURLMapping() + LIST_URL_MAPPING);
		modelAndView.addAllObjects(showListPage(modelMap));
		
		return modelAndView;
	}
	
	/**
	 * Show update page.
	 * 
	 * @param entityId - ID of the entity to be updated.
	 * 
	 * @param modelMap - Request scope data.
	 * @return - A model map containing an instance of the bean to be mapped with the input form.
	 */
	public abstract ModelMap showUpdatePage(Long entityId, ModelMap modelMap);

	@RequestMapping(value = (UPDATE_URL_MAPPING + "/{entityId}"), method = RequestMethod.GET)
	public final  ModelAndView showUpdatePageInternal(@PathVariable Long entityId, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(getURLMapping() + UPDATE_URL_MAPPING);
		modelAndView.addAllObjects(showUpdatePage(entityId, modelMap));
		
		return modelAndView;
	}

	/**
	 * Handle the update entity request.
	 * 
	 * @param entityId - ID of the entity to be update.
	 * @param bean - Bean containing the updated data.
	 * @param bindingResult - Contains any error while mapping the bean with the input form data.
	 * @param modelMap - Request scope data.
	 * @return - A model map containing any attributes (Success/error message etc) to be sent to view.
	 */
	public abstract ModelMap update(Long entityId, T bean, BindingResult bindingResult, ModelMap modelMap);

	@RequestMapping(value = (UPDATE_URL_MAPPING + "/{entityId}"), method = RequestMethod.POST)
	public final  ModelAndView updateInternal(@PathVariable Long entityId, 
												@Valid @ModelAttribute T bean, 
												BindingResult bindingResult, 
												ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(getURLMapping() + UPDATE_URL_MAPPING);
		modelAndView.addAllObjects(update(entityId, bean, bindingResult, modelMap));
		
		return modelAndView;
	}
}
