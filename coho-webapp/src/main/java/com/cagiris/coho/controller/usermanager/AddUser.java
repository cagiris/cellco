/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller.usermanager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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
public class AddUser {

    @Autowired
    private IHierarchyService hierarchyService;

    @RequestMapping(value = {"/add-user"}, method = RequestMethod.GET)
    public ModelAndView showAddUserPage(Model model) {
        model.addAttribute("userBean", new UserBean());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("usermanager/add-user");
        modelAndView.addObject("userTypes", UserRole.values());

        return modelAndView;
    }

    @RequestMapping(value = {"/add-user"}, method = RequestMethod.POST)
    public String addUserAction(@ModelAttribute("userBean") UserBean userBean, BindingResult bindingResult,
            ModelMap modelMap) {
        try {
            hierarchyService.addUserToTeam(2l, userBean.getUserId(), userBean.getUserName(), userBean.getPassword(),
                    UserRole.valueOf(userBean.getUserType()), AuthenicationPolicy.PASSWORD_BASED);
        } catch (HierarchyServiceException e) {

        }
        return "usermanager/add-user";
    }
}
