/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import java.io.Serializable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cagiris.coho.model.TeamShiftDetailsBean;
import com.cagiris.coho.service.api.IAttendenceReportingService;
import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.ITeam;
import com.cagiris.coho.service.api.ITeamShiftDetails;
import com.cagiris.coho.service.exception.CohoException;

/**
 *
 * @author: ssnk
 */
@Controller
@RequestMapping(TeamShiftDetailsController.URL_MAPPING)
public class TeamShiftDetailsController extends AbstractCRUDController<TeamShiftDetailsBean> {

    @Autowired
    private IHierarchyService hierarchyService;

    @Autowired
    private IAttendenceReportingService attendenceReportingService;

    public static final String URL_MAPPING = "teamShiftDetails";

    @Override
    protected String getURLMapping() {
        return URL_MAPPING;
    }

    @Override
    protected ModelMap getCreateFormModel() throws CohoException {
        return null;
    }

    @Override
    protected ModelMap create(TeamShiftDetailsBean bean, ModelMap modelMap) throws CohoException {
        ITeam defaultTeam = ControllerUtils.getDefaultTeam(hierarchyService);
        ITeamShiftDetails updateTeamShiftDetails = attendenceReportingService.updateTeamShiftDetails(
                defaultTeam.getTeamId(), bean.getShiftStartTime(), bean.getShiftEndTime(), bean.getShiftBuffer(), true);
        ModelMap responseModelMap = new ModelMap();
        responseModelMap.addAttribute(ATTR_SUCCESS_MSG, "Team shift details successfuly updated.");
        responseModelMap.addAttribute(new TeamShiftDetailsBean(updateTeamShiftDetails));
        return responseModelMap;
    }

    @Override
    protected void delete(Serializable entityId) throws CohoException {
    }

    @Override
    protected ModelMap get(Serializable entityId) throws CohoException {
        ITeam defaultTeam = ControllerUtils.getDefaultTeam(hierarchyService);
        ITeamShiftDetails teamShiftDetails = attendenceReportingService.getTeamShiftDetails(defaultTeam.getTeamId());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute(TeamShiftDetailsBean.mapToBean(teamShiftDetails));
        return modelMap;
    }

    @Override
    protected ModelMap getListFormModel() throws CohoException {
        return null;
    }

    @Override
    protected ModelMap getListData(Map<String, String> params) throws CohoException {
        return null;
    }

    @Override
    protected ModelMap update(Serializable entityId, TeamShiftDetailsBean bean, ModelMap modelMap) throws CohoException {
        return null;
    }

}
