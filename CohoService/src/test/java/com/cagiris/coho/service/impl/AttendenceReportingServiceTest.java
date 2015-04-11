/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.cagiris.coho.service.api.IAttendenceReportingService;
import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.ITeamShiftDetails;
import com.cagiris.coho.service.api.IUserShiftInfo;
import com.cagiris.coho.service.db.api.DatabaseManagerException;
import com.cagiris.coho.service.exception.AttendenceReportingServiceException;
import com.cagiris.coho.service.exception.HierarchyServiceException;

/**
 *
 * @author: abhishek
 */

public class AttendenceReportingServiceTest extends AbstractTestCase {

    private IAttendenceReportingService attendenceReportingService;
    private IHierarchyService hierarchyService;

    @Before
    public void setUp() {
        this.attendenceReportingService = applicationContext.getBean(AttendenceReportingService.class);
        this.hierarchyService = applicationContext.getBean(HierarchyService.class);

    }

    /**
     * @throws AttendenceReportingServiceException
     * @throws HierarchyServiceException
     * @throws DatabaseManagerException
     */

    @Ignore
    @Test
    public void testStartUserShift() throws AttendenceReportingServiceException, HierarchyServiceException,
            DatabaseManagerException {
        //IOrganization addOrganization = hierarchyService.addOrganization("coho", "coho");
        //ITeam team = hierarchyService.addTeam(addOrganization.getOrganizationId(), null, "team2", "test team");
        //ITeamUser user = hierarchyService.addUserToTeam(team.getTeamId(), "testUser1", "testUser1", "testUser1",
        //        UserRole.AGENT, AuthenicationPolicy.PASSWORD_BASED);
        //hierarchyService.toString();
        //attendenceReportingService.toString();

        attendenceReportingService.startUserShift(2l, "testUser");
        attendenceReportingService.startUserShift(2l, "testUser");

        attendenceReportingService.startUserShift(3l, "testUser1");
        attendenceReportingService.startUserShift(3l, "testUser1");

        //        Assert.assertNotNull(startUserShift.getUserId());
        //        Assert.assertNotNull(startUserShift.getShiftStartTime());
        //        Assert.assertNotNull(startUserShift.getShiftEndTime());
        //        Assert.assertNotNull(startUserShift.getShiftId());
        //        Assert.assertNotNull(startUserShift.getShiftDuration());
        //        Assert.assertNotNull(startUserShift.getTeamId());

        //        QUserShiftEntity qUserShiftEntity = QUserShiftEntity.userShiftEntity;
        //        Date endTime = new Date();
        //        Date startTime = new Date(endTime.getTime() - 100000000);
        //        HibernateQuery hibernateQuery = new HibernateQuery().from(qUserShiftEntity).where(
        //                qUserShiftEntity.shiftStartTime.gt(startTime).and(qUserShiftEntity.shiftStartTime.lt(endTime)));
        //        List<UserShiftEntity> executeQueryAndGetResults = databaseManager.executeQueryAndGetResults(hibernateQuery,
        //                qUserShiftEntity);
        //        System.out.println(executeQueryAndGetResults.size());
    }

    /*
     * @Test public void testGetShiftId() { IAttendenceReportingService
     * attendenceReportingService = new AttendenceReportingService(
     * databaseManager); }
     */

    @Ignore
    @Test
    public void testEndUserShift() throws AttendenceReportingServiceException {

        //IAttendenceReportingService attendenceReportingService = new AttendenceReportingService(databaseManager);

        // SUCCESS 
        String shiftId = "82c8c9c57e5e46b5-shiftId-shiftId-14c677c83af";
        //String shiftId = "shift";
        attendenceReportingService.endUserShift(shiftId, "logout");
        //Assert.assertSame(startUserShift1.getShiftStartTime(), endUserShift1.getShiftStartTime());
        //Assert.assertNotSame(startUserShift1.getShiftEndTime(), endUserShift1.getShiftEndTime());

        // Invalid Shift Id 
        //IUserShiftInfo endUserShift2 = null;
        //endUserShift2 = attendenceReportingService.endUserShift("ShiftId2");
        //Assert.assertNull(endUserShift2); //
        //Assert.assertNotSame(startUserShift1.getShiftEndTime(), endUserShift2.getShiftEndTime());

    }

    @Ignore
    @Test
    public void testCreateTeamShiftDetails() throws AttendenceReportingServiceException {

        IAttendenceReportingService attendenceReportingService = new AttendenceReportingService(databaseManager);

        // SUCCESS
        Date shiftStartTime = new Date();
        Date shiftEndTime = new Date();
        int hours = shiftStartTime.getHours();
        shiftEndTime.setHours(hours + 1);
        long teamId = 21L;
        ITeamShiftDetails teamShiftDetails = attendenceReportingService.createTeamShiftDetails(teamId, shiftStartTime,
                shiftEndTime, 60l, 60l, true);
        // Assert.assertEqual(teamShiftDetails.getUserId(),id);
        // Assert.assertEqual(teamShiftDetails.getUserId(), id);
        // Assert.assertEqual(teamShiftDetails.getUserId(), id);
    }

