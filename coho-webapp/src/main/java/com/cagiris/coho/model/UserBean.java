/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.model;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Ashish Jindal
 *
 */
public class UserBean extends AbstractBean {
	/**
	 * Username chosen at the time of registration (Unique for each user).
	 */
	@NotBlank (message = "Field can't be left empty")
	@Pattern (regexp = "^[a-zA-Z0-9]*$", message = "Username can only be alphanumeric")
	@Size (min = 5, max = 50, message = "Username length should be between {min} and {max}")
	private String userName;
	
	/**
	 * Password corresponding to the username.
	 */
	@NotBlank (message = "Field can't be left empty")
	@Pattern (regexp = "^[a-zA-Z0-9]*$", message = "Password can only be alphanumeric")
	@Size (min = 10, max = 50, message = "Username length should be between {min} and {max}")
	private String password;

	@NotBlank (message = "Field can't be left empty")
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
}
