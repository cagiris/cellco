/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.impl;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cagiris.coho.service.api.IAttendenceReportingService;
import com.cagiris.coho.service.api.ITeamShiftDetails;
import com.cagiris.coho.service.api.IUserShiftInfo;
import com.cagiris.coho.service.db.api.DatabaseManagerException;
import com.cagiris.coho.service.db.api.EntityNotFoundException;
import com.cagiris.coho.service.db.api.IDatabaseManager;
import com.cagiris.coho.service.entity.QUserShiftEntity;
import com.cagiris.coho.service.entity.TeamShiftDetailsEntity;
import com.cagiris.coho.service.entity.UserShiftEntity;
import com.cagiris.coho.service.exception.AttendenceReportingServiceException;
import com.cagiris.coho.service.utils.UniqueIDGenerator;
import com.mysema.query.jpa.hibernate.HibernateQuery;
import com.mysema.query.types.CollectionExpression;

/**
 *
 * @author: abhishek
 */

public class AttendenceReportingService implements IAttendenceReportingService {

    private static final Logger logger = LoggerFactory.getLogger(AttendenceReportingService.class);

    private IDatabaseManager databaseManager;

    public AttendenceReportingService(IDatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public IUserShiftInfo startUserShift(Long teamId, String userId) throws AttendenceReportingServiceException {
        Date currentTime = new Date();
        UserShiftEntity userShiftEntity = new UserShiftEntity();
        userShiftEntity.setUserId(userId);
        userShiftEntity.setShiftStartTime(currentTime);
        userShiftEntity.setShiftEndTime(currentTime);
        userShiftEntity.setShiftId(getShiftId());
        userShiftEntity.setDateAdded(currentTime);
        userShiftEntity.setDateModified(currentTime);
        userShiftEntity.setTeamId(teamId);
        try {
            databaseManager.save(userShiftEntity);
        } catch (DatabaseManagerException e) {
            logger.error("Error while adding user shift:{}", e.getMessage(), e);
            throw new AttendenceReportingServiceException(e);
        }
        return userShiftEntity;
    }

    private String getShiftId() {
        // TODO change
        UniqueIDGenerator uid = new UniqueIDGenerator("shiftId");
        return uid.getNextUID("shiftId");
    }

    @Override
    public IUserShiftInfo endUserShift(String shiftId) throws AttendenceReportingServiceException {
        UserShiftEntity userShiftEntity = null;
        try {
            userShiftEntity = databaseManager.get(UserShiftEntity.class, shiftId);
            Date currentTime = new Date();
            userShiftEntity.setDateModified(currentTime);
            userShiftEntity.setShiftEndTime(currentTime);
            databaseManager.update(userShiftEntity);
            return userShiftEntity;
        } catch (DatabaseManagerException | EntityNotFoundException e) {
            logger.error("Error while updating user shift", e);
            throw new AttendenceReportingServiceException("invalid.shift.id");
        }
    }

    @Override
    public ITeamShiftDetails createTeamShiftDetails(Long teamId, Date shiftStartTime, Date shiftEndTime,
            boolean autoExpire) throws AttendenceReportingServiceException {

        TeamShiftDetailsEntity teamShiftDetails = new TeamShiftDetailsEntity();
        Date currentTime = new Date();
        teamShiftDetails.setDateAdded(currentTime);
        teamShiftDetails.setDateModified(currentTime);
        teamShiftDetails.setTeamId(teamId);
        teamShiftDetails.setShiftStartTime(shiftStartTime);
        teamShiftDetails.setShiftEndTime(shiftEndTime);
        teamShiftDetails.setAutoExpire(autoExpire);
        try {
            databaseManager.save(teamShiftDetails);
        } catch (DatabaseManagerException e) {
            logger.error("Error while adding teams shift details:{}", e.getMessage(), e);
            throw new AttendenceReportingServiceException(e);
        }

        return null;
    }

    @Override
    public ITeamShiftDetails updateTeamShiftDetails(Long teamId, Date shiftStartTime, Date shiftEndTime,
            boolean autoExpire) throws AttendenceReportingServiceException {

        try {
            TeamShiftDetailsEntity teamShiftDetails = databaseManager.get(TeamShiftDetailsEntity.class, teamId);
            //TeamShiftDetailsEntity teamShiftDetails = new TeamShiftDetailsEntity();
            Date currentTime = new Date();
            teamShiftDetails.setDateModified(currentTime);
            teamShiftDetails.setTeamId(teamId);
            teamShiftDetails.setShiftStartTime(shiftStartTime);
            teamShiftDetails.setShiftEndTime(shiftEndTime);
            teamShiftDetails.setAutoExpire(autoExpire);
            databaseManager.update(teamShiftDetails);
        } catch (DatabaseManagerException | EntityNotFoundException e) {
            logger.error("Error while adding teams shift details:{}", e.getMessage(), e);
            throw new AttendenceReportingServiceException(e);
        }

        return null;
    }

    @Override
    public List<? extends IUserShiftInfo> getCurrentUserShiftInfo(String userId)
            throws AttendenceReportingServiceException {
        try {
            QUserShiftEntity qUserShiftEntity = QUserShiftEntity.userShiftEntity;
            HibernateQuery hibernateQuery = new HibernateQuery().from(qUserShiftEntity).where(
                    qUserShiftEntity.userId.eq(userId));
            List<UserShiftEntity> executeQueryAndGetResults = databaseManager.executeQueryAndGetResults(hibernateQuery,
                    qUserShiftEntity);
            return executeQueryAndGetResults;
        } catch (DatabaseManagerException e) {
            logger.error("Error while retrieving User Shift List:{}", e.getMessage(), e);
            throw new AttendenceReportingServiceException(e);
        }
    }

    @Override
    public List<? extends IUserShiftInfo> queryUserShiftInfo(List<String> userIds, Date startTime, Date endTime)
            throws AttendenceReportingServiceException {

        try {
            QUserShiftEntity qUserShiftEntity = QUserShiftEntity.userShiftEntity;
            HibernateQuery hibernateQuery = new HibernateQuery().from(qUserShiftEntity).where(
                    qUserShiftEntity.shiftStartTime.gt(startTime).and(
                            qUserShiftEntity.shiftEndTime.lt(endTime).and(
                                    qUserShiftEntity.userId.eqAny((CollectionExpression<?, ? super String>)userIds))));

            List<UserShiftEntity> executeQueryAndGetResults = databaseManager.executeQueryAndGetResults(hibernateQuery,
                    qUserShiftEntity);
            return executeQueryAndGetResults;
        } catch (DatabaseManagerException e) {
            logger.error("Error while retrieving User Shift List:{}", e.getMessage(), e);
            throw new AttendenceReportingServiceException(e);
        }
    }

    @Override
    public IUserShiftInfo updateUserShiftInfo(String shiftId, Date shiftStartTime, Date shiftEndTime)
            throws AttendenceReportingServiceException {
        UserShiftEntity userShiftEntity = null;
        try {
            userShiftEntity = databaseManager.get(UserShiftEntity.class, shiftId);
            Date currentTime = new Date();
            userShiftEntity.setDateModified(currentTime);
            userShiftEntity.setShiftStartTime(shiftStartTime);
            userShiftEntity.setShiftEndTime(shiftEndTime);
            databaseManager.update(userShiftEntity);
            return userShiftEntity;
        } catch (DatabaseManagerException | EntityNotFoundException e) {
            logger.error("Error while updating user shift info", e);
            throw new AttendenceReportingServiceException("invalid.shift.id");
        }
    }

}
