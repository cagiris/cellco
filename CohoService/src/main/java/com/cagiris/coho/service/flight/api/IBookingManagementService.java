/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.flight.api;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;

import com.cagiris.coho.service.exception.ResourceNotFoundException;
import com.cagiris.coho.service.flight.exception.BookingManagementException;

/**
 *
 * @author: ssnk
 */

public interface IBookingManagementService {

    IBookingDetails submitBookingDetails(String userId, BigInteger customerId, List<? extends IPassenger> passengers,
            BookingGDSType bookingGDSType, BigDecimal baseFare, BigDecimal taxesAndServiceFee,
            BigDecimal miscellaneousCharges) throws BookingManagementException;

    IBookingDetails getBookingDetails(String bookingId) throws ResourceNotFoundException, BookingManagementException;

    ICustomer addCustomer(String firstName, String lastName, String middleName, String addressLine1,
            String addressLine2, String city, String contactNumber, String country, String emailId, String pincode,
            String state) throws BookingManagementException;

    ICustomer getCustomer(BigInteger customerId) throws ResourceNotFoundException, BookingManagementException;
}
