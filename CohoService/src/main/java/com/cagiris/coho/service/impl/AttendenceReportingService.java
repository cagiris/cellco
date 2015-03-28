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
import com.cagiris.coho.service.db.api.IDatabaseManager;
import com.cagiris.coho.service.entity.UserShiftEntity;
import com.cagiris.coho.service.exception.AttendanceReportingServiceException;

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
	public IUserShiftInfo startUserShift(Long teamId, String userId) throws AttendanceReportingServiceException {
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
			throw new AttendanceReportingServiceException(e);
		}
		return userShiftEntity;
	}

	private String getShiftId() {
		// TODO change
		return "shift";
	}

	@Override
	public IUserShiftInfo endUserShift(String shiftId) throws AttendanceReportingServiceException {
		UserShiftEntity userShiftEntity;
		try {
			userShiftEntity = databaseManager.get(UserShiftEntity.class, shiftId);
			Date currentTime = new Date();
			userShiftEntity.setDateModified(currentTime);
			userShiftEntity.setShiftEndTime(currentTime);
			databaseManager.save(userShiftEntity);
			return userShiftEntity;
		} catch (DatabaseManagerException e) {
			logger.error("Error while updating user shift", e);
			throw new AttendanceReportingServiceException("invalid.shift.id");
		}
	}

	@Override
	public ITeamShiftDetails updateTeamShiftDetails(Long teamId, Long shiftStartTime, Long shiftEndTime,
			boolean autoExpire) throws AttendanceReportingServiceException {
		return null;
	}

	@Override
	public List<? extends IUserShiftInfo> getCurrentUserShiftInfo(String userId)
			throws AttendanceReportingServiceException {
		return null;
	}

	@Override
	public List<? extends IUserShiftInfo> queryUserShiftInfo(List<String> userIds, Long startDate, Long endDate)
			throws AttendanceReportingServiceException {
		return null;
	}

	@Override
	public IUserShiftInfo updateUserShiftInfo(String shiftId, Long shiftStartTime, Long shiftEndTime)
			throws AttendanceReportingServiceException {
		return null;
	}

}
