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

import com.cagiris.coho.model.UserShiftInfoBean;
import com.cagiris.coho.service.api.IAttendenceReportingService;
import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.IOrganization;
import com.cagiris.coho.service.api.ITeam;
import com.cagiris.coho.service.api.IUserShiftInfo;
import com.cagiris.coho.service.exception.CohoException;
import com.cagiris.coho.service.exception.HierarchyServiceException;
import com.cagiris.coho.service.utils.JSONUtils;

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
    public String getShiftStatus() throws CohoException {
        IUserShiftInfo currentUserShiftInTeam = attendenceReportingService.getCurrentUserShiftInTeam(getDefaultTeam()
                .getTeamId(), ControllerUtils.getLoggedInUser().getUsername());
        UserShiftInfoBean userShiftInfoBean = UserShiftInfoBean.mapToBean(currentUserShiftInTeam);
        return JSONUtils.getJsonStringForObject(userShiftInfoBean);
    }

    @RequestMapping(value = START_USER_SHIFT_URL_MAPPING, method = RequestMethod.POST)
    @ResponseBody
    public String startShift() throws CohoException {
        IUserShiftInfo startUserShift = attendenceReportingService.startUserShift(getDefaultTeam().getTeamId(),
                ControllerUtils.getLoggedInUser().getUsername());
        UserShiftInfoBean userShiftInfoBean = UserShiftInfoBean.mapToBean(startUserShift);
        return JSONUtils.getJsonStringForObject(userShiftInfoBean);
    }

    @RequestMapping(value = END_USER_SHIFT_URL_MAPPING, method = RequestMethod.POST)
    @ResponseBody
    public String endShift(@RequestParam String shiftId) throws CohoException {
        attendenceReportingService.endUserShift(shiftId, "user logged out");
        return "ok";
    }

    private ITeam getDefaultTeam() throws HierarchyServiceException {
        IOrganization defaultOrganization = hierarchyService.getDefaultOrganization();
        return hierarchyService.getDefaultTeam(defaultOrganization.getOrganizationId());
    }
}
