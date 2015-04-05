/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.exception;


/**
 *
 * @author: ssnk
 */

public class LeaveManagementServiceException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public LeaveManagementServiceException() {
    }

    public LeaveManagementServiceException(String message) {
        super(message);
    }

    public LeaveManagementServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    public LeaveManagementServiceException(Throwable e) {
        super(e);
    }
}
