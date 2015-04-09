/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.MappedSuperclass;

import com.cagiris.coho.service.api.IHoliday;
import com.cagiris.coho.service.api.UserRole;

/**
 *
 * @author: ssnk
 */

@MappedSuperclass
public abstract class HolidayEntity implements IHoliday {

    private Long organizationId;

    private String description;

    private UserRole userRole;

    @Override
    public Long getOrganizationId() {
        return this.organizationId;
    }

    public void setOrganizationId(Long teamId) {
        this.organizationId = teamId;
    }

    @Override
    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Enumerated(EnumType.STRING)
    @Override
    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

}
