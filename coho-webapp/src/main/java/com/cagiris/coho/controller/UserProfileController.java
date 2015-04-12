/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cagiris.coho.model.UpdatePasswordBean;
import com.cagiris.coho.model.UserBean;
import com.cagiris.coho.model.UserProfileBean;
import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.ITeamUser;
import com.cagiris.coho.service.api.IUserProfile;
import com.cagiris.coho.service.api.UserRole;
import com.cagiris.coho.service.exception.CohoException;

/**
 * @author Ashish Jindal
 *
 */
@Controller
@RequestMapping(UserProfileController.URL_MAPPING)
public class UserProfileController extends AbstractCRUDController<UserProfileBean> {

    public static final String URL_MAPPING = "user-profile";
    public static final String UPDATE_PASSWORD_MAPPING = "update-user-password";

    @Autowired
    private IHierarchyService hierarchyService;

    @Override
    protected String getURLMapping() {
        return URL_MAPPING;
    }

    @Override
    protected ModelMap getCreateFormModel() throws CohoException {
        throw new CohoException(Constants.ERROR_FORBIDDEN);
    }

    @Override
    protected ModelMap create(UserProfileBean bean, ModelMap modelMap) throws CohoException {
        throw new CohoException(Constants.ERROR_FORBIDDEN);
    }

    @Override
    protected void delete(Serializable entityId) throws CohoException {
        throw new CohoException(Constants.ERROR_FORBIDDEN);
    }

    @Override
    protected ModelMap get(Serializable entityId) throws CohoException {
        ModelMap modelMap = new ModelMap();

        UserProfileBean userProfile = new UserProfileBean(hierarchyService.getUserProfile((String)entityId));
        modelMap.addAttribute(userProfile);

        ITeamUser user = hierarchyService.getTeamUserByUserId(ControllerUtils.getDefaultTeam(hierarchyService)
                .getTeamId(), (String)entityId);

        UserBean userBean = new UserBean(user);

        modelMap.addAttribute(userBean);

        return modelMap;
    }

    @Override
    protected ModelMap getListFormModel() throws CohoException {
        return null;
    }

    @Override
    protected ModelMap getListData(Map<String, String> params) throws CohoException {
        ModelMap modelListData = new ModelMap();

        List<? extends IUserProfile> allUsers = hierarchyService.getAllUserProfiles(hierarchyService
                .getDefaultOrganization().getOrganizationId());

        List<UserProfileBean> userProfiles = new ArrayList<>();
        for (IUserProfile userProfile : allUsers) {
            userProfiles.add(new UserProfileBean(userProfile));
        }

        modelListData.addAttribute(userProfiles);

        return modelListData;
    }

    @Override
    protected ModelMap update(Serializable entityId, UserProfileBean bean, ModelMap modelMap) throws CohoException {
        ModelMap responseModelMap = new ModelMap();

        hierarchyService.updateUserProfile(bean.getUserId(), bean.getFirstName(), bean.getLastName(),
                bean.getDateOfBirth(), bean.getGender(), bean.getMobileNumber(), bean.getEmailId(),
                bean.getAddressLine1(), bean.getAddressLine2(), bean.getCity(), bean.getPincode(), bean.getState(),
                bean.getCountry(), bean.getJoinedOn(), bean.getLeftOn(), bean.getDesignation());

        responseModelMap.addAttribute(ATTR_SUCCESS_MSG, "User profile successfuly updated.");

        return responseModelMap;
    }

    @RequestMapping(value = {"", "/"})
    public final ModelAndView showProfile() throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getURLMapping() + GET_URL_MAPPING);

        String entityId = ControllerUtils.getLoggedInUser().getUsername();
        modelAndView.addAllObjects(get(entityId));

        return modelAndView;
    }

    @RequestMapping(value = {UPDATE_PASSWORD_MAPPING}, method = RequestMethod.GET)
    public ModelAndView showUpdatePasswordPage() {
        ModelAndView model = new ModelAndView();
        model.addObject(new UpdatePasswordBean());
        model.setViewName(ControllerUtils.AJAX_CONTENT_MAPPING_PREFIX + getURLMapping() + "/" + UPDATE_PASSWORD_MAPPING);
        return model;
    }

    @RequestMapping(value = {UPDATE_PASSWORD_MAPPING}, method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView updatePassword(@RequestBody @Valid UpdatePasswordBean bean, BindingResult bindingResult,
            ModelMap modelMap) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(ControllerUtils.AJAX_CONTENT_MAPPING_PREFIX + getURLMapping() + "/"
                + UPDATE_PASSWORD_MAPPING);
        try {
            if (bindingResult.hasErrors()) {
                modelAndView.addAllObjects(modelMap);
            } else {
                ITeamUser user = hierarchyService.getTeamUserByUserId(ControllerUtils.getDefaultTeam(hierarchyService)
                        .getTeamId(), bean.getUserId());

                hierarchyService.updateUser(bean.getUserId(), bean.getNewPassword(),
                        UserRole.valueOf(user.getUserRole()));

                modelAndView.setViewName(ControllerUtils.AJAX_CONTENT_MAPPING_PREFIX + "common/ajax-feedback-msg");
                modelAndView.addObject(ATTR_SUCCESS_MSG, "Password updated successfuly");
            }
        } catch (CohoException e) {
            modelAndView.addAllObjects(modelMap);
            modelAndView.addObject(ATTR_ERROR_MSG, e.getMessage());
        }

        return modelAndView;
    }
}
