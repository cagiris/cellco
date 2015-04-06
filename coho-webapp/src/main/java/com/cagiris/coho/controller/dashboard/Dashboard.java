/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ashish Jindal
 *
 */
@Controller
public class Dashboard {

	@RequestMapping(value = {"/", "/dashboard"}, method = RequestMethod.GET)
	public String showDashboard () {
		return "dashboard/dashboard";
	}
}
