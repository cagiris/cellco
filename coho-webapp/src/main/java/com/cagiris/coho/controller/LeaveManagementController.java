/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import java.io.Serializable;
import java.text.ParseException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
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
public class LeaveManagementController extends AbstractCRUDController<LeaveRequestBean> {

    private static final Logger logger = LoggerFactory.getLogger(LeaveManagementController.class);

    public static final String URL_MAPPING = "leave";
    public static final String PENDING_APPROVALS_URL_MAPPING = "/pending";
    public static final String MY_LEAVES_URL_MAPPING = "/my-leaves-list";

    @Autowired
    private ILeaveManagementService leaveManagementService;

    @Override
    public ModelMap create(LeaveRequestBean bean, BindingResult bindingResult, ModelMap modelMap)
            throws LeaveManagementServiceException, ParseException {
    	 ModelMap responseModelMap = new ModelMap(); 
    	if (bindingResult.hasErrors()) {
             responseModelMap.addAllAttributes(modelMap);
         } else {
        	 User user = getLoggedInUser();
        	 IUserLeaveRequest leaveRequest = leaveManagementService.applyForLeave(user.getUsername(), null,
                     bean.getLeaveStartDateFormatted(), bean.getLeaveEndDateFormatted(), bean.getRequestSubject(),
                     bean.getRequestDescription());
             bean.setLeaveApplicationId(leaveRequest.getLeaveApplicationId());
         }
        
        return responseModelMap;
    }

    @Override
    public void delete(Serializable entityId) {
        throw new AccessDeniedException(Constants.ERROR_FORBIDDEN);
    }

    @Override
    public ModelMap get(Serializable entityId) throws LeaveManagementServiceException {
        ModelMap modelMap = new ModelMap();
        IUserLeaveRequest leaveRequest = leaveManagementService.getLeaveRequestById((String)entityId);
        LeaveRequestBean leaveRequestBean = new LeaveRequestBean(leaveRequest);
        modelMap.addAttribute(leaveRequestBean);

        return modelMap;
    }

    @Override
    public String getURLMapping() {
        return URL_MAPPING;
    }

    @Override
    public ModelMap showCreatePage(ModelMap modelMap) {
        ModelMap responseModelMap = new ModelMap();
        responseModelMap.addAttribute(new LeaveRequestBean());
        return responseModelMap;
    }

    @Override
    public ModelMap showFilteredListPage(Map<String, String> filterParams, ModelMap modelMap) {
        throw new AccessDeniedException(Constants.ERROR_FORBIDDEN);
    }

    @Override
    public ModelMap showListPage(ModelMap modelMap) throws LeaveManagementServiceException {
        Set<UserRole> authorities = getUserRolesForLoggedInUser();
        User loggedInUser = getLoggedInUser();
        if (authorities.contains(UserRole.AGENT)) {
            List<? extends IUserLeaveRequest> leaveRequestsByUserId = leaveManagementService
                    .getLeaveRequestsByUserId(loggedInUser.getUsername());
            List<LeaveRequestBean> userLeaveRequestBeans = leaveRequestsByUserId.stream()
                    .map(LeaveRequestBean::mapToBean).collect(Collectors.toList());
            modelMap.addAttribute(userLeaveRequestBeans);
        } else if (authorities.contains(UserRole.ADMIN)) {
            List<? extends IUserLeaveRequest> newLeaveRequests = leaveManagementService
                    .getAllPendingLeaveRequestsByLeaveStatus(loggedInUser.getUsername(), LeaveRequestStatus.NEW);
            List<LeaveRequestBean> userLeaveRequestBeans = newLeaveRequests.stream().map(LeaveRequestBean::mapToBean)
                    .collect(Collectors.toList());
            modelMap.addAttribute(userLeaveRequestBeans);
        }

        return modelMap;
    }

    @Override
    public ModelMap showUpdatePage(Serializable entityId, ModelMap modelMap) {
        throw new AccessDeniedException(Constants.ERROR_FORBIDDEN);
    }

    @Override
    public ModelMap update(Serializable entityId, LeaveRequestBean bean, BindingResult bindingResult, ModelMap modelMap) {
        throw new AccessDeniedException(Constants.ERROR_FORBIDDEN);
    }

    /**
     * Show the pending approvals page.
     * 
     * @param modelMap
     * @return
     */
    @RequestMapping(value = PENDING_APPROVALS_URL_MAPPING, method = RequestMethod.GET)
    public final ModelAndView showPendingApprovalsPage(ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getURLMapping() + PENDING_APPROVALS_URL_MAPPING);

        return modelAndView;
    }

    @RequestMapping(value = MY_LEAVES_URL_MAPPING, method = RequestMethod.POST)
    public final ModelAndView showFilteredMyLeavesList(@Valid @ModelAttribute LeaveRequestBean bean,
            BindingResult bindingResult, ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getURLMapping() + MY_LEAVES_URL_MAPPING);

        return modelAndView;
    }

    @RequestMapping(value = MY_LEAVES_URL_MAPPING, method = RequestMethod.GET)
    public final ModelAndView showMyLeavesList(ModelMap modelMap) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getURLMapping() + MY_LEAVES_URL_MAPPING);

        return modelAndView;
    }
}
