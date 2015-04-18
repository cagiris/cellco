/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.flight.api;

import java.math.BigDecimal;
import java.util.List;

/**
 *
 * @author: Ashish Jindal
 */

public interface IBookingDetails {

    String getBookingId();

    String getPnr();

    public abstract ICustomer getCustomer();

    public abstract List<? extends IPassenger> getPassengers();

    public abstract BookingGDSType getBookingGDSType();

    public abstract BigDecimal getBaseFare();

    public abstract BigDecimal getTaxesAndServiceFee();

    public abstract BigDecimal getMiscellaneousCharges();

    public abstract String getUserId();
}
