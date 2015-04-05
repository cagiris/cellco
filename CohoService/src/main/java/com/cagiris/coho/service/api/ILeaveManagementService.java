/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cagiris.coho.service.exception.LeaveManagementServiceException;
import com.cagiris.coho.service.exception.ResourceNotFoundException;

/**
 * 
 * @author: ssnk
 */

public interface ILeaveManagementService {

    /**
     * Called when a user applies for a leave. The leave request
     */
    IUserLeaveRequest applyForLeave(String userId, Map<LeaveType, Integer> leaveTypeVsLeaveCount, Date leaveStartDate,
            Date leaveEndDate, String requestSubject, String requestDescription) throws LeaveManagementServiceException;

    // used by the requester to cancel the leave request ... 
    void cancelLeaveRequest(String leaveApplicationId) throws LeaveManagementServiceException;

    /**
     * This api can be used by user to cancel his leave request or by admin to
     * reject or approve a leave request.
     */
    IUserLeaveRequest updateLeaveRequestStatus(String approvingUserId, String leaveApplicationId,
            LeaveRequestStatus leaveStatus, String comments) throws LeaveManagementServiceException;

    /**
     * The available leave count for each user. This will be determined by the
     * user role.
     */
    IUserLeaveQuota getUserLeaveQuota(String userId) throws LeaveManagementServiceException;

    IUserLeaveQuota addUserLeaveQuota(String userId) throws LeaveManagementServiceException;

    IUserLeaveQuota updateUserLeaveQuota(String userId, Map<LeaveType, Integer> leaveTypeVsLeaveCount)
            throws LeaveManagementServiceException;

    /**
     * The number of leaves can be configured per user role.
     */
    IUserRoleLeaveQuota updateLeaveQuotaForRole(Long organizationId, UserRole userRole,
            Map<LeaveType, Integer> leaveTypeVsLeaveCount, LeaveAccumulationPolicy leaveAccumulationPolicy)
            throws LeaveManagementServiceException;

    IUserRoleLeaveQuota getUserRoleQuota(Long organizationId, UserRole userRole)
            throws LeaveManagementServiceException, ResourceNotFoundException;

    /**
     * This will return the the list of leave requests by leaveRequestStatus
     * applied by userId.
     */
    List<? extends IUserLeaveRequest> getLeaveRequestsByUserId(String userId) throws LeaveManagementServiceException;

    /**
     * This will return the leave requests pending approval on approvingUserId
     * by leave status.
     */
    List<? extends IUserLeaveRequest> getAllPendingLeaveRequestsByLeaveStatus(String approvingUserId,
            LeaveRequestStatus leaveStatus) throws LeaveManagementServiceException;

    IAnnualHoliday addAnnualHoliday(Long organizationId, Integer year, Integer day, String description)
            throws LeaveManagementServiceException;

    // weekday Monday = 1 and Sunday = 7 
    IWeeklyHoliday addWeeklyHoliday(Long organizationId, UserRole userRole, Integer weekDay, String description)
            throws LeaveManagementServiceException;

    List<IHoliday> getAllHolidaysForOrganization(Long organizationId) throws LeaveManagementServiceException;

    List<? extends IAnnualHoliday> getAllAnnualHolidays(Long organizationId) throws LeaveManagementServiceException;

    List<? extends IWeeklyHoliday> getAllWeeklyHolidays(Long organizationId) throws LeaveManagementServiceException;

    IUserLeaveRequest getLeaveRequestById(String leaveApplicationId) throws LeaveManagementServiceException;
}
