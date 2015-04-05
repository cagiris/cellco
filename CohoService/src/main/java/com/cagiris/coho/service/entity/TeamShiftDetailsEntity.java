/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

import com.cagiris.coho.service.api.ITeamShiftDetails;

/**
 *
 * @author: abhishek
 */

@Entity
public class TeamShiftDetailsEntity extends BaseEntity implements ITeamShiftDetails {

    private Long teamId;
    private Date shiftStartTime;
    private Date shiftEndTime;
    private boolean autoExpire;
    private Long shiftBuffer;

    @Id
    @Override
    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
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

    public boolean isAutoExpire() {
        return autoExpire;
    }

    public void setAutoExpire(boolean autoExpire) {
        this.autoExpire = autoExpire;
    }

    @Transient
    public Long getShiftDuration() {
        if ((shiftStartTime == null) || (shiftEndTime == null)) {
            return 0L;
        }
        return shiftEndTime.getTime() - shiftStartTime.getTime();
    }

    @Override
    public Long getShiftBuffer() {
        return shiftBuffer;
    }

    public void setShiftBuffer(Long shiftBuffer) {
        this.shiftBuffer = shiftBuffer;
    }

}
