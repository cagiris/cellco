/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.model;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.validation.GroupSequence;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.cagiris.coho.service.api.IUserLeaveRequest;
import com.cagiris.coho.service.api.LeaveRequestStatus;

/**
 * @author Ashish Jindal
 *
 */
@GroupSequence({ ValidationCheckForEmpty.class, ValidationCheckForLength.class, ValidationCheckForPattern.class, LeaveRequestBean.class})
public class LeaveRequestBean extends AbstractBean implements ICRUDBean {

	public static final String DATE_FORMAT = "dd-MM-YYYY";
	
	@NotBlank (message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
	@Size (min = 1, max = 50, message = "Length should be between {min} and {max}", groups = ValidationCheckForLength.class)
    private String requestSubject;

	@NotBlank (message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
	@Size (min = 1, max = 500, message = "Too long, (max {max} characters)", groups = ValidationCheckForLength.class)
    private String requestDescription;

	@NotBlank (message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
	@Pattern (regexp = "^\\d\\d-\\d\\d-\\d\\d\\d\\d$", message = "Please enter a valid date (dd-MM-YYYY)", groups = ValidationCheckForPattern.class)
	@Size (min = 10, max = 10, message = "Invalid date (dd-MM-YYYY)", groups = ValidationCheckForLength.class)
    private String leaveStartDate;

	@NotBlank (message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
	@Pattern (regexp = "^\\d\\d-\\d\\d-\\d\\d\\d\\d$", message = "Please enter a valid date (dd-MM-YYYY)", groups = ValidationCheckForPattern.class)
	@Size (min = 10, max = 10, message = "Invalid date (dd-MM-YYYY)", groups = ValidationCheckForLength.class)
    private String leaveEndDate;

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
        
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        this.leaveStartDate = simpleDateFormat.format(userLeaveRequest.getLeaveStartDate());
        this.leaveEndDate = simpleDateFormat.format(userLeaveRequest.getLeaveEndDate());
        
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

    public Date getLeaveStartDateFormatted() throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        return simpleDateFormat.parse(leaveStartDate);
    }

    public String getLeaveStartDate() {
        return leaveStartDate;
    }
    
    public void setLeaveStartDate(String leaveStartDate) {
        this.leaveStartDate = leaveStartDate;
    }

    public String getLeaveEndDate() {
        return leaveEndDate;
    }
    
    public Date getLeaveEndDateFormatted() throws ParseException {

        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(DATE_FORMAT);
        return simpleDateFormat.parse(leaveEndDate);
    }

    public void setLeaveEndDate(String leaveEndDate) {
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
