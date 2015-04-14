/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotBlank;

/**
 *
 * @author: Ashish Jindal
 */
@GroupSequence({ValidationCheckForEmpty.class, ValidationCheckForLength.class, ValidationCheckForPattern.class,
        BookingDetailsBean.class})
public class BookingDetailsBean extends AbstractBean implements ICRUDBean {

    private String bookingId;

    @Valid
    private CustomerBean customer = new CustomerBean();

    @Valid
    private List<PassengerBean> passengers = new ArrayList();

    @NotNull(message = "Can't be left Empty")
    private String bookingGDSType;

    @Pattern(regexp = "^[0-9]*.?[0-9]*$", message = "Can only contain decimals",
             groups = ValidationCheckForPattern.class)
    @NotBlank(message = "Can't be left Empty")
    private String baseFare;

    @Pattern(regexp = "^[0-9]*.?[0-9]*$", message = "Can only contain decimals",
             groups = ValidationCheckForPattern.class)
    @NotBlank(message = "Can't be left Empty")
    private String taxesAndServiceFee;

    @Pattern(regexp = "^[0-9]*.?[0-9]*$", message = "Can only contain decimals",
             groups = ValidationCheckForPattern.class)
    @NotBlank(message = "Can't be left Empty")
    private String miscellaneousCharges; //MCO

    @Override
    public Serializable getEntityId() {
        return bookingId;
    }

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public CustomerBean getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerBean customer) {
        this.customer = customer;
    }

    public List<PassengerBean> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerBean> passengers) {
        this.passengers = passengers;
    }

    public String getBookingGDSType() {
        return bookingGDSType;
    }

    public void setBookingGDSType(String bookingGDSType) {
        this.bookingGDSType = bookingGDSType;
    }

    public String getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(String baseFare) {
        this.baseFare = baseFare;
    }

    public String getTaxesAndServiceFee() {
        return taxesAndServiceFee;
    }

    public void setTaxesAndServiceFee(String taxesAndServiceFee) {
        this.taxesAndServiceFee = taxesAndServiceFee;
    }

    public String getMiscellaneousCharges() {
        return miscellaneousCharges;
    }

    public void setMiscellaneousCharges(String miscellaneousCharges) {
        this.miscellaneousCharges = miscellaneousCharges;
    }

}
