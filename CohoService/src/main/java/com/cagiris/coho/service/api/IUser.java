/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

/**
 *
 * @author: ssnk
 */

public interface IUser {

	String getUserId();

	String getUserName();

	String getAuthToken();

	AuthenicationPolicy getAuthPolicy();
}
