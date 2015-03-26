/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

import java.util.Map;

/**
 * 
 * @author: ssnk
 */

public interface IUserLeaveRequest {

	String getLeaveApplicationId();

	String getUserId();

	Map<LeaveType, Integer> getLeaveTypeVsLeaveCount();

	LeaveRequestStatus getLeaveApplicationStatus();

	String getApprovingUserId();
}
