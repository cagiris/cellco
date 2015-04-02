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
import org.springframework.web.servlet.ModelAndView;

import com.cagiris.coho.controller.ICRUDController;
import com.cagiris.coho.datamodel.UserBean;
import com.cagiris.coho.service.api.AuthenicationPolicy;
import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.UserRole;
import com.cagiris.coho.service.exception.HierarchyServiceException;

/**
 * @author Ashish Jindal
 *
 */
@Controller
public class AddUser implements ICRUDController<UserBean> {

    @Autowired
    private IHierarchyService hierarchyService;

    @Override
    public ModelAndView create(UserBean bean, BindingResult bindingResult, ModelMap modelMap) {

        try {
            hierarchyService.addUserToTeam(2l, bean.getUserId(), bean.getUserName(), bean.getPassword(),
                    UserRole.valueOf(bean.getUserType()), AuthenicationPolicy.PASSWORD_BASED);
        } catch (HierarchyServiceException e) {

        }
        return null;
    }

    @Override
    public ModelAndView update(UserBean bean, BindingResult bindingResult, ModelMap modelMap) {
        return null;
    }

    @Override
    public ModelAndView delete(String id) {
        return null;
    }

    @Override
    public ModelAndView get(String id) {
        return null;
    }

    @Override
    public ModelAndView getAll() {
        return null;
    }

    @Override
    public ModelAndView query(Map<String, String> queryParameters, ModelMap modelMap) {
        return null;
    }

    @Override
    public ModelAndView showDetailPage(Model model) {
        model.addAttribute("userBean", new UserBean());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("usermanager/add-user");
        modelAndView.addObject("userTypes", UserRole.values());
        return modelAndView;
    }
}
