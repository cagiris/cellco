/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

/**
 *
 * @author: ssnk
 */

public enum LeaveType {
    CASUAL_LEAVE("Casual Leaves"), PRIVILEGE_LEAVE("Privilege Leaves"), SICK_LEAVE("Sick Leaves"), PAID_LEAVE(
            "Paid Leaves");

    private String val;

    private LeaveType(String val) {
        this.setVal(val);
    }

    public String getVal() {
        return val;
    }

    public void setVal(String val) {
        this.val = val;
    }

    @Override
    public String toString() {
        return val;
    }
}
