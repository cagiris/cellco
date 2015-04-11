/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cagiris.coho.model.AnnualHolidayBean;
import com.cagiris.coho.model.WeeklyHolidayBean;
import com.cagiris.coho.service.api.IAnnualHoliday;
import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.ILeaveManagementService;
import com.cagiris.coho.service.api.IOrganization;

/**
 *
 * @author: akhil
 */
@Controller
@RequestMapping(HolidayManagementController.URL_MAPPING)
public class HolidayManagementController extends AbstractController {

    public static final String URL_MAPPING = "holiday";
    public static final String CREATE_ANNUAL_HOLIDAY_URL_MAPPING = "/create-annual";
    public static final String CREATE_ANNUAL_WEEKLY_URL_MAPPING = "/create-weekly";
    public static final String LIST_URL_MAPPING = "/list";

    @Autowired
    private ILeaveManagementService leaveManagementService;

    @Autowired
    private IHierarchyService hierarchyService;

    @RequestMapping(value = CREATE_ANNUAL_HOLIDAY_URL_MAPPING, method = RequestMethod.GET)
    public final ModelAndView showCreateAnnualHolidayPage(ModelMap modelMap) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(URL_MAPPING + CREATE_ANNUAL_HOLIDAY_URL_MAPPING);

        IOrganization defaultOrganization = hierarchyService.getDefaultOrganization();
        modelMap.addAttribute(hierarchyService.getAvailableUserRoles(defaultOrganization.getOrganizationId()));
        modelMap.addAttribute(new AnnualHolidayBean());
        modelAndView.addAllObjects(modelMap);

        return modelAndView;
    }

    @RequestMapping(value = CREATE_ANNUAL_HOLIDAY_URL_MAPPING, method = RequestMethod.POST)
    public final ModelAndView createAnnualHoliday(@Valid @ModelAttribute AnnualHolidayBean annualHolidayBean,
            BindingResult bindingResult, ModelMap modelMap) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        leaveManagementService
                .addAnnualHoliday(hierarchyService.getDefaultOrganization().getOrganizationId(),
                        annualHolidayBean.getUserRole(), annualHolidayBean.getHolidayDate(),
                        annualHolidayBean.getDescription());
        ModelMap responseModelMap = new ModelMap();
        responseModelMap.addAttribute(ATTR_SUCCESS_MSG, "Success");
        modelAndView.addObject(responseModelMap);
        modelAndView.setViewName(URL_MAPPING + CREATE_ANNUAL_HOLIDAY_URL_MAPPING);
        return modelAndView;
    }

    @RequestMapping(value = CREATE_ANNUAL_WEEKLY_URL_MAPPING, method = RequestMethod.GET)
    public final ModelAndView showCreateWeeklyHolidayPage(ModelMap modelMap) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(URL_MAPPING + CREATE_ANNUAL_WEEKLY_URL_MAPPING);
        modelMap.addAttribute(new WeeklyHolidayBean());
        modelAndView.addAllObjects(modelMap);
        return modelAndView;
    }

    @RequestMapping(value = CREATE_ANNUAL_WEEKLY_URL_MAPPING, method = RequestMethod.POST)
    public final ModelAndView createWeeklyHoliday(@Valid @ModelAttribute WeeklyHolidayBean weeklyHolidayBean,
            BindingResult bindingResult, ModelMap modelMap) throws Exception {
        ModelAndView modelAndView = new ModelAndView();
        leaveManagementService.addWeeklyHoliday(hierarchyService.getDefaultOrganization().getOrganizationId(),
                weeklyHolidayBean.getUserRole(), weeklyHolidayBean.getWeekDay(), weeklyHolidayBean.getDescription());
        ModelMap responseModelMap = new ModelMap();
        responseModelMap.addAttribute(ATTR_SUCCESS_MSG, "Success");
        modelAndView.addObject(responseModelMap);
        modelAndView.setViewName(URL_MAPPING + CREATE_ANNUAL_WEEKLY_URL_MAPPING);
        return modelAndView;
    }

    @RequestMapping(value = LIST_URL_MAPPING)
    public final ModelAndView showListPageInternal(@RequestParam(required = false) Map<String, String> params,
            ModelMap modelMap) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(URL_MAPPING + LIST_URL_MAPPING);
        ModelMap responseMap = new ModelMap();
        //        responseMap.addAllAttributes(leaveManagementService.getAllAnnualHolidays(hierarchyService
        //                .getDefaultOrganization().getOrganizationId()));

        /* 
         List<? extends IWeeklyHoliday> allWeeklyHolidays = leaveManagementService.getAllWeeklyHolidays(hierarchyService
                 .getDefaultOrganization().getOrganizationId());

         List<WeeklyHolidayBean> weeklyHolidayBeans = allWeeklyHolidays.stream().map(WeeklyHolidayBean::mapToBean)
                 .collect(Collectors.toList());
         responseMap.addAttribute(weeklyHolidayBeans);
         */
        List<? extends IAnnualHoliday> allAnnualHolidays = leaveManagementService.getAllAnnualHolidays(hierarchyService
                .getDefaultOrganization().getOrganizationId());

        List<AnnualHolidayBean> annualHolidayBean = allAnnualHolidays.stream().map(AnnualHolidayBean::mapToBean)
                .collect(Collectors.toList());

        responseMap.addAttribute(annualHolidayBean);

        modelAndView.addAllObjects(responseMap);
        return modelAndView;
    }
}
