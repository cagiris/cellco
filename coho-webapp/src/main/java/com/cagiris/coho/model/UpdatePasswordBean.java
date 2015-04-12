/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.model;

import javax.validation.GroupSequence;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.cagiris.coho.model.validator.FieldMatch;

/**
 *
 * @author: Ashish Jindal
 */
@FieldMatch(first = "newPassword", second = "reEnteredPassword", errorMessage = "The password fields must match",
            groups = {ValidationCheckForPattern.class})
@GroupSequence({ValidationCheckForEmpty.class, ValidationCheckForLength.class, ValidationCheckForPattern.class,
        UpdatePasswordBean.class})
public class UpdatePasswordBean extends AbstractBean {

    private String userId;

    @NotBlank(message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Can only be alphanumeric", groups = ValidationCheckForPattern.class)
    @Size(min = 6, max = 50, message = "Length should be between {min} and {max}",
          groups = ValidationCheckForLength.class)
    private String newPassword;

    @NotBlank(message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Can only be alphanumeric", groups = ValidationCheckForPattern.class)
    @Size(min = 6, max = 50, message = "Length should be between {min} and {max}",
          groups = ValidationCheckForLength.class)
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
