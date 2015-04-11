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
    private Double baseFare;

    @NotNull(message = "Can't be left Empty")
    private Double taxesAndServiceFee;

    @NotNull(message = "Can't be left Empty")
    private Double miscellaneousCharges; //MCO

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

    public Double getBaseFare() {
        return baseFare;
    }

    public void setBaseFare(Double baseFare) {
        this.baseFare = baseFare;
    }

    public Double getTaxesAndServiceFee() {
        return taxesAndServiceFee;
    }

    public void setTaxesAndServiceFee(Double taxesAndServiceFee) {
        this.taxesAndServiceFee = taxesAndServiceFee;
    }

    public Double getMiscellaneousCharges() {
        return miscellaneousCharges;
    }

    public void setMiscellaneousCharges(Double miscellaneousCharges) {
        this.miscellaneousCharges = miscellaneousCharges;
    }

}
