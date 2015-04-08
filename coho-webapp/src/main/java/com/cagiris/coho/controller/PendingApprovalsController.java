/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author Ashish Jindal
 *
 */
@Controller
@RequestMapping(LeaveManagementController.URL_MAPPING)
public class PendingApprovalsController extends AbstractController {

    public static final String PENDING_APPROVALS_URL_MAPPING = "/pending";
    
    /**
     * Show the pending approvals page.
     * 
     * @param modelMap
     * @return
     */
    @RequestMapping(value = PENDING_APPROVALS_URL_MAPPING, method = RequestMethod.GET)
    public final ModelAndView showPendingApprovalsPage(ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(LeaveManagementController.URL_MAPPING + PENDING_APPROVALS_URL_MAPPING);

        return modelAndView;
    }
}
