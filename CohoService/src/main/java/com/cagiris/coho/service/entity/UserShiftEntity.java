/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.cagiris.coho.service.api.IUserShiftInfo;

/**
 *
 * @author: abhishek
 */

@Entity(name = "user_shift")
public class UserShiftEntity extends BaseEntity implements IUserShiftInfo {

	private String shiftId;
	private Date shiftStartTime;
	private Date shiftEndTime;
	private String userId;
	private Long teamId;

	@Id
	@Override
	public String getShiftId() {
		return shiftId;
	}

	public void setShiftId(String shiftId) {
		this.shiftId = shiftId;
	}

	@Override
	public Date getShiftStartTime() {
		return shiftStartTime;
	}

	public void setShiftStartTime(Date shiftStartTime) {
		this.shiftStartTime = shiftStartTime;
	}

	@Override
	public Date getShiftEndTime() {
		return shiftEndTime;
	}

	public void setShiftEndTime(Date shiftEndTime) {
		this.shiftEndTime = shiftEndTime;
	}

	@Transient
	@Override
	public Long getShiftDuration() {
		if (shiftStartTime == null) {
			return 0L;
		}
		if (shiftEndTime == null) {
			return (new Date().getTime()) - shiftStartTime.getTime();
		}
		return shiftEndTime.getTime() - shiftStartTime.getTime();
	}

	@Override
	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	@Override
	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

}
