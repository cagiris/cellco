/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.model;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Nonnull;

import org.springframework.format.annotation.DateTimeFormat;

import com.cagiris.coho.controller.ControllerUtils;
import com.cagiris.coho.service.api.ITeamShiftDetails;

/**
 *
 * @author: ssnk
 */

public class TeamShiftDetailsBean extends AbstractBean implements ICRUDBean {
    private Long teamId;

    @Nonnull
    @DateTimeFormat(pattern = "HH:mm")
    private Date shiftStartTime;

    @Nonnull
    @DateTimeFormat(pattern = "HH:mm")
    private Date shiftEndTime;
    private String shiftDuration;
    private Long shiftBuffer;

    public TeamShiftDetailsBean() {
    }

    public TeamShiftDetailsBean(ITeamShiftDetails teamShiftDetails) {
        this.teamId = teamShiftDetails.getTeamId();
        this.shiftStartTime = teamShiftDetails.getShiftStartTime();
        this.shiftEndTime = teamShiftDetails.getShiftEndTime();
        this.shiftBuffer = teamShiftDetails.getShiftBuffer();
        this.shiftDuration = ControllerUtils.getFormattedTimeForMS(teamShiftDetails.getShiftDuration());
    }

    public static TeamShiftDetailsBean mapToBean(ITeamShiftDetails teamShiftDetails) {
        return new TeamShiftDetailsBean(teamShiftDetails);
    }

    @Override
    public Serializable getEntityId() {
        return this.teamId;
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

    public Long getShiftBuffer() {
        return shiftBuffer;
    }

    public void setShiftBuffer(Long shiftBuffer) {
        this.shiftBuffer = shiftBuffer;
    }

    public String getShiftDuration() {
        return shiftDuration;
    }

    public void setShiftDuration(String shiftDuration) {
        this.shiftDuration = shiftDuration;
    }

}
