package com.cagiris.coho.model;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import com.cagiris.coho.service.api.IUserLeaveQuota;
import com.cagiris.coho.service.api.LeaveType;

public class UserLeaveQuotaBean extends AbstractBean {

    private String userId;
    private Map<LeaveType, Integer> leaveTypeVsLeaveQuota;
    private Integer totalLeaveCount;
    private Date lastLeaveAccumulationDate;

    public UserLeaveQuotaBean(IUserLeaveQuota userLeaveQuota) {
        this.userId = userLeaveQuota.getUserId();
        this.leaveTypeVsLeaveQuota = new HashMap(userLeaveQuota.getLeaveTypeVsLeaveQuota());
        this.totalLeaveCount = userLeaveQuota.getTotalLeaveCount();
        this.lastLeaveAccumulationDate = userLeaveQuota.getLastLeaveAccumulationDate();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Map<LeaveType, Integer> getLeaveTypeVsLeaveQuota() {
        return leaveTypeVsLeaveQuota;
    }

    public void setLeaveTypeVsLeaveQuota(Map<LeaveType, Integer> leaveTypeVsLeaveQuota) {
        this.leaveTypeVsLeaveQuota = leaveTypeVsLeaveQuota;
    }

    public Integer getTotalLeaveCount() {
        return totalLeaveCount;
    }

    public void setTotalLeaveCount(Integer totalLeaveCount) {
        this.totalLeaveCount = totalLeaveCount;
    }

    public Date getLastLeaveAccumulationDate() {
        return lastLeaveAccumulationDate;
    }

    public void setLastLeaveAccumulationDate(Date lastLeaveAccumulationDate) {
        this.lastLeaveAccumulationDate = lastLeaveAccumulationDate;
    }

}
