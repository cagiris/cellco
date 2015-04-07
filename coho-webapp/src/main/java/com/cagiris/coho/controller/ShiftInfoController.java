/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cagiris.coho.service.api.IAttendenceReportingService;
import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.IOrganization;
import com.cagiris.coho.service.api.ITeam;
import com.cagiris.coho.service.api.IUserShiftInfo;
import com.cagiris.coho.service.exception.AttendenceReportingServiceException;
import com.cagiris.coho.service.exception.HierarchyServiceException;

/**
 * @author Ashish Jindal
 *
 */
@Controller
public class ShiftInfoController extends AbstractController {

    public static final String GET_SHIFT_STATUS_URL_MAPPING = "/get-shift-status";
    public static final String START_USER_SHIFT_URL_MAPPING = "/start-shift";
    public static final String END_USER_SHIFT_URL_MAPPING = "/end-shift";

    @Autowired
    private IAttendenceReportingService attendenceReportingService;

    @Autowired
    private IHierarchyService hierarchyService;

    @RequestMapping(GET_SHIFT_STATUS_URL_MAPPING)
    @ResponseBody
    public String getShiftStatus() throws AttendenceReportingServiceException, HierarchyServiceException {
        IUserShiftInfo currentUserShiftInTeam = attendenceReportingService.getCurrentUserShiftInTeam(getDefaultTeam()
                .getTeamId(), ControllerUtils.getLoggedInUser().getUsername());
        return currentUserShiftInTeam.getShiftId();
    }

    @RequestMapping(value = START_USER_SHIFT_URL_MAPPING, method = RequestMethod.POST)
    @ResponseBody
    public String startShift() throws AttendenceReportingServiceException, HierarchyServiceException {
        IUserShiftInfo startUserShift = attendenceReportingService.startUserShift(getDefaultTeam().getTeamId(),
                ControllerUtils.getLoggedInUser().getUsername());
        return startUserShift.getShiftId();
    }

    @RequestMapping(value = END_USER_SHIFT_URL_MAPPING, method = RequestMethod.POST)
    @ResponseBody
    public String endShift(@RequestParam String shiftId) throws AttendenceReportingServiceException {
        attendenceReportingService.endUserShift(shiftId, "user logged out");
        return "ok";
    }

    private ITeam getDefaultTeam() throws HierarchyServiceException {
        IOrganization defaultOrganization = hierarchyService.getDefaultOrganization();
        return hierarchyService.getDefaultTeam(defaultOrganization.getOrganizationId());
    }
}
