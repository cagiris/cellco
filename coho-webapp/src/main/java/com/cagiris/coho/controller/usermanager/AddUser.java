/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller.usermanager;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author Ashish Jindal
 *
 */
@Controller
public class AddUser {

	@RequestMapping(value = {"/add-user"}, method = RequestMethod.GET)
	public String showAddUserPage () {
		return "usermanager/add-user";
	}
}
