/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.model;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Nonnull;
import javax.validation.GroupSequence;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.cagiris.coho.service.api.IUserLeaveRequest;
import com.cagiris.coho.service.api.LeaveRequestStatus;

/**
 * @author Ashish Jindal
 *
 */
@GroupSequence({ValidationCheckForEmpty.class, ValidationCheckForLength.class, ValidationCheckForPattern.class,
        LeaveRequestBean.class})
public class LeaveRequestBean extends AbstractBean implements ICRUDBean {

    @NotBlank(message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
    @Size(min = 1, max = 50, message = "Length should be between {min} and {max}",
          groups = ValidationCheckForLength.class)
    private String requestSubject;

    @NotBlank(message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
    @Size(min = 1, max = 500, message = "Too long, (max {max} characters)", groups = ValidationCheckForLength.class)
    private String requestDescription;

    @Nonnull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date leaveStartDate;

    @Nonnull
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date leaveEndDate;

    private String leaveApplicationId;
    private LeaveRequestStatus leaveRequestStatus;
    private Integer leaveCount;
    private String userId;

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
        this.setUserId(userLeaveRequest.getUserId());
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
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
}
