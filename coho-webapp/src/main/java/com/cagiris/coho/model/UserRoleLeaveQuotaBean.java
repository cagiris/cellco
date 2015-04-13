/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.model;

import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import com.cagiris.coho.service.api.IUserRoleLeaveQuota;
import com.cagiris.coho.service.api.LeaveAccumulationPolicy;
import com.cagiris.coho.service.api.LeaveType;
import com.cagiris.coho.service.api.UserRole;

/**
 *
 * @author: ssnk
 */

public class UserRoleLeaveQuotaBean extends AbstractBean implements ICRUDBean {
    private Long userLeaveQuotaId;
    private UserRole userRole;
    private Long organizationId;
    private Map<LeaveType, Integer> leaveTypeVsLeaveCount;
    private LeaveAccumulationPolicy leaveAccumulationPolicy;

    public UserRoleLeaveQuotaBean() {
    }

    public UserRoleLeaveQuotaBean(IUserRoleLeaveQuota userRoleLeaveQuota) {
        this.userLeaveQuotaId = userRoleLeaveQuota.getUserLeaveQuotaId();
        this.userRole = userRoleLeaveQuota.getUserRole();
        this.leaveAccumulationPolicy = userRoleLeaveQuota.getLeaveAccumulationPolicy();
        this.leaveTypeVsLeaveCount = new TreeMap<LeaveType, Integer>(userRoleLeaveQuota.getLeaveTypeVsLeaveCount());
        this.organizationId = userRoleLeaveQuota.getOrganizationId();
    }

    public static UserRoleLeaveQuotaBean mapToBean(IUserRoleLeaveQuota userRoleLeaveQuota) {
        return new UserRoleLeaveQuotaBean(userRoleLeaveQuota);
    }

    public Long getUserLeaveQuotaId() {
        return userLeaveQuotaId;
    }

    public void setUserLeaveQuotaId(Long userLeaveQuotaId) {
        this.userLeaveQuotaId = userLeaveQuotaId;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public Map<LeaveType, Integer> getLeaveTypeVsLeaveCount() {
        return leaveTypeVsLeaveCount;
    }

    public void setLeaveTypeVsLeaveCount(Map<LeaveType, Integer> leaveTypeVsLeaveCount) {
        this.leaveTypeVsLeaveCount = leaveTypeVsLeaveCount;
    }

    public LeaveAccumulationPolicy getLeaveAccumulationPolicy() {
        return leaveAccumulationPolicy;
    }

    public void setLeaveAccumulationPolicy(LeaveAccumulationPolicy leaveAccumulationPolicy) {
        this.leaveAccumulationPolicy = leaveAccumulationPolicy;
    }

    @Override
    public Serializable getEntityId() {
        return userLeaveQuotaId;
    }

}
