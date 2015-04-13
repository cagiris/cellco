/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cagiris.coho.model.BookingDetailsBean;
import com.cagiris.coho.model.CustomerBean;
import com.cagiris.coho.model.UserBean;
import com.cagiris.coho.service.exception.CohoException;
import com.cagiris.coho.service.flight.api.BookingGDSType;
import com.cagiris.coho.service.flight.api.IBookingDetails;
import com.cagiris.coho.service.flight.api.ICustomer;
import com.cagiris.coho.service.flight.api.PassengerInfoBean;
import com.cagiris.coho.service.flight.api.PassengerType;
import com.cagiris.coho.service.flight.impl.BookingManagementService;

/**
 *
 * @author: Ashish Jindal
 */

@Controller
@RequestMapping(BookingManagementController.URL_MAPPING)
public class BookingManagementController extends AbstractCRUDController<BookingDetailsBean> {

    public static final String URL_MAPPING = "booking";
    public static final String BOOKING_HISTORY_URL_MAPPING = "/history";

    @Autowired
    private BookingManagementService bookingManagementService;

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
        modelMap.addAttribute(BookingGDSType.values());

        return modelMap;
    }

    @Override
    protected ModelMap create(BookingDetailsBean bean, ModelMap modelMap) throws CohoException {
        CustomerBean customerBean = bean.getCustomer();
        ICustomer addCustomer = bookingManagementService.addCustomer(customerBean.getFirstName(),
                customerBean.getLastName(), "", customerBean.getAddressLine1(), customerBean.getAddressLine2(),
                customerBean.getCity(), customerBean.getContactNumber(), customerBean.getCountry(),
                customerBean.getEmailId(), customerBean.getPincode(), customerBean.getState());
        List<PassengerInfoBean> passengerInfos = bean.getPassengers().stream().map((p) -> p.mapToPassengerInfoBean())
                .collect(Collectors.toList());
        User loggedInUser = ControllerUtils.getLoggedInUser();
        IBookingDetails bookingDetails = bookingManagementService.submitBookingDetails(loggedInUser.getUsername(),
                addCustomer.getCustomerId(), passengerInfos, BookingGDSType.valueOf(bean.getBookingGDSType()),
                BigDecimal.valueOf(bean.getBaseFare()), BigDecimal.valueOf(bean.getTaxesAndServiceFee()),
                BigDecimal.valueOf(bean.getMiscellaneousCharges()));
        bean.setBookingId(bookingDetails.getBookingId());
        return null;
    }

    @Override
    protected String getCreateSuccessRedirectView(Serializable entityId) {
        return ("redirect:/" + URL_MAPPING + CREATE_URL_MAPPING);
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

    @RequestMapping(value = BOOKING_HISTORY_URL_MAPPING)
    public ModelAndView showBookingHistoryPage(@RequestParam(required = false) Map<String, String> params,
            ModelMap modelMap) throws Exception {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(getURLMapping() + BOOKING_HISTORY_URL_MAPPING);

        modelAndView.addAllObjects(getbookingHistoryData(params));

        return modelAndView;
    }

    private Map<String, ?> getbookingHistoryData(Map<String, String> params) {
        return null;
    }
}
