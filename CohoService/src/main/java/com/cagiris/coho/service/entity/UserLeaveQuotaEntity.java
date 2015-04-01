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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;

import com.cagiris.coho.service.api.IUserLeaveQuota;
import com.cagiris.coho.service.api.LeaveType;

/**
 *
 * @author: ssnk
 */
@Entity
public class UserLeaveQuotaEntity extends BaseEntity implements IUserLeaveQuota {

    private String userLeaveQuotaId;
    private String userId;
    private Map<LeaveType, Integer> leaveTypeVsLeaveQuota;

    @Override
    @Column(name = "user_id")
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @ElementCollection(targetClass = java.lang.String.class)
    @MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name = "leave_type")
    @Column(name = "leave_quota")
    @CollectionTable(name = "leave_type_vs_quota", joinColumns = @JoinColumn(name = "user_id"))
    @Override
    public Map<LeaveType, Integer> getLeaveTypeVsLeaveQuota() {
        return leaveTypeVsLeaveQuota;
    }

    public void setLeaveTypeVsLeaveQuota(Map<LeaveType, Integer> leaveTypeVsLeaveQuota) {
        this.leaveTypeVsLeaveQuota = leaveTypeVsLeaveQuota;
    }

    @Id
    public String getUserLeaveQuotaId() {
        return userLeaveQuotaId;
    }

    public void setUserLeaveQuotaId(String userLeaveQuotaId) {
        this.userLeaveQuotaId = userLeaveQuotaId;
    }

}
