/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import java.util.Date;

import com.cagiris.coho.service.api.PassengerType;

/**
 *
 * @author: Ashish Jindal
 */

public interface IPassenger {

    public abstract Long getPassengerId();

    public abstract String getFirstName();

    public abstract String getMiddleName();

    public abstract String getLastName();

    public abstract PassengerType getType();

    public abstract Date getDateOfBirth();

}
