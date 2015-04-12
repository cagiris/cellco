/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cagiris.coho.service.api.IUserRoleLeaveQuota;
import com.cagiris.coho.service.api.LeaveAccumulationPolicy;
import com.cagiris.coho.service.api.LeaveType;
import com.cagiris.coho.service.api.UserRole;

/**
 *
 * @author: ssnk
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class UserRoleLeaveQuotaEntity extends BaseEntity implements IUserRoleLeaveQuota {

    private Long userLeaveQuotaId;

    private Long organizationId;

    private UserRole userRole;

    private Map<LeaveType, Integer> leaveTypeVsLeaveCount;

    private LeaveAccumulationPolicy leaveAccumulationPolicy;

    @Override
    public Long getOrganizationId() {
        return organizationId;
    }

    @Override
    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "leave_type")
    @Column(name = "leave_quota")
    @CollectionTable(name = "user_leave_type_vs_quota", joinColumns = {@JoinColumn(name = "user_leave_quota_id")})
    @Override
    public Map<LeaveType, Integer> getLeaveTypeVsLeaveCount() {
        return leaveTypeVsLeaveCount;
    }

    public void setLeaveTypeVsLeaveCount(Map<LeaveType, Integer> leaveTypeVsLeaveCount) {
        this.leaveTypeVsLeaveCount = leaveTypeVsLeaveCount;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    @Enumerated(EnumType.STRING)
    @Override
    public LeaveAccumulationPolicy getLeaveAccumulationPolicy() {
        return leaveAccumulationPolicy;
    }

    public void setLeaveAccumulationPolicy(LeaveAccumulationPolicy leaveAccumulationPolicy) {
        this.leaveAccumulationPolicy = leaveAccumulationPolicy;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getUserLeaveQuotaId() {
        return userLeaveQuotaId;
    }

    public void setUserLeaveQuotaId(Long userLeaveQuotaId) {
        this.userLeaveQuotaId = userLeaveQuotaId;
    }
}
