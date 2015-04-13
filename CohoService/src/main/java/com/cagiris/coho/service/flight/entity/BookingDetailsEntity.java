/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.flight.entity;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import com.cagiris.coho.service.entity.BaseEntity;
import com.cagiris.coho.service.flight.api.BookingGDSType;
import com.cagiris.coho.service.flight.api.IBookingDetails;

/**
 *
 * @author: Ashish Jindal
 */
@Entity
public class BookingDetailsEntity extends BaseEntity implements IBookingDetails {

    private String bookingId;

    private String userId;

    private CustomerEntity customer;

    private List<PassengerInfoEntity> passengers;

    private BookingGDSType bookingGDSType;

    private BigDecimal baseFare;

    private BigDecimal taxesAndServiceFee;

    private BigDecimal miscellaneousCharges; //MCO

    @Override
    public BigDecimal getBaseFare() {
        return baseFare;
    }

    @Id
    @Override
    public String getBookingId() {
        return bookingId;
    }

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "customer_id")
    @Override
    public CustomerEntity getCustomer() {
        return customer;
    }

    @Override
    public BigDecimal getMiscellaneousCharges() {
        return miscellaneousCharges;
    }

    @Override
    public BigDecimal getTaxesAndServiceFee() {
        return taxesAndServiceFee;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    public void setBaseFare(BigDecimal baseFare) {
        this.baseFare = baseFare;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public void setMiscellaneousCharges(BigDecimal miscellaneousCharges) {
        this.miscellaneousCharges = miscellaneousCharges;
    }

    public void setTaxesAndServiceFee(BigDecimal taxesAndServiceFee) {
        this.taxesAndServiceFee = taxesAndServiceFee;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "booking_id")
    @Override
    public List<PassengerInfoEntity> getPassengers() {
        return passengers;
    }

    public void setPassengers(List<PassengerInfoEntity> passengers) {
        this.passengers = passengers;
    }

    @Enumerated(EnumType.STRING)
    @Override
    public BookingGDSType getBookingGDSType() {
        return bookingGDSType;
    }

    public void setBookingGDSType(BookingGDSType bookingGDSType) {
        this.bookingGDSType = bookingGDSType;
    }
}
