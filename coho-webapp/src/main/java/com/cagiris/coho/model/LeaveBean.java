/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.model;

import java.io.Serializable;

/**
 * @author Ashish Jindal
 *
 */
public class LeaveBean extends AbstractBean implements ICRUDBean {

	private String leaveid;
	
	@Override
	public Serializable getEntityId() {
		return leaveid;
	}

}
