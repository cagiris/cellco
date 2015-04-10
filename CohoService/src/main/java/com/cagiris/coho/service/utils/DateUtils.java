/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.utils;

import java.text.ParseException;
import java.util.Date;

import org.joda.time.DateTime;

/**
 *
 * @author: ssnk
 */

public class DateUtils {

    private static String[] dateFormatters = {"yyyy-MM-dd hh:mm:ss.SSS"};

    public static DateTime getDateTimeWithDateOnly(Date date) {
        return getDateTimeWithDateOnly(new DateTime(date));
    }

    public static DateTime getDateTimeWithDateOnly(DateTime dateTime) {
        return dateTime.withMillisOfSecond(0).withSecondOfMinute(0).withMinuteOfHour(0).withHourOfDay(0);
    }

    public static DateTime getDateTimeWithTimeOnly(Date date) {
        return getDateTimeWithDateOnly(new DateTime(date));
    }

    public static DateTime getDateTimeWithTimeOnly(DateTime dateTime) {
        return dateTime.withYear(2012).withDayOfMonth(1).withMonthOfYear(11);
    }

    public static Date parseDate(String dateString) throws ParseException {
        return org.apache.commons.lang3.time.DateUtils.parseDate(dateString, dateFormatters);
    }
}
