/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.model;

import java.io.Serializable;
import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.cagiris.coho.service.api.IUserLeaveRequest;
import com.cagiris.coho.service.api.LeaveRequestStatus;

/**
 * @author Ashish Jindal
 *
 */
public class LeaveRequestBean extends AbstractBean implements ICRUDBean {

    private String leaveApplicationId;
    private String requestSubject;
    private String requestDescription;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date leaveStartDate;
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date leaveEndDate;
    private LeaveRequestStatus leaveRequestStatus;
    private Integer leaveCount;

    public LeaveRequestBean() {
    }

    public LeaveRequestBean(IUserLeaveRequest userLeaveRequest) {
        this.leaveApplicationId = userLeaveRequest.getLeaveApplicationId();
        this.requestSubject = userLeaveRequest.getRequestSubject();
        this.requestDescription = userLeaveRequest.getRequestDescription();
        this.leaveCount = userLeaveRequest.getRequiredLeaveCount();
        this.leaveStartDate = userLeaveRequest.getLeaveStartDate();
        this.leaveEndDate = userLeaveRequest.getLeaveEndDate();
        this.leaveRequestStatus = userLeaveRequest.getLeaveApplicationStatus();
    }

    public static LeaveRequestBean mapToBean(IUserLeaveRequest userLeaveRequest) {
        LeaveRequestBean leaveRequestBean = new LeaveRequestBean(userLeaveRequest);
        return leaveRequestBean;
    }

    @Override
    public Serializable getEntityId() {
        return leaveApplicationId;
    }

    public String getRequestSubject() {
        return requestSubject;
    }

    public void setRequestSubject(String requestSubject) {
        this.requestSubject = requestSubject;
    }

    public String getLeaveApplicationId() {
        return leaveApplicationId;
    }

    public void setLeaveApplicationId(String leaveApplicationId) {
        this.leaveApplicationId = leaveApplicationId;
    }

    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    public Date getLeaveStartDate() {
        return leaveStartDate;
    }

    public void setLeaveStartDate(Date leaveStartDate) {
        this.leaveStartDate = leaveStartDate;
    }

    public Date getLeaveEndDate() {
        return leaveEndDate;
    }

    public void setLeaveEndDate(Date leaveEndDate) {
        this.leaveEndDate = leaveEndDate;
    }

    public LeaveRequestStatus getLeaveRequestStatus() {
        return leaveRequestStatus;
    }

    public void setLeaveRequestStatus(LeaveRequestStatus leaveRequestStatus) {
        this.leaveRequestStatus = leaveRequestStatus;
    }

    public Integer getLeaveCount() {
        return leaveCount;
    }

    public void setLeaveCount(Integer leaveCount) {
        this.leaveCount = leaveCount;
    }

}
