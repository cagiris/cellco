/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.exception;


/**
 *
 * @author: ssnk
 */

public class HierarchyServiceException extends CohoException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public HierarchyServiceException() {
	}

	public HierarchyServiceException(String message) {
		super(message);
	}

	public HierarchyServiceException(String message, Throwable cause) {
		super(message, cause);
	}

	public HierarchyServiceException(Throwable e) {
		super(e);
	}
}
