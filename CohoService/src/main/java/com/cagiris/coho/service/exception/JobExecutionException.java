/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.exception;

/**
 *
 * @author: ssnk
 */

public class JobExecutionException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public JobExecutionException() {
    }

    public JobExecutionException(String message) {
        super(message);
    }

    public JobExecutionException(String message, Throwable cause) {
        super(message, cause);
    }

    public JobExecutionException(Throwable e) {
        super(e);
    }

}
