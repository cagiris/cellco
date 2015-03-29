/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.impl;

import java.util.HashMap;

import org.junit.Before;
import org.junit.Test;

import com.cagiris.coho.service.api.ILeaveManagementService;
import com.cagiris.coho.service.api.LeaveType;
import com.cagiris.coho.service.exception.LeaveManagementServiceException;

/**
 *
 * @author: ssnk
 */

public class LeaveManagementServiceTest extends AbstractTestCase {

    private ILeaveManagementService leaveManagementService;

    @Before
    public void setUp() {
        this.leaveManagementService = applicationContext.getBean(LeaveManagementService.class);
    }

    @Test
    public void applyLeaveTest() throws LeaveManagementServiceException {
        HashMap<LeaveType, Integer> leaveTypeVsLeaveCount = new HashMap<LeaveType, Integer>();
        leaveTypeVsLeaveCount.put(LeaveType.CASUAL_LEAVE, 2);
        leaveManagementService.applyForLeave("agent2", "agent3", leaveTypeVsLeaveCount);
    }
}
