/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import org.apache.log4j.Logger;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ashish Jindal
 *
 */
@Controller
public class AuthenticationController extends AbstractController {

	private static Logger logger = Logger.getLogger(AuthenticationController.class);

	@RequestMapping(value = {"/login"}, method = RequestMethod.GET)
	public String showLoginPage () {
		
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			logger.debug("The user is already logged in");
		    return "redirect:/";
		}
		
		return "login";
	}
}
