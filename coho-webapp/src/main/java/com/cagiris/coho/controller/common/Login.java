/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller.common;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Handle login related requests.
 * 
 * @author Ashish Jindal
 *
 */
@Controller
public class Login {
	
	private static Logger logger = Logger.getLogger(Login.class);
	/**
	 * @param model
	 * @param error
	 * @return
	 */
	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String showLoginPage () {
		logger.debug("Entered Login Controller");
		return "login";
	}
	
}
