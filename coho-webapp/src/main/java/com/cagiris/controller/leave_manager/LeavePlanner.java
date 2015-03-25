package com.cagiris.controller.leave_manager;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.cagiris.controller.common.Login;

/**
 * @author Ashish Jindal
 *
 */
@Controller
public class LeavePlanner {

	private static Logger logger = Logger.getLogger(Login.class);
	
	@RequestMapping(value = {"/leave-planner"}, method = RequestMethod.GET)
	public String showLeavePlanner () {
		return "leave-planner";
	}
}
