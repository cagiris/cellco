/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.impl;

import org.junit.Before;
import org.junit.Test;

import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.ITeam;
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
        ITeam defaultTeam = hierarchyService.getDefaultTeam(hierarchyService.getDefaultOrganization()
                .getOrganizationId());
        hierarchyService.removeUserFromTeam(defaultTeam.getTeamId(), "agent");
    }
}
