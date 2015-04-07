/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.ILeaveManagementService;
import com.cagiris.coho.service.api.IUserLeaveQuota;
import com.cagiris.coho.service.api.LeaveType;
import com.cagiris.coho.service.exception.HierarchyServiceException;
import com.cagiris.coho.service.exception.LeaveManagementServiceException;

/**
 * @author Ashish Jindal
 *
 */
@Controller
public class QuickStatisticsController extends AbstractController implements IController {

	public static final String COUNT_PAID_LEAVES_URL_MAPPING = "/get-leave-count";
	public static final String COUNT_LEAVES_APPROVALS_URL_MAPPING = "/get-leave-approvals-count";
	public static final String COUNT_USERS_ACTIVE_IN_SHIFT = "/get-users-active-in-shift-count";
	public static final String COUNT_USERS = "/get-users-count";
	
    @Autowired
    private ILeaveManagementService leaveManagementService;
    
    @Autowired
    private IHierarchyService hierarchyService;
    
	@RequestMapping(COUNT_PAID_LEAVES_URL_MAPPING + "/{leaveType}")
	@ResponseBody
	public String getLeaveCount(@PathVariable String leaveType) {
		
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
	        User user = (User)authentication.getPrincipal();
			IUserLeaveQuota userLeaveQuota;
			
			userLeaveQuota = leaveManagementService.getUserLeaveQuota(user.getUsername());
			Map<LeaveType, Integer> userLeaveQuotaMap = userLeaveQuota.getLeaveTypeVsLeaveQuota();
			
			Integer leaveCount = userLeaveQuotaMap.get(LeaveType.valueOf(leaveType.toUpperCase()));
			if (leaveCount == null) {
				leaveCount = 0;
			}
			
			return leaveCount.toString();
		} catch (LeaveManagementServiceException e) {
			return "error";
		}
	}

	@RequestMapping(COUNT_LEAVES_APPROVALS_URL_MAPPING + "/{approvalStatus}")
	@ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
	public String getLeaveApprovalsCount() {
		return "0";
	}

	@RequestMapping(COUNT_USERS_ACTIVE_IN_SHIFT)
	@ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
	public String getActiveShiftUsersCount() {
		return "0";
	}

	@RequestMapping(COUNT_USERS)
	@ResponseBody
    @PreAuthorize("hasRole('ADMIN')")
	public String getUsersCount() {
		try {
			Integer countUsers = hierarchyService.getAllUsersForTeam(getDefaultTeam().getTeamId()).size();
			return countUsers.toString();
		} catch (HierarchyServiceException e) {
			return "error";
		}
	}
}
