/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

/**
 *
 * @author: Ashish Jindal
 */

public interface ICustomer {

    public abstract String getAddressLine1();

    public abstract String getAddressLine2();

    public abstract String getCity();

    public abstract String getContactNumber();

    public abstract String getCountry();

    public abstract Long getCustomerId();

    public abstract String getEmailId();

    public abstract String getFirstName();

    public abstract String getLastName();

    public abstract String getMiddleName();

    public abstract String getPincode();

    public abstract String getState();

}
