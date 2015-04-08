/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.exception;

/**
 *
 * @author: ssnk
 */

public class AttendenceReportingServiceException extends CohoException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public AttendenceReportingServiceException() {
	}

	public AttendenceReportingServiceException(String message) {
		super(message);
	}

	public AttendenceReportingServiceException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public AttendenceReportingServiceException(Throwable cause)
	{
		super(cause);
	}
}