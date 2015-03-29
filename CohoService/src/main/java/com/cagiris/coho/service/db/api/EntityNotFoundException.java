/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.db.api;

/**
 *
 * @author: ssnk
 */

public class EntityNotFoundException extends Exception {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException() {
    }

    public EntityNotFoundException(String message) {
        super(message);
    }

    public EntityNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

}
