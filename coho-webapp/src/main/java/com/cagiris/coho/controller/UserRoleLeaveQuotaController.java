/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import java.io.Serializable;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cagiris.coho.model.UserRoleLeaveQuotaBean;
import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.ILeaveManagementService;
import com.cagiris.coho.service.api.ITeam;
import com.cagiris.coho.service.api.IUserRoleLeaveQuota;
import com.cagiris.coho.service.api.LeaveAccumulationPolicy;
import com.cagiris.coho.service.exception.CohoException;
import com.cagiris.coho.service.utils.JSONUtils;

/**
 *
 * @author: ssnk
 */
@Controller
@RequestMapping(UserRoleLeaveQuotaController.URL_MAPPING)
public class UserRoleLeaveQuotaController extends AbstractCRUDController<UserRoleLeaveQuotaBean> {

    @Autowired
    private IHierarchyService hierarchyService;

    @Autowired
    private ILeaveManagementService leaveManagementService;

    public static final String URL_MAPPING = "userRoleLeaveQuota";

    @Override
    protected String getURLMapping() {
        return URL_MAPPING;
    }

    @Override
    protected ModelMap getCreateFormModel() throws CohoException {
        return null;
    }

    @Override
    protected ModelMap create(UserRoleLeaveQuotaBean bean, ModelMap modelMap) throws CohoException {
        return null;
    }

    @Override
    protected void delete(Serializable entityId) throws CohoException {
    }

    @Override
    protected ModelMap get(Serializable entityId) throws CohoException {
        return null;
    }

    @Override
    protected ModelMap getListFormModel() throws CohoException {
        return null;
    }

    @Override
    protected ModelMap getListData(Map<String, String> params) throws CohoException {
        ITeam defaultTeam = ControllerUtils.getDefaultTeam(hierarchyService);
        List<? extends IUserRoleLeaveQuota> allUserRoleLeaveQuotas = leaveManagementService
                .getAllUserRoleLeaveQuotas(defaultTeam.getOrganizationId());
        List<UserRoleLeaveQuotaBean> userRoleLeaveQuotaBeans = allUserRoleLeaveQuotas.stream()
                .map(UserRoleLeaveQuotaBean::mapToBean).collect(Collectors.toList());
        ModelMap modelMap = new ModelMap();
        modelMap.addAttribute("userRoleLeaveQuotaBeans", userRoleLeaveQuotaBeans);
        modelMap.addAttribute("leaveAccumulationPolicies", LeaveAccumulationPolicy.values());
        return modelMap;
    }

    /**
     * @throws CohoException  
     */
    @RequestMapping(value = "/modify", method = RequestMethod.POST)
    public @ResponseBody String updateUserRoleLeaveQuota(@RequestParam String input) throws CohoException {
        UserRoleLeaveQuotaBean bean = JSONUtils.parseJSON(input, UserRoleLeaveQuotaBean.class);
        leaveManagementService.updateLeaveQuotaForRole(bean.getUserLeaveQuotaId(), bean.getLeaveTypeVsLeaveCount(),
                bean.getLeaveAccumulationPolicy());
        return "ok";
    }

    @Override
    protected ModelMap update(Serializable entityId, UserRoleLeaveQuotaBean bean, ModelMap modelMap)
            throws CohoException {
        return null;
    }

}
