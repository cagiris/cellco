/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.model;

/**
 *
 * @author: Ashish Jindal
 */

public class UpdatePasswordBean extends AbstractBean {

    private String userId;

    private String newPassword;

    private String reEnteredPassword;

    public String getNewPassword() {
        return newPassword;
    }

    public String getReEnteredPassword() {
        return reEnteredPassword;
    }

    public String getUserId() {
        return userId;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public void setReEnteredPassword(String reEnteredPassword) {
        this.reEnteredPassword = reEnteredPassword;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}
