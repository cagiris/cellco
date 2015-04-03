/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller.usermanager;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cagiris.coho.controller.AbstractCRUDController;
import com.cagiris.coho.datamodel.UserBean;
import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.UserRole;

/**
 * @author Ashish Jindal
 *
 */
@Controller
@RequestMapping("/leave")
public class AddUser extends AbstractCRUDController<UserBean> {

    @Autowired
    private IHierarchyService hierarchyService;

	@Override
	public ModelAndView create(UserBean bean, BindingResult bindingResult,
			ModelMap modelMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView delete(Long entityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView get(Long entityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView showCreatePage(ModelMap modelMap) {
		modelMap.addAttribute("userBean", new UserBean());
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("usermanager/add-user");
		modelAndView.addObject("userTypes", UserRole.values());

		return modelAndView;
	}

	@Override
	public ModelAndView showFilteredListPage(
			Map<String, String> filterParameters, ModelMap modelMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView showListPage(ModelMap modelMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView showUpdatePage(Long entityId, ModelMap modelMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelAndView update(Long entityId, UserBean bean,
			BindingResult bindingResult, ModelMap modelMap) {
		// TODO Auto-generated method stub
		return null;
	}

}
