/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.cagiris.coho.model.UserBean;
import com.cagiris.coho.service.api.AuthenicationPolicy;
import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.IOrganization;
import com.cagiris.coho.service.api.ITeam;
import com.cagiris.coho.service.api.ITeamUser;
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
    public static final String PROFILE_URL_MAPPING = "/profile";
    @Autowired
    private IHierarchyService hierarchyService;

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ModelMap create(UserBean bean, BindingResult bindingResult, ModelMap modelMap)
            throws HierarchyServiceException {
        ModelMap responseModelMap = new ModelMap();
        if (bindingResult.hasErrors()) {
            responseModelMap.addAllAttributes(modelMap);
            responseModelMap.addAttribute(getAvailableUserRoles());
        } else {
            hierarchyService.addUserToTeam(getDefaultTeam().getTeamId(), bean.getUserId(), bean.getUserName(),
                    bean.getPassword(), UserRole.valueOf(bean.getUserRole()), AuthenicationPolicy.PASSWORD_BASED);

            responseModelMap.addAttribute(ATTR_SUCCESS_MSG, "Success");
        }

        return responseModelMap;
    }

    private Set<UserRole> getAvailableUserRoles() throws HierarchyServiceException {
        return hierarchyService.getAvailableUserRoles(getDefaultTeam().getTeamId());
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public void delete(Serializable entityId) throws Exception {
        User loggedInUser = getLoggedInUser();
        if (StringUtils.equals((String)entityId, loggedInUser.getUsername())) {
            throw new Exception("Can not delete your own account");
        }
        hierarchyService.removeUserFromTeam(getDefaultTeam().getTeamId(), (String)entityId);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ModelMap get(Serializable entityId) throws HierarchyServiceException, ResourceNotFoundException {
        ModelMap modelMap = new ModelMap();

        ITeamUser user = hierarchyService.getTeamUserByUserId(getDefaultTeam().getTeamId(), (String)entityId);

        UserBean userBean = new UserBean();
        prepareUserBean(userBean, user);

        modelMap.addAttribute(userBean);

        return modelMap;
    }

    @RequestMapping(value = PROFILE_URL_MAPPING)
    public ModelAndView getUserProfile() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        
        modelAndView.setViewName(getURLMapping() + GET_URL_MAPPING);
        
        User loggedInUser = getLoggedInUser();
        ITeamUser user = hierarchyService.getTeamUserByUserId(getDefaultTeam().getTeamId(), loggedInUser.getUsername());

        UserBean userBean = new UserBean();
        prepareUserBean(userBean, user);

        modelAndView.addObject(userBean);

        return modelAndView;
    }
    
    private ITeam getDefaultTeam() throws HierarchyServiceException {
        IOrganization defaultOrganization = hierarchyService.getDefaultOrganization();
        return hierarchyService.getDefaultTeam(defaultOrganization.getOrganizationId());
    }

    @Override
    public String getURLMapping() {
        return URL_MAPPING;
    }

    /**
     * Use the entity data to prepare a bean.
     * 
     * @param userBean
     * @param userEntity
     */
    private void prepareUserBean(UserBean userBean, ITeamUser user) {
        userBean.setUserId(user.getUserId());
        userBean.setUserName(user.getUserName());
        userBean.setUserRole(user.getUserRole());
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ModelMap showCreatePage(ModelMap modelMap) throws HierarchyServiceException {
        ModelMap responseModelMap = new ModelMap();
        responseModelMap.addAttribute(new UserBean());

        IOrganization defaultOrganization = hierarchyService.getDefaultOrganization();
        responseModelMap.addAttribute(hierarchyService.getAvailableUserRoles(defaultOrganization.getOrganizationId()));

        return responseModelMap;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ModelMap showFilteredListPage(Map<String, String> filterParams, ModelMap modelMap) {
    	throw new AccessDeniedException(Constants.ERROR_FORBIDDEN);
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ModelMap showListPage(ModelMap modelMap) throws HierarchyServiceException {
        List<? extends ITeamUser> allUsersForTeam = hierarchyService.getAllUsersForTeam(getDefaultTeam().getTeamId());
        List<UserBean> userBeans = new ArrayList<UserBean>();
        for (ITeamUser teamUser : allUsersForTeam) {
            UserBean userBean = new UserBean();
            prepareUserBean(userBean, teamUser);
            userBeans.add(userBean);
        }

        modelMap.addAttribute(userBeans);
        return modelMap;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ModelMap showUpdatePage(Serializable entityId, ModelMap modelMap) throws HierarchyServiceException,
            ResourceNotFoundException {
        ModelMap responseModelMap = new ModelMap();

        ITeamUser user = hierarchyService.getTeamUserByUserId(getDefaultTeam().getTeamId(), (String)entityId);

        UserBean userBean = new UserBean();
        prepareUserBean(userBean, user);
        responseModelMap.addAttribute(userBean);
        responseModelMap.addAttribute(getAvailableUserRoles());

        return responseModelMap;
    }

    @Override
    @PreAuthorize("hasRole('ADMIN')")
    public ModelMap update(Serializable entityId, UserBean bean, BindingResult bindingResult, ModelMap modelMap)
            throws HierarchyServiceException {
        ModelMap responseModelMap = new ModelMap();
        if (bindingResult.hasErrors()) {
            responseModelMap.addAllAttributes(modelMap);
            responseModelMap.addAttribute(getAvailableUserRoles());
        } else {
            hierarchyService.addUserToTeam(getDefaultTeam().getTeamId(), bean.getUserId(), bean.getUserName(),
                    bean.getPassword(), UserRole.valueOf(bean.getUserRole()), AuthenicationPolicy.PASSWORD_BASED);

            responseModelMap.addAttribute(ATTR_SUCCESS_MSG, "User details successfuly updated.");
        }
        return responseModelMap;
    }
}
