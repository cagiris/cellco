package com.cagiris.controller.reports;

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
public class UserStatistics {

	private static Logger logger = Logger.getLogger(Login.class);
	
	@RequestMapping(value = {"/user-statistics"}, method = RequestMethod.GET)
	public String showUserStatistics () {
		return "user-statistics";
	}
}
