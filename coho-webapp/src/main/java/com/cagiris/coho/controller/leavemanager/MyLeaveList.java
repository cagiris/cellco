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
public class MyLeaveList {

	@RequestMapping(value = {"/my-leave-list"}, method = RequestMethod.GET)
	public String showMyLeaveList () {
		return "my-leave-list";
	}
}
