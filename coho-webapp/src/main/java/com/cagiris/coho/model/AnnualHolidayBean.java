/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.model;

import java.util.Date;

import com.cagiris.coho.service.api.IAnnualHoliday;

/**
 *
 * @author: akhil
 */

public class AnnualHolidayBean extends HolidayBean {

    private Long holidayId;

    private Date holidayDate;

    public AnnualHolidayBean() {

    }

    public AnnualHolidayBean(IAnnualHoliday annualHoliday) {
        this.setHolidayId(annualHoliday.getHolidayId());
        this.setHolidayDate(annualHoliday.getHolidayDate());
        this.setDescription(annualHoliday.getDescription());
        this.setUserRole(annualHoliday.getUserRole());
    }

    @Override
    public Long getHolidayId() {
        return holidayId;
    }

    @Override
    public void setHolidayId(Long holidayId) {
        this.holidayId = holidayId;
    }

    public Date getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }

}
