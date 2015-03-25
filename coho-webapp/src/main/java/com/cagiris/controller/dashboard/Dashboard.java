package com.cagiris.controller.dashboard;

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
public class Dashboard {

	private static Logger logger = Logger.getLogger(Login.class);
	
	@RequestMapping(value = {"/index", "/dashboard"}, method = RequestMethod.GET)
	public String showDashboard () {
		return "dashboard";
	}
}
