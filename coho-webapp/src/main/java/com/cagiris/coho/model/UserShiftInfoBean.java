/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.cagiris.coho.controller.ControllerUtils;
import com.cagiris.coho.service.api.IUserShiftInfo;

/**
 *
 * @author: ssnk
 */

public class UserShiftInfoBean extends AbstractBean {
    private String shiftId;

    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm aaa")
    private Date shiftStartTime;

    @DateTimeFormat(pattern = "dd/MM/yyyy hh:mm aaa")
    private Date shiftEndTime;

    private Long teamId;

    private String userId;

    private String shiftStartReason;

    private String shiftEndReason;

    private String shiftDuration;

    public UserShiftInfoBean() {
    }

    public UserShiftInfoBean(IUserShiftInfo userShiftInfo) {
        this.shiftId = userShiftInfo.getShiftId();
        this.shiftStartTime = userShiftInfo.getShiftStartTime();
        this.shiftEndTime = userShiftInfo.getShiftEndTime();
        this.teamId = userShiftInfo.getTeamId();
        this.userId = userShiftInfo.getUserId();
        this.shiftStartReason = userShiftInfo.getShiftStartReason();
        this.shiftEndReason = userShiftInfo.getShiftEndReason();
        this.shiftDuration = ControllerUtils.getFormattedTimeForMS(userShiftInfo.getShiftDuration());
    }

    public static UserShiftInfoBean mapToBean(IUserShiftInfo userShiftInfo) {
        return new UserShiftInfoBean(userShiftInfo);
    }

    public String getShiftId() {
        return shiftId;
    }

    public void setShiftId(String shiftId) {
        this.shiftId = shiftId;
    }

    public Date getShiftStartTime() {
        return shiftStartTime;
    }

    public void setShiftStartTime(Date shiftStartTime) {
        this.shiftStartTime = shiftStartTime;
    }

    public Date getShiftEndTime() {
        return shiftEndTime;
    }

    public void setShiftEndTime(Date shiftEndTime) {
        this.shiftEndTime = shiftEndTime;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getShiftStartReason() {
        return shiftStartReason;
    }

    public void setShiftStartReason(String shiftStartReason) {
        this.shiftStartReason = shiftStartReason;
    }

    public String getShiftEndReason() {
        return shiftEndReason;
    }

    public void setShiftEndReason(String shiftEndReason) {
        this.shiftEndReason = shiftEndReason;
    }

    public String getShiftDuration() {
        return this.shiftDuration;
    }

    public void setShiftDuration(String formattedShiftDuration) {
        this.shiftDuration = formattedShiftDuration;
    }
}
