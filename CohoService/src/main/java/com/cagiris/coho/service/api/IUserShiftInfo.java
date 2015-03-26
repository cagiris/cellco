/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

/**
 *
 * @author: ssnk
 */

public interface IUserShiftInfo {

	String getShiftId();

	Long getShiftStartTime();

	Long getShiftEndTime();

	Long getShiftDuration();

	String getUserId();
}
