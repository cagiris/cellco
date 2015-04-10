/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import java.io.Serializable;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cagiris.coho.model.UserBean;
import com.cagiris.coho.service.api.AuthenicationPolicy;
import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.IOrganization;
import com.cagiris.coho.service.api.ITeamUser;
import com.cagiris.coho.service.api.UserRole;
import com.cagiris.coho.service.exception.CohoException;

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
    protected String getURLMapping() {
        return URL_MAPPING;
    }

    @Override
    protected ModelMap getCreateFormModel() throws CohoException {
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute(new UserBean());

        IOrganization defaultOrganization = hierarchyService.getDefaultOrganization();
        modelMap.addAttribute(hierarchyService.getAvailableUserRoles(defaultOrganization.getOrganizationId()));

        return modelMap;
    }

    @Override
    protected String getCreateSuccessRedirectView(Serializable entityId) {
        return ("redirect:/" + UserProfileController.URL_MAPPING + UPDATE_URL_MAPPING + "/" + entityId);
    }

    @Override
    protected ModelMap create(UserBean bean, ModelMap modelMap) throws CohoException {

        ModelMap responseModelMap = new ModelMap();

        hierarchyService.addUserToTeam(ControllerUtils.getDefaultTeam(hierarchyService).getTeamId(), bean.getUserId(),
                bean.getPassword(), UserRole.valueOf(bean.getUserRole()), AuthenicationPolicy.PASSWORD_BASED);

        responseModelMap.addAttribute(ATTR_SUCCESS_MSG, "Success");

        return responseModelMap;
    }

    @Override
    protected void delete(Serializable entityId) throws CohoException {
        User loggedInUser = ControllerUtils.getLoggedInUser();
        if (StringUtils.equals((String)entityId, loggedInUser.getUsername())) {
            throw new CohoException("Can not delete your own account");
        }

        hierarchyService.removeUserFromTeam(ControllerUtils.getDefaultTeam(hierarchyService).getTeamId(),
                (String)entityId);
    }

    @Override
    protected ModelMap get(Serializable entityId) throws CohoException {
        ModelMap modelMap = new ModelMap();

        ITeamUser user = hierarchyService.getTeamUserByUserId(ControllerUtils.getDefaultTeam(hierarchyService)
                .getTeamId(), (String)entityId);

        UserBean userBean = new UserBean(user);

        modelMap.addAttribute(userBean);

        return modelMap;
    }

    @Override
    protected ModelMap getListFormModel() throws CohoException {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    protected ModelMap getListData(Map<String, String> params) throws CohoException {
        throw new CohoException(Constants.ERROR_FORBIDDEN);
    }

    @Override
    protected ModelMap getUpdateFormModel(Serializable entityId) throws CohoException {

        ModelMap modelMap = new ModelMap();
        modelMap.addAllAttributes(super.getUpdateFormModel(entityId));

        // Add the additional data for list box.
        IOrganization defaultOrganization = hierarchyService.getDefaultOrganization();
        modelMap.addAttribute(hierarchyService.getAvailableUserRoles(defaultOrganization.getOrganizationId()));

        return modelMap;
    }

    @Override
    protected ModelMap update(Serializable entityId, UserBean bean, ModelMap modelMap) throws CohoException {
        ModelMap responseModelMap = new ModelMap();

        hierarchyService.addUserToTeam(ControllerUtils.getDefaultTeam(hierarchyService).getTeamId(), bean.getUserId(),
                bean.getPassword(), UserRole.valueOf(bean.getUserRole()), AuthenicationPolicy.PASSWORD_BASED);

        responseModelMap.addAttribute(ATTR_SUCCESS_MSG, "User details successfuly updated.");

        return responseModelMap;
    }
}
