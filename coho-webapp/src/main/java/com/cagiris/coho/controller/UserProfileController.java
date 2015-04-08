/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import java.io.Serializable;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cagiris.coho.model.UserProfileBean;
import com.cagiris.coho.service.exception.CohoException;

/**
 * @author Ashish Jindal
 *
 */
@Controller
@RequestMapping(UserProfileController.URL_MAPPING)
public class UserProfileController extends AbstractCRUDController<UserProfileBean> {

    public static final String URL_MAPPING = "user/profile";

	@Override
	protected String getURLMapping() {
		return URL_MAPPING;
	}

	@Override
	protected ModelMap getCreateFormModel() throws CohoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ModelMap create(UserProfileBean bean, ModelMap modelMap)
			throws CohoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void delete(Serializable entityId) throws CohoException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected ModelMap get(Serializable entityId) throws CohoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ModelMap getListFormModel() throws CohoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ModelMap getListData(Map<String, String> params)
			throws CohoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ModelMap update(Serializable entityId, UserProfileBean bean,
			ModelMap modelMap) throws CohoException {
		// TODO Auto-generated method stub
		return null;
	}
}
