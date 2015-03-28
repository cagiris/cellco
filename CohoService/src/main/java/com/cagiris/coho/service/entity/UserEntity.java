/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;

import com.cagiris.coho.service.api.AuthenicationPolicy;
import com.cagiris.coho.service.api.IUser;
import com.cagiris.coho.service.api.UserRole;

/**
 *
 * @author: ssnk
 */
@Entity(name = "users")
public class UserEntity extends BaseEntity implements IUser {
	private String userId;

	private String userName;

	private String authToken;

	private AuthenicationPolicy authPolicy;

	private UserRole userRole;

	@Enumerated(EnumType.STRING)
	@Override
	public AuthenicationPolicy getAuthPolicy() {
		return this.authPolicy;
	}

	@Override
	public String getAuthToken() {
		return this.authToken;
	}

	@Override
	@Id
	public String getUserId() {
		return this.userId;
	}

	@Override
	public String getUserName() {
		return this.userName;
	}

	public void setAuthPolicy(AuthenicationPolicy authPolicy) {
		this.authPolicy = authPolicy;
	}

	public void setAuthToken(String authToken) {
		this.authToken = authToken;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	@Enumerated(EnumType.STRING)
	@Override
	public UserRole getUserRole() {
		return this.userRole;
	}

	public void setUserRole(UserRole userRole) {
		this.userRole = userRole;
	}
}
