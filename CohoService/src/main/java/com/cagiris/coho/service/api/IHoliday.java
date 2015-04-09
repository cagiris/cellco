/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

/**
 *
 * @author: ssnk
 */

public interface IHoliday {

    Long getHolidayId();

    Long getOrganizationId();

    UserRole getUserRole();

    String getDescription();
}
