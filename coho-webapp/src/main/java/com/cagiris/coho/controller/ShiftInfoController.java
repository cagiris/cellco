/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author Ashish Jindal
 *
 */
@Controller
public class ShiftInfoController implements IController {

	public static final String IS_SHIFT_STARTED_URL_MAPPING = "/is-shift-started";
	public static final String START_USER_SHIFT_URL_MAPPING = "/start-shift";
	public static final String END_USER_SHIFT_URL_MAPPING = "/end-shift";
	
	@RequestMapping(IS_SHIFT_STARTED_URL_MAPPING)
	@ResponseBody
	public String isShiftStarted() {
		// TODO: Make a web service call
		return "Response";
	}
	
	@RequestMapping(START_USER_SHIFT_URL_MAPPING)
	@ResponseBody
	public String startShift() {
		// TODO: Make a web service call
		return "Response";
	}

	@RequestMapping(END_USER_SHIFT_URL_MAPPING)
	@ResponseBody
	public String endShift() {
		// TODO: Make a web service call
		return "Response";
	}
}
