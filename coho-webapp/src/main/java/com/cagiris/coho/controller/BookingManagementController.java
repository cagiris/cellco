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

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.cagiris.coho.model.BookingDetailsBean;
import com.cagiris.coho.model.CustomerBean;
import com.cagiris.coho.model.PassengerBean;
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
    public static final String PASSENGER_DETAILS_URL_MAPPING = "/passenger-details";
    public static final String BOOKING_SAVE_URL_MAPPING = "/save";

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
        BookingDetailsBean bookingDetailsBean = new BookingDetailsBean();
        bookingDetailsBean.getPassengers().add(new PassengerBean());

        modelMap.addAttribute(bookingDetailsBean);
        modelMap.addAttribute(PassengerType.values());
        modelMap.addAttribute(BookingGDSType.values());

        return modelMap;
    }

    @Override
    protected ModelMap create(BookingDetailsBean bean, ModelMap modelMap) throws CohoException {
        CustomerBean customerBean = bean.getCustomer();
        ICustomer addCustomer = bookingManagementService.addCustomer(customerBean.getFirstName(),
                customerBean.getLastName(), customerBean.getMiddleName(), customerBean.getAddressLine1(),
                customerBean.getAddressLine2(), customerBean.getCity(), customerBean.getContactNumber(),
                customerBean.getCountry(), customerBean.getEmailId(), customerBean.getPincode(),
                customerBean.getState());
        List<PassengerInfoBean> passengerInfos = bean.getPassengers().stream().map((p) -> p.mapToPassengerInfoBean())
                .collect(Collectors.toList());
        User loggedInUser = ControllerUtils.getLoggedInUser();
        IBookingDetails bookingDetails = bookingManagementService.submitBookingDetails(loggedInUser.getUsername(), bean
                .getPnr(), addCustomer.getCustomerId(), passengerInfos,
                BookingGDSType.valueOf(bean.getBookingGDSType()), new BigDecimal(bean.getBaseFare()), new BigDecimal(
                        bean.getTaxesAndServiceFee()), new BigDecimal(bean.getMiscellaneousCharges()));
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

    @RequestMapping(value = {PASSENGER_DETAILS_URL_MAPPING}, method = RequestMethod.POST)
    public ModelAndView addPassenger() {
        ModelAndView model = new ModelAndView();
        model.addObject("passengerIndex", (int)(500 + Math.random() * 1000));
        model.addObject(PassengerType.values());
        model.setViewName(ControllerUtils.AJAX_CONTENT_MAPPING_PREFIX + getURLMapping() + PASSENGER_DETAILS_URL_MAPPING);
        return model;
    }

    @RequestMapping(value = {BOOKING_SAVE_URL_MAPPING}, method = RequestMethod.POST,
                    consumes = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView saveBooking(@RequestBody @Valid BookingDetailsBean bean, BindingResult bindingResult,
            ModelMap modelMap) {

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject(PassengerType.values());
        modelAndView.addObject(BookingGDSType.values());

        modelAndView.setViewName(ControllerUtils.AJAX_CONTENT_MAPPING_PREFIX + getURLMapping() + CREATE_URL_MAPPING);
        try {
            if (bindingResult.hasErrors() || (bean.getPassengers().size() == 0)) {
                modelAndView.addObject(bean);
                modelAndView.addAllObjects(modelMap);

                if ((bean.getPassengers().size() == 0)) {
                    modelAndView.addObject(ATTR_ERROR_MSG, "Please atleast one passenger in the booking");
                }
            } else {
                CustomerBean customerBean = bean.getCustomer();
                ICustomer addCustomer = bookingManagementService.addCustomer(customerBean.getFirstName(),
                        customerBean.getLastName(), customerBean.getMiddleName(), customerBean.getAddressLine1(),
                        customerBean.getAddressLine2(), customerBean.getCity(), customerBean.getContactNumber(),
                        customerBean.getCountry(), customerBean.getEmailId(), customerBean.getPincode(),
                        customerBean.getState());
                List<PassengerInfoBean> passengerInfos = bean.getPassengers().stream()
                        .map((p) -> p.mapToPassengerInfoBean()).collect(Collectors.toList());
                User loggedInUser = ControllerUtils.getLoggedInUser();
                IBookingDetails bookingDetails = bookingManagementService.submitBookingDetails(
                        loggedInUser.getUsername(), bean.getPnr(), addCustomer.getCustomerId(), passengerInfos,
                        BookingGDSType.valueOf(bean.getBookingGDSType()), new BigDecimal(bean.getBaseFare()),
                        new BigDecimal(bean.getTaxesAndServiceFee()), new BigDecimal(bean.getMiscellaneousCharges()));
                bean.setBookingId(bookingDetails.getBookingId());

                modelMap.addAllAttributes(getCreateFormModel());
                modelAndView.addObject(ATTR_SUCCESS_MSG, "Booking saved Successfuly");
            }
        } catch (CohoException e) {
            modelAndView.addAllObjects(modelMap);
            modelAndView.addObject(bean);
            modelAndView.addObject(ATTR_ERROR_MSG, e.getMessage());
        }

        return modelAndView;
    }
}
