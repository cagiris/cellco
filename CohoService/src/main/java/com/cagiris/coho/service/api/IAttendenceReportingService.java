/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

import java.util.List;

import com.cagiris.coho.service.exception.AttendanceReportingServiceException;

/**
 * 
 * @author: ssnk
 */

public interface IAttendenceReportingService {

	/**
	 * This api will be called when a user starts his shift.
	 */
	IUserShiftInfo startUserShift(Long teamId, String userId) throws AttendanceReportingServiceException;

	/**
	 * This will be called when a user ends his shift.
	 */
	IUserShiftInfo endUserShift(Long teamId, String userId) throws AttendanceReportingServiceException;

	/**
	 * This can be used to update the shift timings for a particular team. All
	 * users of a team will be automatically logged out if they won't log out
	 * before shiftEndTime and autoExpire is true.
	 */
	ITeamShiftDetails updateTeamShiftDetails(Long teamId, Long shiftStartTime, Long shiftEndTime, boolean autoExpire)
			throws AttendanceReportingServiceException;

	/**
	 * This will return the shift info of an active shift. i.e. the user is not
	 * logged out yet.
	 */
	IUserShiftInfo getCurrentUserShiftInfo(String userId) throws AttendanceReportingServiceException;

	/**
	 * This can be used by admin to query shift details for different users.
	 */
	List<IUserShiftInfo> queryUserShiftInfo(List<String> userIds, Long startDate, Long endDate)
			throws AttendanceReportingServiceException;

	/**
	 * This api can be used to manually modify the shift details of a users by
	 * admin.
	 */
	IUserShiftInfo updateUserShiftInfo(String userId, Long shiftStartTime, Long shiftEndTime)
			throws AttendanceReportingServiceException;

}
