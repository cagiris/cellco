/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.impl;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cagiris.coho.service.api.ILeaveManagementService;
import com.cagiris.coho.service.api.IUserLeaveQuota;
import com.cagiris.coho.service.api.IUserLeaveRequest;
import com.cagiris.coho.service.api.IUserRoleLeaveQuota;
import com.cagiris.coho.service.api.LeaveRequestStatus;
import com.cagiris.coho.service.api.LeaveType;
import com.cagiris.coho.service.api.UserRole;
import com.cagiris.coho.service.db.api.DatabaseManagerException;
import com.cagiris.coho.service.db.api.EntityNotFoundException;
import com.cagiris.coho.service.db.api.IDatabaseManager;
import com.cagiris.coho.service.entity.UserLeaveRequestEntity;
import com.cagiris.coho.service.exception.LeaveManagementServiceException;

/**
 *
 * @author: phugga
 */

public class LeaveManagementService implements ILeaveManagementService {

    private static final Logger logger = LoggerFactory.getLogger(HierarchyService.class);

    private IDatabaseManager databaseManager;

    public LeaveManagementService(IDatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public IUserLeaveRequest applyForLeave(String userId, String approvingUserId,
            Map<LeaveType, Integer> leaveTypeVsLeaveCount) throws LeaveManagementServiceException {
        UserLeaveRequestEntity userLeaveRequestEntity = new UserLeaveRequestEntity();
        userLeaveRequestEntity.setApprovingUserId(approvingUserId);
        userLeaveRequestEntity.setLeaveApplicationId("dsfsdf125664");
        userLeaveRequestEntity.setUserId(userId);
        userLeaveRequestEntity.setLeaveTypeVsLeaveCount(leaveTypeVsLeaveCount);
        try {
            Serializable save = databaseManager.save(userLeaveRequestEntity);
            UserLeaveRequestEntity userLeaveRequestEntity2 = databaseManager.get(UserLeaveRequestEntity.class, save);
            System.out.println(userLeaveRequestEntity2);
            return userLeaveRequestEntity2;
        } catch (DatabaseManagerException | EntityNotFoundException e) {
            logger.error("error while adding user leave request", e);
        }
        return null;
    }

    @Override
    public IUserLeaveRequest updateLeaveRequestStatus(String leaveApplicationId, LeaveRequestStatus leaveStatus)
            throws LeaveManagementServiceException {
        return null;
    }

    @Override
    public IUserLeaveQuota getUserLeaveQuota(String userId) throws LeaveManagementServiceException {
        return null;
    }

    @Override
    public IUserRoleLeaveQuota updateLeaveQuotaForRole(UserRole userRole, Map<LeaveType, Integer> leaveTypeVsLeaveCount)
            throws LeaveManagementServiceException {
        return null;
    }

    @Override
    public List<IUserLeaveRequest> getLeaveRequestsByUserId(String userId, LeaveRequestStatus leaveRequestStatus)
            throws LeaveManagementServiceException {
        return null;
    }

    @Override
    public List<? extends IUserLeaveRequest> getAllPendingLeaveRequestsByLeaveStatus(String approvingUserId,
            LeaveRequestStatus leaveStatus) throws LeaveManagementServiceException {
        return null;
    }

}
