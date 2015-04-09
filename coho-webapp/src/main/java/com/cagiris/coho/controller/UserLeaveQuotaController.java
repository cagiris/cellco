package com.cagiris.coho.controller;

import java.io.Serializable;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cagiris.coho.model.ICRUDBean;
import com.cagiris.coho.model.UserLeaveQuotaBean;
import com.cagiris.coho.service.api.ILeaveManagementService;
import com.cagiris.coho.service.api.IUserLeaveQuota;

@Controller
@RequestMapping(value = "userLeaveQuota")
public class UserLeaveQuotaController extends AbstractCRUDController<ICRUDBean> {
    @Autowired
    private ILeaveManagementService leaveManagementService;

    @Override
    public ModelMap create(ICRUDBean bean, BindingResult bindingResult, ModelMap modelMap) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void delete(Serializable entityId) throws Exception {
        // TODO Auto-generated method stub

    }

    @RequestMapping(value = "/fetch", method = RequestMethod.GET)
    public @ResponseBody UserLeaveQuotaBean fetch() throws Exception {
        User loggedInUser = getLoggedInUser();
        IUserLeaveQuota userLeaveQuota = leaveManagementService.getUserLeaveQuota(loggedInUser.getUsername());

        UserLeaveQuotaBean userLeaveQuotaBean = new UserLeaveQuotaBean(userLeaveQuota);

        return userLeaveQuotaBean;
    }

    @Override
    public String getURLMapping() {
        // TODO Auto-generated method stub
        return "userLeaveQuota";
    }

    @Override
    public ModelMap showCreatePage(ModelMap modelMap) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ModelMap showFilteredListPage(Map<String, String> filterParams, ModelMap modelMap) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ModelMap showListPage(ModelMap modelMap) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ModelMap showUpdatePage(Serializable entityId, ModelMap modelMap) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ModelMap update(Serializable entityId, ICRUDBean bean, BindingResult bindingResult, ModelMap modelMap)
            throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public ModelMap get(Serializable entityId) throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

}
