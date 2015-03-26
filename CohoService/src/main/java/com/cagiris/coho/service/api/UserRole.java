/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

/**
 *
 * @author: ssnk
 */

public enum UserRole {
	ROOT(0), ADMIN(1), SUPERVISOR(2), AGENT(3);

	private int level;

	private UserRole(int level) {
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}
