/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cagiris.coho.service.api.IAnnualHoliday;

/**
 *
 * @author: ssnk
 */
//read only as no update api exit as of now
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
public class AnnualHolidayEntity extends HolidayEntity implements IAnnualHoliday {

    private Long holidayId;

    private Date holidayDate;

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(Long holidayId) {
        this.holidayId = holidayId;
    }

    @Override
    public Date getHolidayDate() {
        return holidayDate;
    }

    public void setHolidayDate(Date holidayDate) {
        this.holidayDate = holidayDate;
    }
}
