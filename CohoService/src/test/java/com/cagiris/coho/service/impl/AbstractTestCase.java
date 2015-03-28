/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.impl;

import org.junit.Before;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.cagiris.coho.service.db.api.IDatabaseManager;
import com.cagiris.coho.service.db.impl.DatabaseManager;

/**
 *
 * @author: ssnk
 */

public abstract class AbstractTestCase {
	protected IDatabaseManager databaseManager;
	protected ApplicationContext applicationContext;

	@Before
	public void setUpClass() {
		this.applicationContext = new GenericXmlApplicationContext("coho-service-spring.xml");
		this.databaseManager = applicationContext.getBean(DatabaseManager.class);
	}
}
