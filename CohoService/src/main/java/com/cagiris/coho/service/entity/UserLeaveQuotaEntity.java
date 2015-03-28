/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */

package com.cagiris.coho.service.entity;

import java.util.Map;

import javax.persistence.Entity;

import com.cagiris.coho.service.api.IUserLeaveQuota;
import com.cagiris.coho.service.api.LeaveType;

/**
 *
 * @author: ssnk
 */
@Entity(name = "userLeaveQuota")
public class UserLeaveQuota extends BaseEntity implements IUserLeaveQuota {

	private String userId;
	private Map<LeaveType, Integer> leaveTypeVsLeaveQuota;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Map<LeaveType, Integer> getLeaveTypeVsLeaveQuota() {
		return leaveTypeVsLeaveQuota;
	}

	public void setLeaveTypeVsLeaveQuota(
			Map<LeaveType, Integer> leaveTypeVsLeaveQuota) {
		this.leaveTypeVsLeaveQuota = leaveTypeVsLeaveQuota;
	}

}
