/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.model;

import java.io.Serializable;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

/**
 * @author Ashish Jindal
 *
 */
public class UserProfileBean extends AbstractBean implements ICRUDBean {
	
	@NotBlank (message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
	@Size (min = 10, max = 50, message = "Length should be between {min} and {max}", groups = ValidationCheckForLength.class)
	private String address;
	
	@NotBlank (message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
	@Pattern (regexp = "^[a-zA-Z]*$", message = "Can only contain alphabets", groups = ValidationCheckForPattern.class)
	@Size (min = 1, max = 50, message = "Length should be between {min} and {max}", groups = ValidationCheckForLength.class)
	private String city;

	@NotBlank (message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
	@Pattern (regexp = "^[0-9]*$", message = "Can only contain numbers", groups = ValidationCheckForPattern.class)
	@Size (min = 10, max = 10, message = "Please enter a correct {min} digit mobile number", groups = ValidationCheckForLength.class)
	private String phone;

	@NotBlank (message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
	@Pattern (regexp = "^\\d\\d-\\d\\d-\\d\\d\\d\\d$", message = "Please enter a valid date (dd-MM-YYYY)", groups = ValidationCheckForPattern.class)
	@Size (min = 10, max = 10, message = "Invalid date (dd-MM-YYYY)", groups = ValidationCheckForLength.class)
	private String dateOfBirth;

	@NotBlank (message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
	@Email (message = "Invalid email address", groups = ValidationCheckForPattern.class)
	@Size (min = 5, max = 50, message = "Invalid email address", groups = ValidationCheckForLength.class)
	private String email;

	@NotBlank (message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
	@Pattern (regexp = "^[0-9]*$", message = "Invalid postal code", groups = ValidationCheckForPattern.class)
	@Size (min = 6, max = 6, message = "Please enter a correst {min} digit postal code", groups = ValidationCheckForLength.class)
	private String postalCode;

	@NotBlank (message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
	@Pattern (regexp = "^\\d\\d-\\d\\d-\\d\\d\\d\\d$", message = "Please enter a valid date (dd-MM-YYYY)", groups = ValidationCheckForPattern.class)
	@Size (min = 10, max = 10, message = "Invalid date (dd-MM-YYYY)", groups = ValidationCheckForLength.class)
	private String startWorkDate;

	@Pattern (regexp = "^\\d\\d-\\d\\d-\\d\\d\\d\\d$", message = "Please enter a valid date (dd-MM-YYYY)", groups = ValidationCheckForPattern.class)
	@Size (min = 10, max = 10, message = "Invalid date (dd-MM-YYYY)", groups = ValidationCheckForLength.class)
	private String endWorkDate;
	

	@Override
    public Serializable getEntityId() {
		return "";
	}
}
