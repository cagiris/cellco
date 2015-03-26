/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

/**
 * This will maintain the shift timings for a particular team.
 * 
 * @author: ssnk
 */

public interface ITeamShiftDetails {

	Long getTeamId();

	Long getShiftStartTime();

	Long getShiftEndTime();

}
