/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cagiris.coho.service.api.IWeeklyHoliday;

/**
 *
 * @author: ssnk
 */
//read only as no update api exit as of now
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
public class WeeklyHolidayEntity extends HolidayEntity implements IWeeklyHoliday {

    private Long holidayId;

    private Integer weekDay;

    @Override
    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;

    }

    @Override
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getHolidayId() {
        return holidayId;
    }

    public void setHolidayId(Long holidayId) {
        this.holidayId = holidayId;
    }
}
