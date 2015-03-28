/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

import java.util.Map;

import javax.persistence.Entity;

import com.cagiris.coho.service.api.IUserLeaveRequest;
import com.cagiris.coho.service.api.IUserRoleLeaveQuota;
import com.cagiris.coho.service.api.LeaveType;
import com.cagiris.coho.service.api.UserRole;

/**
 *
 * @author: ssnk
 */

@Entity(name = "userRoleLeaveQuota")
public class UserRoleLeaveQuota extends BaseEntity implements IUserRoleLeaveQuota {

	private UserRole userRole;
	private Map<LeaveType, Integer> leaveTypeVsLeaveCount;

	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	public Map<LeaveType, Integer> getLeaveTypeVsLeaveCount() {
		return leaveTypeVsLeaveCount;
	}

	public void setLeaveTypeVsLeaveCount(
			Map<LeaveType, Integer> leaveTypeVsLeaveCount) {
		this.leaveTypeVsLeaveCount = leaveTypeVsLeaveCount;
	}



}