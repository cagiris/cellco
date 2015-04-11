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

    public abstract Long getCustomerId();

    public abstract String getFirstName();

    public abstract String getMiddleName();

    public abstract String getLastName();

    public abstract String getEmailId();

    public abstract String getContactNumber();

    public abstract String getBillingAddress();

}
