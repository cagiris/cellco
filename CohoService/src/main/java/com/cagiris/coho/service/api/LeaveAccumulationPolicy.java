/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

/**
 *
 * @author: ssnk
 */

public enum LeaveAccumulationPolicy {

    MONTHLY(1), QUARTLY(3), BIANNUALLY(6), ANNUALLY(12);

    private int months;

    private LeaveAccumulationPolicy(int months) {
        this.months = months;
    }

    public int getMonths() {
        return months;
    }

}
