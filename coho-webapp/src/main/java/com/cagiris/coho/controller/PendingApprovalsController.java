/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.cagiris.coho.model.LeaveRequestBean;
import com.cagiris.coho.service.api.ILeaveManagementService;
import com.cagiris.coho.service.api.IUserLeaveRequest;
import com.cagiris.coho.service.api.LeaveRequestStatus;
import com.cagiris.coho.service.api.UserRole;
import com.cagiris.coho.service.exception.LeaveManagementServiceException;

/**
 * @author Ashish Jindal
 *
 */
@Controller
@RequestMapping(LeaveManagementController.URL_MAPPING)
public class PendingApprovalsController extends AbstractController {

    public static final String PENDING_APPROVALS_URL_MAPPING = "/pending";
    public static final String PENDING_APPROVALS_APPROVE_URL_MAPPING = "/pending/approve";
    public static final String PENDING_APPROVALS_HOLD_URL_MAPPING = "/pending/hold";
    public static final String PENDING_APPROVALS_CANCEL_URL_MAPPING = "/pending/cancel";

    @Autowired
    private ILeaveManagementService leaveManagementService;

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

        Set<UserRole> authorities = ControllerUtils.getUserRolesForLoggedInUser();
        User loggedInUser = ControllerUtils.getLoggedInUser();
        if (authorities.contains(UserRole.ADMIN)) {
            List<? extends IUserLeaveRequest> allPendingLeaveRequestsByLeaveStatus;
            try {
                // NEW , TODO PENDING
                allPendingLeaveRequestsByLeaveStatus = leaveManagementService.getAllPendingLeaveRequestsByLeaveStatus(
                        loggedInUser.getUsername(), LeaveRequestStatus.NEW);
                List<LeaveRequestBean> userLeaveRequestBeans = allPendingLeaveRequestsByLeaveStatus.stream()
                        .map(LeaveRequestBean::mapToBean).collect(Collectors.toList());
                modelMap.addAttribute(userLeaveRequestBeans);
            } catch (LeaveManagementServiceException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

        modelAndView.addObject(modelMap);

        return modelAndView;
    }

    /*
     * @RequestMapping(value = PENDING_APPROVALS_APPROVE_URL_MAPPING, method =
     * RequestMethod.GET)
     * 
     * @PreAuthorize("hasRole('ADMIN')") public final String
     * pendingApprovalsApproveRequest(ModelMap modelMap) { //ModelAndView
     * modelAndView = new ModelAndView();
     * //modelAndView.setViewName(LeaveManagementController.URL_MAPPING +
     * PENDING_APPROVALS_URL_MAPPING); //return modelAndView;
     * 
     * return "SUCCESS retured :)"; }
     */

    @RequestMapping(value = "pending/approve")
    // @PreAuthorize("hasRole('ADMIN')")
    public final String pendingApprovalsApproveRequest(ModelMap modelMap) {
        // ModelAndView modelAndView = new ModelAndView();
        // modelAndView.setViewName(LeaveManagementController.URL_MAPPING +
        // PENDING_APPROVALS_URL_MAPPING);
        // return modelAndView;

        return "SUCCESS retured :)";
    }

}
