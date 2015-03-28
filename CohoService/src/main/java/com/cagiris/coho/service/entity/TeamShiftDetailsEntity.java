/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.cagiris.coho.service.api.ITeamShiftDetails;

/**
 *
 * @author: abhishek
 */

@Entity(name = "team_shift_details")
public class TeamShiftDetailsEntity implements ITeamShiftDetails {

	 private Long teamId;

     private Long shiftStartTime;

     private Long shiftEndTime;

    @Id
	@Override
	public Long getTeamId() {
		return teamId;
	}

	public void setTeamId(Long teamId) {
		this.teamId = teamId;
	}

	@Override
	public Long getShiftStartTime() {
		return shiftStartTime;
	}

	public void setShiftStartTime(Long shiftStartTime) {
		this.shiftStartTime = shiftStartTime;
	}

	@Override
	public Long getShiftEndTime() {
		return shiftEndTime;
	}

	public void setShiftEndTime(Long shiftEndTime) {
		this.shiftEndTime = shiftEndTime;
	}

}
