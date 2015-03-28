/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.impl;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.cagiris.coho.service.api.AuthenicationPolicy;
import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.IOrganization;
import com.cagiris.coho.service.api.ITeam;
import com.cagiris.coho.service.api.UserRole;
import com.cagiris.coho.service.exception.HierarchyServiceException;

/**
 *
 * @author: ssnk
 */

public class HierarchyServiceTest extends AbstractTestCase {

	private IHierarchyService hierarchyService;

	@Before
	public void setUp() {
		this.hierarchyService = applicationContext.getBean(HierarchyService.class);
	}

	@Test
	public void testAddTeam() throws HierarchyServiceException {
		IOrganization organization = hierarchyService.addOrganization("test2", "test1");
		ITeam addTeam = hierarchyService.addTeam(organization.getOrganizationId(), null, "coho", "coho");
		ITeam addTeam2 = hierarchyService.addTeam(1l, addTeam.getTeamId(), "yo", "yoyo");
		hierarchyService.addUserToTeam(2l, "agent2", "Agent", "agent", UserRole.AGENT,
				AuthenicationPolicy.PASSWORD_BASED);
		hierarchyService.addUserToTeam(2l, "agent3", "Agent1", "agent1", UserRole.AGENT,
				AuthenicationPolicy.PASSWORD_BASED);
		Assert.assertEquals(addTeam.getTeamId(), addTeam2.getParentTeamId());

		hierarchyService.removeUserFromTeam(2l, "agent2");
		hierarchyService.deleteTeam(2l);
	}
}
