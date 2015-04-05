/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import java.io.Serializable;
import java.util.Map;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.MapKeyColumn;

import com.cagiris.coho.service.api.IUserRoleLeaveQuota;
import com.cagiris.coho.service.api.LeaveAccumulationPolicy;
import com.cagiris.coho.service.api.LeaveType;
import com.cagiris.coho.service.api.UserRole;
import com.cagiris.coho.service.entity.UserRoleLeaveQuotaEntity.UserRoleLeaveQuotaPK;

/**
 *
 * @author: ssnk
 */

@Entity
@IdClass(UserRoleLeaveQuotaPK.class)
public class UserRoleLeaveQuotaEntity extends BaseEntity implements IUserRoleLeaveQuota {

    private Long organizationId;

    private UserRole userRole;

    private Map<LeaveType, Integer> leaveTypeVsLeaveCount;

    private LeaveAccumulationPolicy leaveAccumulationPolicy;

    @Id
    @Override
    public Long getOrganizationId() {
        return organizationId;
    }

    @Id
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
    @CollectionTable(name = "user_leave_type_vs_quota", joinColumns = {@JoinColumn(name = "user_role"),
            @JoinColumn(name = "organization_id")})
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

    public static class UserRoleLeaveQuotaPK implements Serializable {

        private Long organizationId;

        private UserRole userRole;

        public UserRoleLeaveQuotaPK() {
        }

        public UserRoleLeaveQuotaPK(Long organizationId, UserRole userRole) {
            this.organizationId = organizationId;
            this.userRole = userRole;
        }

        public Long getOrganizationId() {
            return organizationId;
        }

        @Override
        public int hashCode() {
            final int prime = 31;
            int result = 1;
            result = prime * result + ((organizationId == null) ? 0 : organizationId.hashCode());
            result = prime * result + ((userRole == null) ? 0 : userRole.hashCode());
            return result;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null)
                return false;
            if (getClass() != obj.getClass())
                return false;
            UserRoleLeaveQuotaPK other = (UserRoleLeaveQuotaPK)obj;
            if (organizationId == null) {
                if (other.organizationId != null)
                    return false;
            } else if (!organizationId.equals(other.organizationId))
                return false;
            if (userRole != other.userRole)
                return false;
            return true;
        }

        public void setOrganizationId(Long organizationId) {
            this.organizationId = organizationId;
        }

        public UserRole getUserRole() {
            return userRole;
        }

        public void setUserRole(UserRole userRole) {
            this.userRole = userRole;
        }
    }
}
