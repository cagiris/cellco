/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import java.io.Serializable;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cagiris.coho.model.BookingDetailsBean;
import com.cagiris.coho.model.UserBean;
import com.cagiris.coho.service.api.PassengerType;
import com.cagiris.coho.service.exception.CohoException;

/**
 *
 * @author: Ashish Jindal
 */

@Controller
@RequestMapping(BookingManagementController.URL_MAPPING)
public class BookingManagementController extends AbstractCRUDController<BookingDetailsBean> {

    public static final String URL_MAPPING = "booking";

    @Override
    protected String getURLMapping() {
        return URL_MAPPING;
    }

    @Override
    protected ModelMap getCreateFormModel() throws CohoException {
        ModelMap modelMap = new ModelMap();

        modelMap.addAttribute(new UserBean());
        modelMap.addAttribute(new BookingDetailsBean());
        modelMap.addAttribute(PassengerType.values());

        return modelMap;
    }

    @Override
    protected ModelMap create(BookingDetailsBean bean, ModelMap modelMap) throws CohoException {
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
        return null;
    }

    @Override
    protected ModelMap update(Serializable entityId, BookingDetailsBean bean, ModelMap modelMap) throws CohoException {
        return null;
    }

}
