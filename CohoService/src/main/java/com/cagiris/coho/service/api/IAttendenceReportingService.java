/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

import java.util.Date;
import java.util.List;

import com.cagiris.coho.service.exception.AttendenceReportingServiceException;
import com.cagiris.coho.service.exception.NoActiveShiftForUserException;
import com.cagiris.coho.service.exception.ResourceNotFoundException;

/**
 *
 * @author: ssnk
 */

public interface IAttendenceReportingService {

    /**
     * This api will be called when a user starts his shift.
     */
    IUserShiftInfo startUserShift(Long teamId, String userId) throws AttendenceReportingServiceException;

    /**
     * This will be called when a user ends his shift.
     */
    IUserShiftInfo endUserShift(String shiftId, String shiftEndReason) throws AttendenceReportingServiceException;

    /**
     * This can be used to create the shift timings for a particular team. All
     * users of a team will be automatically logged out if they won't log out
     * before shiftEndTime and autoExpire is true.
     */
    ITeamShiftDetails createTeamShiftDetails(Long teamId, Date shiftStartTime, Date shiftEndTime, Long shiftBuffer,
            Long minimumGapBetweenShifts, boolean autoExpire) throws AttendenceReportingServiceException;

    /**
     * This can be used to update the shift timings for a particular team. All
     * users of a team will be automatically logged out if they won't log out
     * before shiftEndTime and autoExpire is true.
     */
    ITeamShiftDetails updateTeamShiftDetails(Long teamId, Date shiftStartTime, Date shiftEndTime, Long shiftBuffer,
            Long minimumGapBetweenShifts, boolean autoExpire) throws AttendenceReportingServiceException;

    ITeamShiftDetails getTeamShiftDetails(Long teamId) throws AttendenceReportingServiceException,
            ResourceNotFoundException;

    /**
     * This will return the shift info of an active shift. i.e. the user is not
     * logged out yet.
     */
    List<? extends IUserShiftInfo> getCurrentUserShiftInfo(String userId) throws AttendenceReportingServiceException;

    IUserShiftInfo getCurrentUserShiftInTeam(Long teamId, String userId) throws AttendenceReportingServiceException,
            NoActiveShiftForUserException;

    /**
     * This can be used by admin to query shift details for different users.
     */
    List<? extends IUserShiftInfo> queryUserShiftInfo(List<String> userIds, Date startDate, Date endDate)
            throws AttendenceReportingServiceException;

    /**
     * This api can be used to manually modify the shift details of a users by
     * admin.
     */
    IUserShiftInfo updateUserShiftInfo(String shiftId, Date shiftStartTime, Date shiftEndTime, String updateReason)
            throws AttendenceReportingServiceException;

    List<? extends IUserShiftInfo> getAllActiveShiftInfos() throws AttendenceReportingServiceException;

    IUserShiftInfo getLastShiftDetailsForUser(Long teamId, String userId) throws AttendenceReportingServiceException;

}
