/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.impl;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
import com.cagiris.coho.service.exception.NoActiveShiftForUserException;
import com.cagiris.coho.service.exception.ResourceNotFoundException;
import com.cagiris.coho.service.utils.UniqueIDGenerator;
import com.mysema.query.jpa.hibernate.HibernateQuery;

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
        IUserShiftInfo lastShiftDetailsForUser = getLastShiftDetailsForUser(teamId, userId);
        ITeamShiftDetails teamShiftDetails;
        try {
            teamShiftDetails = getTeamShiftDetails(teamId);
        } catch (ResourceNotFoundException e) {
            throw new AttendenceReportingServiceException(e);
        }
        DateTime currentDateTime = new DateTime();
        if (lastShiftDetailsForUser != null) {
            Integer minimumGapBetweenShifts = Optional.ofNullable(teamShiftDetails.getMinimumGapBetweenShifts())
                    .map(Long::intValue).orElse(0);
            DateTime nextEligibleLoginTime = new DateTime(lastShiftDetailsForUser.getShiftEndTime())
                    .plusMinutes(minimumGapBetweenShifts.intValue());
            if (currentDateTime.isBefore(nextEligibleLoginTime)) {
                throw new UsernameNotFoundException("Cannot start shift before minimum gap");
            }
        }
        UserShiftEntity userShiftEntity = new UserShiftEntity();
        userShiftEntity.setUserId(userId);
        userShiftEntity.setShiftStartTime(currentTime);
        userShiftEntity.setShiftId(getShiftId(userId));
        userShiftEntity.setDateAdded(currentTime);
        userShiftEntity.setDateModified(currentTime);
        userShiftEntity.setTeamId(teamId);
        userShiftEntity.setShiftStartReason("Started_By_User");
        try {
            Serializable id = databaseManager.save(userShiftEntity);
            return databaseManager.get(UserShiftEntity.class, id);
        } catch (DatabaseManagerException | EntityNotFoundException e) {
            logger.error("Error while adding user shift:{}", e.getMessage(), e);
            throw new AttendenceReportingServiceException(e);
        }
    }

    private String getShiftId(String userId) {
        UniqueIDGenerator uid = new UniqueIDGenerator("shift");
        return uid.getNextUID(userId);
    }

    @Override
    public IUserShiftInfo endUserShift(String shiftId, String shiftEndReason)
            throws AttendenceReportingServiceException {
        UserShiftEntity userShiftEntity = null;
        try {
            userShiftEntity = databaseManager.get(UserShiftEntity.class, shiftId);
            Date currentTime = new Date();
            userShiftEntity.setDateModified(currentTime);
            userShiftEntity.setShiftEndTime(currentTime);
            userShiftEntity.setShiftEndReason(shiftEndReason);
            databaseManager.update(userShiftEntity);
            return userShiftEntity;
        } catch (DatabaseManagerException | EntityNotFoundException e) {
            logger.error("Error while updating user shift", e);
            throw new AttendenceReportingServiceException("invalid.shift.id");
        }
    }

    @Override
    public ITeamShiftDetails createTeamShiftDetails(Long teamId, Date shiftStartTime, Date shiftEndTime,
            Long shiftBuffer, Long minimumGapBetweenShifts, boolean autoExpire)
            throws AttendenceReportingServiceException {

        TeamShiftDetailsEntity teamShiftDetails = new TeamShiftDetailsEntity();
        Date currentTime = new Date();
        teamShiftDetails.setDateAdded(currentTime);
        teamShiftDetails.setDateModified(currentTime);
        teamShiftDetails.setTeamId(teamId);
        teamShiftDetails.setShiftStartTime(shiftStartTime);
        teamShiftDetails.setShiftEndTime(shiftEndTime);
        teamShiftDetails.setShiftBuffer(shiftBuffer);
        teamShiftDetails.setAutoExpire(autoExpire);
        teamShiftDetails.setMinimumGapBetweenShifts(minimumGapBetweenShifts);
        try {
            Serializable id = databaseManager.save(teamShiftDetails);
            return databaseManager.get(TeamShiftDetailsEntity.class, id);
        } catch (DatabaseManagerException | EntityNotFoundException e) {
            logger.error("Error while adding teams shift details:{}", e.getMessage(), e);
            throw new AttendenceReportingServiceException(e);
        }

    }

    @Override
    public ITeamShiftDetails updateTeamShiftDetails(Long teamId, Date shiftStartTime, Date shiftEndTime,
            Long shiftBuffer, Long minimumGapBetweenShifts, boolean autoExpire)
            throws AttendenceReportingServiceException {
        DateTime shiftStartDateTime = new DateTime(shiftStartTime).withDayOfMonth(2).withYear(2015).withMonthOfYear(1);
        DateTime shiftEndDateTime = new DateTime(shiftEndTime).withDayOfMonth(2).withYear(2015).withMonthOfYear(1);

        try {
            TeamShiftDetailsEntity teamShiftDetails = databaseManager.get(TeamShiftDetailsEntity.class, teamId);
            Date currentTime = new Date();
            teamShiftDetails.setDateModified(currentTime);
            teamShiftDetails.setTeamId(teamId);
            teamShiftDetails.setShiftStartTime(shiftStartDateTime.toDate());
            teamShiftDetails.setShiftEndTime(shiftEndDateTime.toDate());
            teamShiftDetails.setAutoExpire(autoExpire);
            teamShiftDetails.setShiftBuffer(shiftBuffer);
            teamShiftDetails.setMinimumGapBetweenShifts(minimumGapBetweenShifts);
            databaseManager.update(teamShiftDetails);
            return teamShiftDetails;
        } catch (DatabaseManagerException | EntityNotFoundException e) {
            logger.error("Error while adding teams shift details:{}", e.getMessage(), e);
            throw new AttendenceReportingServiceException(e);
        }

    }

    @Override
    public List<? extends IUserShiftInfo> getCurrentUserShiftInfo(String userId)
            throws AttendenceReportingServiceException {
        try {
            QUserShiftEntity qUserShiftEntity = QUserShiftEntity.userShiftEntity;
            HibernateQuery hibernateQuery = new HibernateQuery().from(qUserShiftEntity)
                    .where(qUserShiftEntity.userId.eq(userId)).orderBy(qUserShiftEntity.dateAdded.desc());
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
                            qUserShiftEntity.shiftEndTime.lt(endTime).and(qUserShiftEntity.userId.in(userIds))));

            List<UserShiftEntity> executeQueryAndGetResults = databaseManager.executeQueryAndGetResults(hibernateQuery,
                    qUserShiftEntity);
            return executeQueryAndGetResults;
        } catch (DatabaseManagerException e) {
            logger.error("Error while retrieving User Shift List:{}", e.getMessage(), e);
            throw new AttendenceReportingServiceException(e);
        }
    }

    @Override
    public IUserShiftInfo updateUserShiftInfo(String shiftId, Date shiftStartTime, Date shiftEndTime,
            String updateReason) throws AttendenceReportingServiceException {
        UserShiftEntity userShiftEntity = null;
        try {
            userShiftEntity = databaseManager.get(UserShiftEntity.class, shiftId);
            Date currentTime = new Date();
            userShiftEntity.setDateModified(currentTime);
            userShiftEntity.setShiftStartTime(shiftStartTime);
            userShiftEntity.setShiftEndTime(shiftEndTime);
            userShiftEntity.setShiftEndReason(updateReason);
            databaseManager.update(userShiftEntity);
            return userShiftEntity;
        } catch (DatabaseManagerException | EntityNotFoundException e) {
            logger.error("Error while updating user shift info", e);
            throw new AttendenceReportingServiceException("invalid.shift.id");
        }
    }

    @Override
    public ITeamShiftDetails getTeamShiftDetails(Long teamId) throws AttendenceReportingServiceException,
            ResourceNotFoundException {
        try {
            return databaseManager.get(TeamShiftDetailsEntity.class, teamId);
        } catch (DatabaseManagerException e) {
            throw new AttendenceReportingServiceException(e);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(e);
        }
    }

    @Override
    public List<? extends IUserShiftInfo> getAllActiveShiftInfos() throws AttendenceReportingServiceException {
        QUserShiftEntity qUserShiftEntity = QUserShiftEntity.userShiftEntity;
        HibernateQuery hibernateQuery = new HibernateQuery().from(qUserShiftEntity).where(
                qUserShiftEntity.shiftEndTime.isNull());
        try {
            return databaseManager.executeQueryAndGetResults(hibernateQuery, qUserShiftEntity);
        } catch (DatabaseManagerException e) {
            throw new AttendenceReportingServiceException(e);
        }
    }

    @Override
    public IUserShiftInfo getCurrentUserShiftInTeam(Long teamId, String userId)
            throws AttendenceReportingServiceException, NoActiveShiftForUserException {
        try {
            QUserShiftEntity qUserShiftEntity = QUserShiftEntity.userShiftEntity;
            HibernateQuery hibernateQuery = new HibernateQuery().from(qUserShiftEntity).where(
                    qUserShiftEntity.userId.eq(userId).and(
                            qUserShiftEntity.teamId.eq(teamId).and(qUserShiftEntity.shiftEndTime.isNull())));
            List<UserShiftEntity> executeQueryAndGetResults = databaseManager.executeQueryAndGetResults(hibernateQuery,
                    qUserShiftEntity);
            if (executeQueryAndGetResults.size() == 0) {
                throw new NoActiveShiftForUserException("No active shift for user in team");
            } else {
                return executeQueryAndGetResults.get(0);
            }
        } catch (DatabaseManagerException e) {
            logger.error("Error while retrieving User Shift List:{}", e.getMessage(), e);
            throw new AttendenceReportingServiceException(e);
        }
    }

    @Override
    public IUserShiftInfo getLastShiftDetailsForUser(Long teamId, String userId)
            throws AttendenceReportingServiceException {
        QUserShiftEntity qUserShiftEntity = QUserShiftEntity.userShiftEntity;
        HibernateQuery hibernateQuery = new HibernateQuery().from(qUserShiftEntity)
                .where(qUserShiftEntity.userId.eq(userId).and(qUserShiftEntity.teamId.eq(teamId)))
                .orderBy(qUserShiftEntity.dateAdded.desc()).limit(1);
        try {
            List<UserShiftEntity> executeQueryAndGetResults = databaseManager.executeQueryAndGetResults(hibernateQuery,
                    qUserShiftEntity);
            if (executeQueryAndGetResults.size() > 0) {
                return executeQueryAndGetResults.get(0);
            }
        } catch (DatabaseManagerException e) {
            throw new AttendenceReportingServiceException(e);
        }
        return null;
    }
}
