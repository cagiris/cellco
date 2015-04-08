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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cagiris.coho.model.LeaveRequestBean;
import com.cagiris.coho.service.api.ILeaveManagementService;
import com.cagiris.coho.service.api.IUserLeaveRequest;
import com.cagiris.coho.service.api.LeaveRequestStatus;
import com.cagiris.coho.service.api.UserRole;
import com.cagiris.coho.service.exception.CohoException;

/**
 * @author Ashish Jindal
 *
 */
@Controller
@RequestMapping(LeaveManagementController.URL_MAPPING)
public class LeaveManagementController extends AbstractCRUDController<LeaveRequestBean> {

    public static final String URL_MAPPING = "leave";

    @Autowired
    private ILeaveManagementService leaveManagementService;

	@Override
	protected String getURLMapping() {
		return URL_MAPPING;
	}

	@Override
	protected ModelMap getCreateFormModel() throws CohoException {
		ModelMap modelMap = new ModelMap();
		modelMap.addAttribute(new LeaveRequestBean());
		return modelMap;
	}

	@Override
	protected ModelMap create(LeaveRequestBean bean, ModelMap modelMap) throws CohoException {
		ModelMap responseModelMap = new ModelMap(); 
		
		try {
			User user = ControllerUtils.getLoggedInUser();
			IUserLeaveRequest leaveRequest = leaveManagementService.applyForLeave(user.getUsername(), 
																					null,
																					bean.getLeaveStartDateFormatted(), 
																					bean.getLeaveEndDateFormatted(), 
																					bean.getRequestSubject(),
																					bean.getRequestDescription());

			bean.setLeaveApplicationId(leaveRequest.getLeaveApplicationId());
		} catch (ParseException e) {
			throw new CohoException("Invalid dadte format!");
		}

        return responseModelMap;
	}

	@Override
	protected void delete(Serializable entityId) throws CohoException {
		throw new CohoException(Constants.ERROR_FORBIDDEN);
	}

	@Override
	protected ModelMap get(Serializable entityId) throws CohoException {
		ModelMap modelMap = new ModelMap();
        IUserLeaveRequest leaveRequest = leaveManagementService.getLeaveRequestById((String)entityId);
        LeaveRequestBean leaveRequestBean = new LeaveRequestBean(leaveRequest);
        modelMap.addAttribute(leaveRequestBean);

        return modelMap;
	}

	@Override
	protected ModelMap getListFormModel() throws CohoException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected ModelMap getListData(Map<String, String> params) throws CohoException {
		
		ModelMap modelMap = new ModelMap();
		
		Set<UserRole> authorities = ControllerUtils.getUserRolesForLoggedInUser();
        User loggedInUser = ControllerUtils.getLoggedInUser();
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
	protected ModelMap update(Serializable entityId, LeaveRequestBean bean, ModelMap modelMap) throws CohoException {
		throw new CohoException(Constants.ERROR_FORBIDDEN);
	}
}
