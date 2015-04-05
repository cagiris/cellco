/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cagiris.coho.service.exception.LeaveManagementServiceException;

/**
 * 
 * @author: ssnk
 */

public interface ILeaveManagementService {

    /**
     * Called when a user applies for a leave. The leave request
     */
    IUserLeaveRequest applyForLeave(String userId, String approvingUserId,
            Map<LeaveType, Integer> leaveTypeVsLeaveCount, Date leaveStartDate, Date leaveEndDate,
            String requestDescription) throws LeaveManagementServiceException;

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

    /**
     * The number of leaves can be configured per user role.
     */
    IUserRoleLeaveQuota updateLeaveQuotaForRole(UserRole userRole, Map<LeaveType, Integer> leaveTypeVsLeaveCount)
            throws LeaveManagementServiceException;

    /**
     * This will return the the list of leave requests by leaveRequestStatus
     * applied by userId.
     */
    List<? extends IUserLeaveRequest> getLeaveRequestsByUserId(String userId, LeaveRequestStatus leaveRequestStatus)
            throws LeaveManagementServiceException;

    /**
     * This will return the leave requests pending approval on approvingUserId
     * by leave status.
     */
    List<? extends IUserLeaveRequest> getAllPendingLeaveRequestsByLeaveStatus(String approvingUserId,
            LeaveRequestStatus leaveStatus) throws LeaveManagementServiceException;
}
