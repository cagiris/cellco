/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.model;

import javax.validation.GroupSequence;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Ashish Jindal
 *
 */
@GroupSequence({ ValidationCheckForEmpty.class, ValidationCheckForLength.class, ValidationCheckForPattern.class, UserBean.class})
public class UserBean extends AbstractBean {
	
	
	@NotBlank (message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
	@Pattern (regexp = "^[a-zA-Z0-9]*$", message = "Can only be alphanumeric", groups = ValidationCheckForPattern.class)
	@Size (min = 5, max = 50, message = "Length should be between {min} and {max}", groups = ValidationCheckForLength.class)
	
	private String userId;
	
	/**
	 * Username chosen at the time of registration (Unique for each user).
	 */
	@NotBlank (message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
	@Pattern (regexp = "^[a-zA-Z0-9]*$", message = "Can only be alphanumeric", groups = ValidationCheckForPattern.class)
	@Size (min = 5, max = 50, message = "Length should be between {min} and {max}", groups = ValidationCheckForLength.class)
	private String userName;
	
	/**
	 * Password corresponding to the username.
	 */
	@NotBlank (message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
	@Pattern (regexp = "^[a-zA-Z0-9]*$", message = "Can only be alphanumeric", groups = ValidationCheckForPattern.class)
	@Size (min = 10, max = 50, message = "Length should be between {min} and {max}", groups = ValidationCheckForLength.class)
	private String password;

	@NotBlank (message = "Field can't be left empty", groups = ValidationCheckForEmpty.class)
	private String userRole;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUserRole() {
		return userRole;
	}

	public void setUserRole(String userRole) {
		this.userRole = userRole;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}
}
