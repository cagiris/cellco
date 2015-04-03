/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cagiris.coho.model.UserBean;
import com.cagiris.coho.service.api.UserRole;

/**
 * @author Ashish Jindal
 *
 */
@Controller
@RequestMapping(UserManagementController.URL_MAPPING)
public class UserManagementController extends AbstractCRUDController<UserBean> {

	public static final String URL_MAPPING = "user";

	@Override
	public ModelMap create(UserBean bean, BindingResult bindingResult,
			ModelMap modelMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public UserBean delete(Long entityId) {
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
		ModelMap responseModelMap = new ModelMap();
		responseModelMap.addAttribute("user", new UserBean());
		responseModelMap.addAttribute("userRoles", UserRole.values());
		return responseModelMap;
	}

	@Override
	public ModelMap showFilteredListPage(UserBean bean,
			BindingResult bindingResult, ModelMap modelMap) {
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
	public ModelMap update(Long entityId, UserBean bean,
			BindingResult bindingResult, ModelMap modelMap) {
		// TODO Auto-generated method stub
		return null;
	}
}
