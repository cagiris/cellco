/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.model;

import java.io.Serializable;

import org.hibernate.validator.constraints.NotBlank;

import com.cagiris.coho.service.api.IHoliday;
import com.cagiris.coho.service.api.UserRole;

/**
 *
 * @author: akhil
 */

public class HolidayBean extends AbstractBean implements ICRUDBean {

    @NotBlank
    private String description;

    private UserRole userRole;

    private Long holidayId;

    public HolidayBean() {
    }

    public HolidayBean(IHoliday holiday) {
        this.setDescription(holiday.getDescription());
        this.setUserRole(holiday.getUserRole());
        this.setHolidayId(holiday.getHolidayId());
    }

    @Override
    public Serializable getEntityId() {
        return null;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    public Long getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(Long holidayId) {
        this.holidayId = holidayId;
    }

}
