/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.cagiris.coho.model.UserShiftInfoBean;
import com.cagiris.coho.service.api.IAttendenceReportingService;
import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.ITeam;
import com.cagiris.coho.service.api.ITeamUser;
import com.cagiris.coho.service.api.IUserShiftInfo;
import com.cagiris.coho.service.exception.AttendenceReportingServiceException;
import com.cagiris.coho.service.exception.HierarchyServiceException;
import com.cagiris.coho.service.utils.DateUtils;

/**
 * @author Ashish Jindal
 *
 */
@Controller
public class UserStatisticsController {

    public static final String GET_ALL_ACTIVE_SHIFT_MAPPING = "/user-runtime-statistics";
    public static final String ALL_USER_SHIFT_MAPPING = "/user-statistics";

    @Autowired
    private IAttendenceReportingService attendenceReportingService;

    @Autowired
    private IHierarchyService hierarchyService;

    @RequestMapping(value = GET_ALL_ACTIVE_SHIFT_MAPPING)
    public ModelAndView showRuntimeStatisticsPage(ModelMap modelMap) throws AttendenceReportingServiceException {
        List<? extends IUserShiftInfo> allActiveShiftInfos = attendenceReportingService.getAllActiveShiftInfos();
        List<UserShiftInfoBean> allActiveShifInfoBeans = allActiveShiftInfos.stream().map(UserShiftInfoBean::mapToBean)
                .collect(Collectors.toList());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("activeShifInfoBeans", allActiveShifInfoBeans);
        modelAndView.setViewName("reports" + GET_ALL_ACTIVE_SHIFT_MAPPING);
        return modelAndView;
    }

    @RequestMapping(value = ALL_USER_SHIFT_MAPPING)
    public ModelAndView showUserShiftStatisticsPage(ModelMap modelMap) throws AttendenceReportingServiceException,
            HierarchyServiceException {
        ITeam defaultTeam = ControllerUtils.getDefaultTeam(hierarchyService);
        List<? extends ITeamUser> allUsersForTeam = hierarchyService.getAllUsersForTeam(defaultTeam.getTeamId());
        List<String> userIds = allUsersForTeam.stream().map(ITeamUser::getUserId).collect(Collectors.toList());
        DateTime dateTime = new DateTime(new Date());
        List<? extends IUserShiftInfo> allActiveShiftInfos = attendenceReportingService.queryUserShiftInfo(userIds,
                dateTime.minusDays(1).toDate(), new Date());
        List<UserShiftInfoBean> allActiveShifInfoBeans = allActiveShiftInfos.stream().map(UserShiftInfoBean::mapToBean)
                .collect(Collectors.toList());
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("shifInfoBeans", allActiveShifInfoBeans);
        modelAndView.setViewName("reports" + ALL_USER_SHIFT_MAPPING);
        return modelAndView;
    }

    @RequestMapping(value = "/modifyUserShift", method = RequestMethod.POST)
    public @ResponseBody String updateUserShiftDetails(@RequestParam String shiftId,
            @RequestParam String shiftStartTime, @RequestParam String shiftEndTime) throws Exception {
        User loggedInUser = ControllerUtils.getLoggedInUser();
        DateTime shiftStartDateTime;
        DateTime shiftEndDateTime;
        try {
            shiftStartDateTime = new DateTime(DateUtils.parseDate(shiftStartTime));
            shiftEndDateTime = new DateTime(DateUtils.parseDate(shiftEndTime));
        } catch (ParseException e) {
            throw new Exception("worng date format");
        }
        attendenceReportingService.updateUserShiftInfo(shiftId, shiftStartDateTime.toDate(), shiftEndDateTime.toDate(),
                "updated by " + loggedInUser.getUsername());
        return "Success";
    }
}
