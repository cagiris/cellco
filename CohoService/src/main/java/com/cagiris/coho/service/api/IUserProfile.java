/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

import java.util.Date;

/**
 *
 * @author: ssnk
 */

public interface IUserProfile {

    String getUserId();

    String getAddress();

    String getCity();

    String getPhone();

    /**
     * @return Date of birth of the user.
     */
    Date getDateOfBirth();

    /**
     * @return Contact email of the user.
     */
    String getEmail();

    String getUserName();

    /**
     * @return Postal code.
     */
    String getPostalCode();

    /**
     * @return The date when user last worked for the organization.
     */
    Date getWorkEndDate();

    /**
     * @return The date when user started working for the organization.
     */
    Date getWorkStartDate();

}
