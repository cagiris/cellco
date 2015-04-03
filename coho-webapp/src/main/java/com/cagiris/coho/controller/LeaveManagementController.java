/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cagiris.coho.model.LeaveBean;

/**
 * @author Ashish Jindal
 *
 */
@Controller
@RequestMapping(LeaveManagementController.URL_MAPPING)
public class LeaveManagementController extends AbstractCRUDController<LeaveBean> {

	public static final String URL_MAPPING = "leave";
	public static final String PENDING_APPROVALS_URL_MAPPING = "/pending";
	public static final String MY_LEAVES_URL_MAPPING = "/my-leaves-list";
	
	@Override
	public ModelMap create(LeaveBean bean, BindingResult bindingResult, ModelMap modelMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LeaveBean delete(Long entityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelMap get(Long entityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getURLMapping() {
		return URL_MAPPING;
	}

	@Override
	public ModelMap showCreatePage(ModelMap modelMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelMap showFilteredListPage(LeaveBean bean, BindingResult bindingResult, ModelMap modelMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelMap showListPage(ModelMap modelMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelMap showUpdatePage(Long entityId, ModelMap modelMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelMap update(Long entityId, LeaveBean bean, BindingResult bindingResult, ModelMap modelMap) {
		// TODO Auto-generated method stub
		return null;
	}
	
	/**
	 * Show the pending approvals page.
	 * 
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = PENDING_APPROVALS_URL_MAPPING, method = RequestMethod.GET)
	public final ModelAndView showPendingApprovalsPage(ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(getURLMapping() + PENDING_APPROVALS_URL_MAPPING);
		
		return modelAndView;
	}
	
	@RequestMapping(value = MY_LEAVES_URL_MAPPING, method = RequestMethod.POST)
	public final  ModelAndView showFilteredMyLeavesList(@Valid @ModelAttribute LeaveBean bean, BindingResult bindingResult, ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(getURLMapping() + MY_LEAVES_URL_MAPPING);
		
		return modelAndView;
	}

	@RequestMapping(value = MY_LEAVES_URL_MAPPING, method = RequestMethod.GET)
	public final  ModelAndView showMyLeavesList(ModelMap modelMap) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName(getURLMapping() + MY_LEAVES_URL_MAPPING);
		
		return modelAndView;
	}
}
