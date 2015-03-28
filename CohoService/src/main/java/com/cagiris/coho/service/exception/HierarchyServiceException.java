/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.exception;

import com.cagiris.coho.service.db.api.DatabaseManagerException;

/**
 *
 * @author: ssnk
 */

public class HierarchyServiceException extends Exception {

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

	public HierarchyServiceException(DatabaseManagerException e) {
		super(e);
	}
}
