/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.exception;

/**
 *
 * @author: ssnk
 */

public class AttendanceReportingServiceException extends Exception {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public AttendanceReportingServiceException() {
	}

	public AttendanceReportingServiceException(String message) {
		super(message);
	}

	public AttendanceReportingServiceException(String message, Throwable cause) {
		super(message, cause);
	}
}