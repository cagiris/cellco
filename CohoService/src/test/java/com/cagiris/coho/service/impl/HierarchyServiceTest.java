/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.impl;

import junit.framework.Assert;

import org.junit.Test;

import com.cagiris.coho.service.api.AuthenicationPolicy;
import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.ITeam;
import com.cagiris.coho.service.api.UserRole;
import com.cagiris.coho.service.db.api.IDatabaseManager;
import com.cagiris.coho.service.db.impl.DatabaseManager;
import com.cagiris.coho.service.exception.HierarchyServiceException;

/**
 *
 * @author: ssnk
 */

public class HierarchyServiceTest {

	@Test
	public void testAddTeam() throws HierarchyServiceException {
		IDatabaseManager databaseManager = new DatabaseManager();
		IHierarchyService hierarchyService = new HierarchyService(databaseManager);
		hierarchyService.addOrganization("test", "test1");
		ITeam addTeam = hierarchyService.addTeam(1l, null, "coho", "coho");
		ITeam addTeam2 = hierarchyService.addTeam(1l, addTeam.getTeamId(), "yo", "yoyo");
		hierarchyService.addUserToTeam(addTeam.getTeamId(), "test1", "test", "test", UserRole.AGENT,
				AuthenicationPolicy.PASSWORD_BASED);
		Assert.assertEquals(addTeam.getTeamId(), addTeam2.getParentTeamId());
	}
}
