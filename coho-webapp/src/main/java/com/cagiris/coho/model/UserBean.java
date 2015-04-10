/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.model;

import java.io.Serializable;

import javax.validation.GroupSequence;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

import com.cagiris.coho.service.api.ITeamUser;

/**
 * @author Ashish Jindal
 *
 */
@GroupSequence({ValidationCheckForEmpty.class, ValidationCheckForLength.class, ValidationCheckForPattern.class,
        UserBean.class})
public class UserBean extends AbstractBean implements ICRUDBean {

    @NotBlank(message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Can only be alphanumeric", groups = ValidationCheckForPattern.class)
    @Size(min = 1, max = 50, message = "Length should be between {min} and {max}",
          groups = ValidationCheckForLength.class)
    private String userId;

    /**
     * Password corresponding to the userId.
     */
    @NotBlank(message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Can only be alphanumeric", groups = ValidationCheckForPattern.class)
    @Size(min = 6, max = 50, message = "Length should be between {min} and {max}",
          groups = ValidationCheckForLength.class)
    private String password;

    @NotBlank(message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
    @Pattern(regexp = "^[a-zA-Z0-9]*$", message = "Can only be alphanumeric", groups = ValidationCheckForPattern.class)
    @Size(min = 6, max = 50, message = "Length should be between {min} and {max}",
          groups = ValidationCheckForLength.class)
    private String reEnterdPassword;

    @NotBlank(message = "Field can't be left empty", groups = ValidationCheckForEmpty.class)
    private String userRole;

    public UserBean() {

    }

    public UserBean(ITeamUser user) {
        this.userId = user.getUserId();
        this.userRole = user.getUserRole();
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getReEnterdPassword() {
        return reEnterdPassword;
    }

    public void setReEnterdPassword(String reEnterdPassword) {
        this.reEnterdPassword = reEnterdPassword;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    @Override
    public Serializable getEntityId() {
        return userId;
    }
}
