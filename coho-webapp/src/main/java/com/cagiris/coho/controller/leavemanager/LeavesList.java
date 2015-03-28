/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller.leavemanager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ashish Jindal
 *
 */
@Controller
public class LeavesList {

	@RequestMapping(value = {"/leaves-list"}, method = RequestMethod.GET)
	public String showLeavesList () {
		return "leavemanager/leaves-list";
	}
}