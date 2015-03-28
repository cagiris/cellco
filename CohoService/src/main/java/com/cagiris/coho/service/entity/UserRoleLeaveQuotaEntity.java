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

import com.cagiris.coho.service.api.IUserRoleLeaveQuota;
import com.cagiris.coho.service.api.LeaveType;
import com.cagiris.coho.service.api.UserRole;

/**
 *
 * @author: ssnk
 */

@Entity(name = "userRoleLeaveQuota")
public class UserRoleLeaveQuotaEntity extends BaseEntity implements IUserRoleLeaveQuota {

	private UserRole userRole;
	private Map<LeaveType, Integer> leaveTypeVsLeaveCount;

	@Id
	@Override
	@Column(name="user_role")
	public UserRole getUserRole() {
		return userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}

	@ElementCollection(targetClass= java.lang.String.class)
	@MapKeyEnumerated(EnumType.STRING)
    @MapKeyColumn(name="leave_type")
    @Column(name="leave_quota")
    @CollectionTable(name="user_leave_type_vs_quota", joinColumns=@JoinColumn(name="user_role"))
	@Override
	public Map<LeaveType, Integer> getLeaveTypeVsLeaveCount() {
		return leaveTypeVsLeaveCount;
	}

	public void setLeaveTypeVsLeaveCount(Map<LeaveType, Integer> leaveTypeVsLeaveCount) {
		this.leaveTypeVsLeaveCount = leaveTypeVsLeaveCount;
	}

}
