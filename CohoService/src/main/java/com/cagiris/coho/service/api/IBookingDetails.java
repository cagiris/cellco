/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

import java.util.Set;

/**
 *
 * @author: Ashish Jindal
 */

public interface IBookingDetails {

    public abstract Long getBookingId();

    public abstract ICustomer getCustomer();

    public abstract Set<IPassenger> getPassengers();

    public abstract double getBaseFare();

    public abstract double getTaxesAndServiceFee();

    public abstract double getMiscellaneousCharges();

    public abstract IUser getUser();
}
