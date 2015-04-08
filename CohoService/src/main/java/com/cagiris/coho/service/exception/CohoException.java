/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.exception;

/**
 * All exceptions thrown by the application should be derived from
 * CohoException, these exceptions are then handled with custom error
 * messages, which can then be shown to user on UI.
 * 
 * @author Ashish Jindal
 *
 */
public class CohoException extends Exception {

	private static final long serialVersionUID = 8099745981057269977L;

	public CohoException() {
	}

	public CohoException(String message) {
		super(message);
	}

	public CohoException(String message, Throwable cause) {
		super(message, cause);
	}

	public CohoException(Throwable e) {
		super(e);
	}
}
