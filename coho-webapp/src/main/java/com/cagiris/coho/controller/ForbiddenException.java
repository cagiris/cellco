/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author Ashish Jindal
 *
 */
@ResponseStatus(HttpStatus.FORBIDDEN)
public class ForbiddenException extends RuntimeException {

	public ForbiddenException(String errorForbidden) {
		super(errorForbidden);
	}

	public ForbiddenException() {
		super(Constants.ERROR_FORBIDDEN);
	}
	
	private static final long serialVersionUID = -2160039455919382463L;

}
