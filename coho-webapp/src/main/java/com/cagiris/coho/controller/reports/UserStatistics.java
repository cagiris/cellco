/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller.reports;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ashish Jindal
 *
 */
@Controller
public class UserStatistics {

	@RequestMapping(value = {"/user-statistics"}, method = RequestMethod.GET)
	public String showUserStatistics () {
		return "reports/user-statistics";
	}
}
