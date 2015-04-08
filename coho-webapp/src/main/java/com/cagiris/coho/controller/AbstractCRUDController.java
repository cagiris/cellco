/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import java.io.Serializable;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cagiris.coho.model.ICRUDBean;
import com.cagiris.coho.service.exception.CohoException;

/**
 *  Ashish Jindal
 *
 */
public abstract class AbstractCRUDController<T extends ICRUDBean> extends AbstractController {

    public static final String CREATE_URL_MAPPING = "/create";
    public static final String GET_URL_MAPPING = "/get";
    public static final String UPDATE_URL_MAPPING = "/update";
    public static final String DELETE_URL_MAPPING = "/delete";
    public static final String LIST_URL_MAPPING = "/list";

    public static final String ENTITY_DELETE_SUCCESS_MSG = "Deleted successfully";
    public static final String ENTITY_DELETE_ERROR_MSG = "Deletetion failed!";

    /**
     * @return The parent view name.
     */
    protected abstract String getURLMapping();

    private String getRedirectUrl(String mappingUrl) {
        return ("redirect:" + mappingUrl.substring(1));
    }
    
    /**
     * @return - A model map containing the objects to be mapped on create entity form.
     * @throws CohoException
     */
    protected abstract ModelMap getCreateFormModel() throws CohoException;
    
    @RequestMapping(value = CREATE_URL_MAPPING, method = RequestMethod.GET)
    public final ModelAndView showCreatePageInternal(ModelMap modelMap) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getURLMapping() + CREATE_URL_MAPPING);
        modelAndView.addAllObjects(getCreateFormModel());

        return modelAndView;
    }

    /**
     * Handle the service call for creating a entity.
     * @param bean - Bean which contains the data entered in form.
     * @param modelMap
     * @return - A model map containing attributes that need to be brought to view.
     */
    protected abstract ModelMap create(T bean, ModelMap modelMap) throws CohoException;
    
    @RequestMapping(value = CREATE_URL_MAPPING, method = RequestMethod.POST)
    public final ModelAndView createInternal(@Valid @ModelAttribute T bean, BindingResult bindingResult,
            ModelMap modelMap) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        try {
        	if (bindingResult.hasErrors()) {
                modelAndView.setViewName(getURLMapping() + CREATE_URL_MAPPING);
                modelAndView.addAllObjects(modelMap);
        		modelAndView.addAllObjects(getCreateFormModel());
        	} else {
        		modelAndView.setViewName(getRedirectUrl(GET_URL_MAPPING) + "/" + bean.getEntityId());
    			modelAndView.addAllObjects(create(bean, modelMap));
        	}
		} catch (CohoException e) {
            modelAndView.setViewName(getURLMapping() + CREATE_URL_MAPPING);
    		modelAndView.addAllObjects(getCreateFormModel());
			modelAndView.addObject(ATTR_ERROR_MSG, e.getMessage());
		}

        return modelAndView;
    }

    /**
     * Handle the service call for delete.
     * 
     * @param entityId - ID of the entity to be deleted.
     * @return - An instance of the bean containing the deleted data, which will be served as JSON to view.
     */
    protected abstract void delete(Serializable entityId) throws CohoException;

    @RequestMapping(value = DELETE_URL_MAPPING + "/{entityId}")
    @ResponseBody
    public final String deleteInternal(@PathVariable Serializable entityId) throws Exception {
    	try {
            delete(entityId);
            return ENTITY_DELETE_SUCCESS_MSG;
    	} catch (CohoException e) {
    		return e.getMessage();
    	}
    }

    /**
     * Get the data related to an entity.
     * 
     * @param entityId - ID of the entity to be loaded.
     * @return - A model map containing an attribute with bean data (From entity).
     */
    protected abstract ModelMap get(Serializable entityId) throws CohoException;

    @RequestMapping(value = GET_URL_MAPPING + "/{entityId}")
    public final ModelAndView getInternal(@PathVariable Serializable entityId, ModelMap modelMap) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getURLMapping() + GET_URL_MAPPING);
        modelAndView.addAllObjects(modelMap);
        modelAndView.addAllObjects(get(entityId));

        return modelAndView;
    }

    /**
     * @return - A model map containing the objects to be mapped on list form.
     * @throws CohoException
     */
    protected abstract ModelMap getListFormModel() throws CohoException;

    /**
     * @param params - Parameters for list query (null if complete list)
     * @return - A model map containing the list to be displayed on UI.
     * @throws CohoException
     */
    protected abstract ModelMap getListData(Map<String, String> params) throws CohoException;
    
    @RequestMapping(value = LIST_URL_MAPPING)
    public final ModelAndView showListPageInternal(@RequestParam (required = false) Map<String, String> params, 
    												ModelMap modelMap)
    												throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getURLMapping() + LIST_URL_MAPPING);
        
        try {
            modelAndView.addAllObjects(getListFormModel());
            modelAndView.addAllObjects(getListData(params));
        } catch (CohoException e) {
            modelAndView.addAllObjects(getListFormModel());
			modelAndView.addObject(ATTR_ERROR_MSG, e.getMessage());
        }

        return modelAndView;
    }
    
    @RequestMapping(value = UPDATE_URL_MAPPING + "/{entityId}", method = RequestMethod.GET)
    public final ModelAndView showUpdatePageInternal(@PathVariable Serializable entityId, 
    													ModelMap modelMap) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getURLMapping() + UPDATE_URL_MAPPING);
        modelAndView.addAllObjects(getCreateFormModel()); // We use the same model as was used for creating this bean.

        return modelAndView;
    }

    /**
     * Handle the service call for creating a entity.
     * @param bean - Bean which contains the data entered in form.
     * @param modelMap
     * @return - A model map containing attributes that need to be brought to view.
     */
    protected abstract ModelMap update(Serializable entityId, T bean, ModelMap modelMap) throws CohoException;
    
    @RequestMapping(value = UPDATE_URL_MAPPING, method = RequestMethod.POST)
    public final ModelAndView updateInternal(@PathVariable Serializable entityId, 
    											@Valid @ModelAttribute T bean,
    											BindingResult bindingResult, 
    											ModelMap modelMap) throws Exception {
        ModelAndView modelAndView = new ModelAndView();

        try {
        	if (bindingResult.hasErrors()) {
                modelAndView.setViewName(getURLMapping() + UPDATE_URL_MAPPING);
                modelAndView.addAllObjects(modelMap);
        		modelAndView.addAllObjects(getCreateFormModel());
        	} else {
        		modelAndView.setViewName(getRedirectUrl(GET_URL_MAPPING) + "/" + bean.getEntityId());
    			modelAndView.addAllObjects(create(bean, modelMap));
        	}
		} catch (CohoException e) {
            modelAndView.setViewName(getURLMapping() + UPDATE_URL_MAPPING);
    		modelAndView.addAllObjects(getCreateFormModel());
			modelAndView.addObject(ATTR_ERROR_MSG, e.getMessage());
		}

        return modelAndView;
    }
}
