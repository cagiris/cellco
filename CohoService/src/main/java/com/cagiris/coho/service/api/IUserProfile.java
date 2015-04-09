/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

import java.util.Date;

/**
 *
 * @author: ssnk
 */

public interface IUserProfile {

	public abstract String getUserId();
	
	public abstract String getFirstName();

	public abstract String getLastName();

	public abstract Date getDateOfBirth();

	public abstract String getGender();

	public abstract String getMobileNumber();

	public abstract String getEmailId();

	public abstract String getAddressLine1();

	public abstract String getAddressLine2();

	public abstract String getCity();

	public abstract String getPincode();

	public abstract String getState();

	public abstract String getCountry();

	public abstract Date getJoinedOn();

	public abstract Date getLeftOn();

	public abstract String getDesignation();
}
