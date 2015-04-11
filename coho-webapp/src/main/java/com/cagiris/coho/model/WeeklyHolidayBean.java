/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.model;

import com.cagiris.coho.service.api.IWeeklyHoliday;

/**
 *
 * @author: akhil
 */

public class WeeklyHolidayBean extends HolidayBean {

    private Long holidayId;

    private Integer weekDay;

    public WeeklyHolidayBean() {
    }

    public WeeklyHolidayBean(IWeeklyHoliday weeklyHoliday) {
        this.setDescription(weeklyHoliday.getDescription());
        this.setHolidayId(weeklyHoliday.getHolidayId());
        this.setUserRole(weeklyHoliday.getUserRole());
        this.setWeekDay(weeklyHoliday.getWeekDay());
    }

    public static WeeklyHolidayBean mapToBean(IWeeklyHoliday weeklyHoliday) {
        WeeklyHolidayBean weeklyHolidayBean = new WeeklyHolidayBean(weeklyHoliday);
        return weeklyHolidayBean;
    }

    @Override
    public Long getHolidayId() {
        return holidayId;
    }

    @Override
    public void setHolidayId(Long holidayId) {
        this.holidayId = holidayId;
    }

    public Integer getWeekDay() {
        return weekDay;
    }

    public void setWeekDay(Integer weekDay) {
        this.weekDay = weekDay;
    }

}
