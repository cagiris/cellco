/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

/**
 *
 * @author: Ashish Jindal
 */

public enum PassengerType {
    ADULT("Adult"), CHILD("Child"), INFANT("Infant");

    private String val;

    private PassengerType(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }
}