    @Ignore
    @Test
    public void testUpdateTeamShiftDetails() throws AttendenceReportingServiceException {

        IAttendenceReportingService attendenceReportingService = new AttendenceReportingService(databaseManager);

        // SUCCESS Date 
        Date shiftStartTime = new Date();
        Date shiftEndTime = new Date();
        int hours = shiftStartTime.getHours();
        shiftEndTime.setHours(6);

        //ITeamShiftDetails teamShiftDetails1 =
        attendenceReportingService.createTeamShiftDetails(2l, shiftStartTime, shiftEndTime, 60l, 60l, true);

        // ITeamShiftDetails teamShiftDetails2 =
        attendenceReportingService.updateTeamShiftDetails(2l, shiftStartTime, shiftEndTime, 60l, 60l, true);
    }

    @Ignore
    @Test
    public void testGetCurrentUserShiftInfo() throws AttendenceReportingServiceException {

        //IOrganization addOrganization = hierarchyService.addOrganization("coho", "coho");
        //ITeam team = hierarchyService.addTeam(addOrganization.getOrganizationId(), null, "team1", "test team");
        //ITeamUser user = hierarchyService.addUserToTeam(team.getTeamId(), "testUser", "testUser", "testUser",
        //UserRole.AGENT, AuthenicationPolicy.PASSWORD_BASED);

        attendenceReportingService.startUserShift(2l, "testUser");
        attendenceReportingService.startUserShift(2l, "testUser");

        attendenceReportingService.startUserShift(3l, "testUser1");
        attendenceReportingService.startUserShift(3l, "testUser1");

        // SUCCESS
        List<? extends IUserShiftInfo> startUserShiftList = attendenceReportingService
                .getCurrentUserShiftInfo("testUser");

        for (IUserShiftInfo startUserShiftVar : startUserShiftList) {
            System.out.println("User Shift List:");
            System.out.println(startUserShiftVar.getShiftId());
            System.out.println(startUserShiftVar.getUserId());
            System.out.println(startUserShiftVar.getClass());
            System.out.println(startUserShiftVar.getShiftDuration());
            System.out.println(startUserShiftVar.getShiftEndTime());
            System.out.println(startUserShiftVar.getShiftStartTime());
            System.out.println(startUserShiftVar.getTeamId());
        }

    }

    @Ignore
    @Test
    public void testQueryUserShiftInfo() throws AttendenceReportingServiceException {

        //        IOrganization addOrganization = hierarchyService.addOrganization("coho", "coho");
        //        ITeam team = hierarchyService.addTeam(addOrganization.getOrganizationId(), null, "team2", "test team");
        //        ITeamUser user1 = hierarchyService.addUserToTeam(team.getTeamId(), "testUser1", "testUser1", "testUser1",
        //                UserRole.AGENT, AuthenicationPolicy.PASSWORD_BASED);
        //        ITeamUser user2 = hierarchyService.addUserToTeam(team.getTeamId(), "testUser2", "testUser2", "testUser2",
        //                UserRole.AGENT, AuthenicationPolicy.PASSWORD_BASED);
        //        ITeamUser user3 = hierarchyService.addUserToTeam(team.getTeamId(), "testUser3", "testUser3", "testUser3",
        //                UserRole.AGENT, AuthenicationPolicy.PASSWORD_BASED);
        //
        //        attendenceReportingService.startUserShift(1l, "testUser1");
        //        attendenceReportingService.startUserShift(1l, "testUser1");
        //
        //        attendenceReportingService.startUserShift(2l, "testUser2");
        //        attendenceReportingService.startUserShift(2l, "testUser2");
        //
        //        attendenceReportingService.startUserShift(2l, "testUser2");
        //        attendenceReportingService.startUserShift(2l, "testUser2");

        Date startTime = new Date();
        Date endTime = new Date();
        int hours = startTime.getHours();
        startTime.setHours(hours - 1);
        endTime.setHours(hours + 1);

        List<String> userIds = new ArrayList<String>();
        userIds.add("testUser");
        userIds.add("testUser1");

        List<? extends IUserShiftInfo> userShiftInfoQuery = attendenceReportingService.queryUserShiftInfo(userIds,
                startTime, endTime);

        for (IUserShiftInfo userShiftInfoQueryVar : userShiftInfoQuery) {
            System.out.println("User Shift List:");
            System.out.println(userShiftInfoQueryVar.getShiftId());
            System.out.println(userShiftInfoQueryVar.getUserId());
            System.out.println(userShiftInfoQueryVar.getClass());
            System.out.println(userShiftInfoQueryVar.getShiftDuration());
            System.out.println(userShiftInfoQueryVar.getShiftEndTime());
            System.out.println(userShiftInfoQueryVar.getShiftStartTime());
            System.out.println(userShiftInfoQueryVar.getTeamId());
        }

    }

    @Ignore
    @Test
    public void testUpdateUserShiftInfo() throws AttendenceReportingServiceException {

        Date shiftStartTime = new Date();
        Date shiftEndTime = new Date();
        int hours = shiftStartTime.getHours();
        shiftEndTime.setHours(hours);
        String shiftId = "shift1";

        IUserShiftInfo userShiftInfo = attendenceReportingService.updateUserShiftInfo(shiftId, shiftStartTime,
                shiftEndTime, "change");

        //Assert.assertSame(userShiftInfo.getShiftId(), "ShiftId5");
        //Assert.assertSame(userShiftInfo.getShiftStartTime(), "2015-05-28 19:30:12.304");
        //Assert.assertSame(userShiftInfo.getShiftEndTime(), "2015-05-29 19:30:12.304");

    }

}
