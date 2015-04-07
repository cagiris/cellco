/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.model;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Ashish Jindal
 *
 */
public class QuickStatsBean extends AbstractBean {
	private Map<String, String> data = new HashMap<>();

	public Map<String, String> getData() {
		return data;
	}

	public void setData(Map<String, String> data) {
		this.data = data;
	}
}
