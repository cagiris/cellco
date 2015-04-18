/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */

package com.cagiris.coho.service.entity;

import java.util.Date;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;

import com.cagiris.coho.service.api.IUserLeaveRequest;
import com.cagiris.coho.service.api.LeaveRequestStatus;
import com.cagiris.coho.service.api.LeaveType;

/**
 *
 * @author: ssnk
 */
@Entity
public class UserLeaveRequestEntity extends BaseEntity implements IUserLeaveRequest {

    private String leaveApplicationId;
    private String userId;
    private Map<LeaveType, Integer> leaveTypeVsLeaveCount;
    private LeaveRequestStatus leaveApplicationStatus;
    private String approvingUserId;
    private String requestDescription;
    private String approvingUserComments;
    private Date leaveStartDate;
    private Date leaveEndDate;
    private Integer requiredLeaveCount;
    private String requestSubject;

    @Id
    @Override
    public String getLeaveApplicationId() {
        return leaveApplicationId;
    }

    public void setLeaveApplicationId(String leaveApplicationId) {
        this.leaveApplicationId = leaveApplicationId;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @ElementCollection(targetClass = java.lang.Integer.class, fetch = FetchType.EAGER)
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "leave_type")
    @Column(name = "leave_quota")
    @CollectionTable
    @Override
    public Map<LeaveType, Integer> getLeaveTypeVsLeaveCount() {
        return leaveTypeVsLeaveCount;
    }

    public void setLeaveTypeVsLeaveCount(Map<LeaveType, Integer> leaveTypeVsLeaveCount) {
        this.leaveTypeVsLeaveCount = leaveTypeVsLeaveCount;
    }

    @Override
    public LeaveRequestStatus getLeaveApplicationStatus() {
        return leaveApplicationStatus;
    }

    public void setLeaveApplicationStatus(LeaveRequestStatus leaveApplicationStatus) {
        this.leaveApplicationStatus = leaveApplicationStatus;
    }

    @Override
    public String getApprovingUserId() {
        return approvingUserId;
    }

    public void setApprovingUserId(String approvingUserId) {
        this.approvingUserId = approvingUserId;
    }

    @Override
    public String getRequestDescription() {
        return requestDescription;
    }

    public void setRequestDescription(String requestDescription) {
        this.requestDescription = requestDescription;
    }

    @Override
    public String getApprovingUserComments() {
        return approvingUserComments;
    }

    public void setApprovingUserComments(String approvingUserComments) {
        this.approvingUserComments = approvingUserComments;
    }

    @Override
    public Date getLeaveStartDate() {
        return leaveStartDate;
    }

    public void setLeaveStartDate(Date leaveStartDate) {
        this.leaveStartDate = leaveStartDate;
    }

    @Override
    public Date getLeaveEndDate() {
        return leaveEndDate;
    }

    public void setLeaveEndDate(Date leaveEndDate) {
        this.leaveEndDate = leaveEndDate;
    }

    @Override
    public Integer getRequiredLeaveCount() {
        return requiredLeaveCount;
    }

    public void setRequiredLeaveCount(Integer requiredLeaveCount) {
        this.requiredLeaveCount = requiredLeaveCount;
    }

    @Override
    public String getRequestSubject() {
        return requestSubject;
    }

    public void setRequestSubject(String requestSubject) {
        this.requestSubject = requestSubject;
    }

}
