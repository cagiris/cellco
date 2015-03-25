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
public class OverAllStatistics {

	private static Logger logger = Logger.getLogger(Login.class);
	
	@RequestMapping(value = {"/overall-statistics"}, method = RequestMethod.GET)
	public String showOverallStatistics () {
		return "overall-statistics";
	}
}
