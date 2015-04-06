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
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.MapKeyColumn;
import javax.persistence.Transient;

import com.cagiris.coho.service.api.IUserLeaveQuota;
import com.cagiris.coho.service.api.LeaveType;

/**
 *
 * @author: ssnk
 */
@Entity
public class UserLeaveQuotaEntity extends BaseEntity implements IUserLeaveQuota {

    private String userId;
    private Map<LeaveType, Integer> leaveTypeVsLeaveQuota;

    private Date lastLeaveAccumulationDate;

    @Id
    @Override
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @ElementCollection(fetch = FetchType.EAGER)
    @MapKeyColumn(name = "leave_type")
    @Column(name = "leave_quota")
    @CollectionTable
    @Override
    public Map<LeaveType, Integer> getLeaveTypeVsLeaveQuota() {
        return leaveTypeVsLeaveQuota;
    }

    public void setLeaveTypeVsLeaveQuota(Map<LeaveType, Integer> leaveTypeVsLeaveQuota) {
        this.leaveTypeVsLeaveQuota = leaveTypeVsLeaveQuota;
    }

    @Transient
    @Override
    public Integer getTotalLeaveCount() {
        Integer totalLeaveCount = 0;
        for (Map.Entry<LeaveType, Integer> entry : leaveTypeVsLeaveQuota.entrySet()) {
            totalLeaveCount += entry.getValue();
        }
        return totalLeaveCount;
    }

    @Override
    public Date getLastLeaveAccumulationDate() {
        return lastLeaveAccumulationDate;
    }

    public void setLastLeaveAccumulationDate(Date lastLeaveAccumulationDate) {
        this.lastLeaveAccumulationDate = lastLeaveAccumulationDate;
    }

}
