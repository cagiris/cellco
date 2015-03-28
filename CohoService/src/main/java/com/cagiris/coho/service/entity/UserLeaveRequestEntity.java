/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */

package com.cagiris.coho.service.entity;

import java.util.Map;

import javax.persistence.Entity;

import com.cagiris.coho.service.api.ITeam;
import com.cagiris.coho.service.api.IUserLeaveRequest;
import com.cagiris.coho.service.api.LeaveRequestStatus;
import com.cagiris.coho.service.api.LeaveType;

/**
 *
 * @author: ssnk
 */
@Entity(name = "userLeaveRequest")
public class UserLeaveRequest extends BaseEntity implements IUserLeaveRequest {

	private String leaveApplicationId;
	private String userId;
	private Map<LeaveType, Integer> leaveTypeVsLeaveCount;
	private LeaveRequestStatus leaveApplicationStatus;
	private String approvingUserId;


	public String getLeaveApplicationId() {
		return leaveApplicationId;
	}

	public void setLeaveApplicationId(String leaveApplicationId) {
		this.leaveApplicationId = leaveApplicationId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Map<LeaveType, Integer> getLeaveTypeVsLeaveCount() {
		return leaveTypeVsLeaveCount;
	}

	public void setLeaveTypeVsLeaveCount(
			Map<LeaveType, Integer> leaveTypeVsLeaveCount) {
		this.leaveTypeVsLeaveCount = leaveTypeVsLeaveCount;
	}

	public LeaveRequestStatus getLeaveApplicationStatus() {
		return leaveApplicationStatus;
	}

	public void setLeaveApplicationStatus(LeaveRequestStatus leaveApplicationStatus) {
		this.leaveApplicationStatus = leaveApplicationStatus;
	}

	public String getApprovingUserId() {
		return approvingUserId;
	}

	public void setApprovingUserId(String approvingUserId) {
		this.approvingUserId = approvingUserId;
	}

}


