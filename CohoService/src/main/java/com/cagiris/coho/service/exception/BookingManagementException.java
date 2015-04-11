/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.exception;

/**
 *
 * @author: Ashish Jindal
 */

public class BookingManagementException extends CohoException {

    public BookingManagementException() {
    }

    public BookingManagementException(String message) {
        super(message);
    }

    public BookingManagementException(String message, Throwable cause) {
        super(message, cause);
    }

    public BookingManagementException(Throwable e) {
        super(e);
    }
}
