/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cagiris.coho.model.UserBean;
import com.cagiris.coho.service.api.AuthenicationPolicy;
import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.IOrganization;
import com.cagiris.coho.service.api.ITeam;
import com.cagiris.coho.service.api.ITeamUser;
import com.cagiris.coho.service.api.IUser;
import com.cagiris.coho.service.api.UserRole;
import com.cagiris.coho.service.exception.HierarchyServiceException;
import com.cagiris.coho.service.exception.ResourceNotFoundException;

/**
 * @author Ashish Jindal
 *
 */
@Controller
@RequestMapping(UserManagementController.URL_MAPPING)
public class UserManagementController extends AbstractCRUDController<UserBean> {

	public static final String URL_MAPPING = "user";
	
	@Autowired
	private IHierarchyService hierarchyService;

	@Override
	public ModelMap create(UserBean bean, BindingResult bindingResult, ModelMap modelMap) {
		ModelMap responseModelMap = new ModelMap();
		if  (bindingResult.hasErrors()) {
			responseModelMap.addAllAttributes(modelMap);
			responseModelMap.addAttribute(UserRole.values());
		}
		else
		{
			try {
				hierarchyService.addUserToTeam(getDefaultTeam().getTeamId(), 
						bean.getUserId(), 
						bean.getUserName(), 
						bean.getPassword(), 
						UserRole.valueOf(bean.getUserRole()), 
						AuthenicationPolicy.PASSWORD_BASED);
				
				responseModelMap.addAttribute(ATTR_SUCCESS_MSG, "Success");

			} catch (HierarchyServiceException e) {
				e.printStackTrace();
			} catch (ResourceNotFoundException e) {
				e.printStackTrace();
			}
		}
		return responseModelMap;
	}

	private ITeam getDefaultTeam() throws HierarchyServiceException,
			ResourceNotFoundException {
		List<? extends IOrganization> allOrganizationInfo = hierarchyService.getAllOrganizationInfo();
		if(allOrganizationInfo==null||allOrganizationInfo.size()==0)
		{
			//TODO throw exception
		}
		ITeam team=null;
		for (IOrganization iOrganization : allOrganizationInfo) {
			List<? extends ITeam> allTeams = hierarchyService.getAllTeams(iOrganization.getOrganizationId());
			if(allTeams!=null&&allTeams.size()>0)
			{
				team=allTeams.get(0);
				break;
			}
		}
		return team;
	}

	@Override
	public UserBean delete(Serializable entityId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelMap get(Serializable entityId) {
		ModelMap modelMap = new ModelMap();
		
		try {
			IUser user = hierarchyService.getUser((String)entityId);
			UserBean userBean = new UserBean();
			userBean.setUserId(user.getUserId());
			userBean.setUserName(user.getUserName());
			userBean.setUserRole(user.getUserRole().name());
			
			modelMap.addAttribute(userBean);
			
		} catch (HierarchyServiceException | ResourceNotFoundException e) {
			modelMap.addAttribute(ATTR_ERROR_MSG, e.getMessage());
		}
		
		return modelMap;
	}

	@Override
	public String getURLMapping() {
		return URL_MAPPING;
	}

	@Override
	public ModelMap showCreatePage(ModelMap modelMap) {
		ModelMap responseModelMap = new ModelMap();
		responseModelMap.addAttribute(new UserBean());
		responseModelMap.addAttribute(UserRole.values());
		return responseModelMap;
	}

	@Override
	public ModelMap showFilteredListPage(UserBean bean, BindingResult bindingResult, ModelMap modelMap) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ModelMap showListPage(ModelMap modelMap) {
		try {
			List<? extends ITeamUser> allUsersForTeam = hierarchyService.getAllUsersForTeam(getDefaultTeam().getTeamId());
			List<UserBean> userBeans = new ArrayList<UserBean>();
			for (ITeamUser teamUser : allUsersForTeam) {
			UserBean userBean = new UserBean();
			userBean.setUserId(teamUser.getUserId());
			userBean.setUserName(teamUser.getUserName());
			userBean.setUserRole(teamUser.getUserRole());
			userBeans.add(userBean);
			}
			modelMap.addAttribute(userBeans);
		} catch (HierarchyServiceException | ResourceNotFoundException e) {
			e.printStackTrace();
		}
		return modelMap;
	}

	@Override
	public ModelMap showUpdatePage(Serializable entityId, ModelMap modelMap) {
		ModelMap responseModelMap = new ModelMap();
		
		try {
			IUser user = hierarchyService.getUser((String)entityId);
			UserBean userBean = new UserBean();
			userBean.setUserId(user.getUserId());
			userBean.setUserName(user.getUserName());
			userBean.setPassword(user.getAuthToken());
			userBean.setUserRole(user.getUserRole().name());
			
			responseModelMap.addAttribute(userBean);
			responseModelMap.addAttribute(UserRole.values());
			
		} catch (HierarchyServiceException | ResourceNotFoundException e) {
			responseModelMap.addAttribute(ATTR_ERROR_MSG, e.getMessage());
		}
		
		return responseModelMap;
	}

	@Override
	public ModelMap update(Serializable entityId, UserBean bean, BindingResult bindingResult, ModelMap modelMap) {
		ModelMap responseModelMap = new ModelMap();
		if  (bindingResult.hasErrors()) {
			responseModelMap.addAllAttributes(modelMap);
			responseModelMap.addAttribute(UserRole.values());
		}
		else
		{
			try {
				hierarchyService.addUserToTeam(getDefaultTeam().getTeamId(), 
						bean.getUserId(), 
						bean.getUserName(), 
						bean.getPassword(), 
						UserRole.valueOf(bean.getUserRole()), 
						AuthenicationPolicy.PASSWORD_BASED);
				
				responseModelMap.addAttribute(ATTR_SUCCESS_MSG, "Success");

			} catch (HierarchyServiceException e) {
				e.printStackTrace();
			} catch (ResourceNotFoundException e) {
				e.printStackTrace();
			}
		}
		return responseModelMap;
	}
}
