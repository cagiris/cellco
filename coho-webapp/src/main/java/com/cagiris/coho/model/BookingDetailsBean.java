/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.model;

import java.io.Serializable;
import java.util.Set;

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

    private Long bookingId;

    @Valid
    private CustomerBean customer;

    @Valid
    private Set<PassengerBean> passengers;

    @NotNull
    private Double baseFare;

    @NotNull
    private Double taxesAndServiceFee;

    @NotNull
    private Double miscellaneousChanrges; //MCO

    @Override
    public Serializable getEntityId() {
        return bookingId;
    }

}
