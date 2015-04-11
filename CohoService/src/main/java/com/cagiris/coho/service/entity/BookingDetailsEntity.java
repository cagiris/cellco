/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.cagiris.coho.service.api.IBookingDetails;
import com.cagiris.coho.service.api.ICustomer;
import com.cagiris.coho.service.api.IUser;

/**
 *
 * @author: Ashish Jindal
 */
@Entity
public class BookingDetailsEntity extends BaseEntity implements IBookingDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookingId;

    @JoinColumn(name = "userId")
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = UserEntity.class)
    private IUser user;

    @JoinColumn(name = "customerId")
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = CustomerEntity.class)
    private ICustomer customer;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "booking", orphanRemoval = true)
    private Set<BookingPassengerInfoEntity> bookingVsPassengers = new HashSet<>();

    private double baseFare;

    private double taxesAndServiceFee;

    private double miscellaneousChanrges; //MCO

    @Override
    public double getBaseFare() {
        return baseFare;
    }

    @Override
    public Long getBookingId() {
        return bookingId;
    }

    public Set<BookingPassengerInfoEntity> getBookingVsPassengers() {
        return bookingVsPassengers;
    }

    @Override
    public ICustomer getCustomer() {
        return customer;
    }

    @Override
    public double getMiscellaneousChanrges() {
        return miscellaneousChanrges;
    }

    @Override
    public Set<IPassenger> getPassengers() {
        Set<IPassenger> passengers = new HashSet<>();
        for (BookingPassengerInfoEntity passengerInfo : bookingVsPassengers) {
            passengers.add(passengerInfo.getPassenger());
        }
        return passengers;
    }

    @Override
    public double getTaxesAndServiceFee() {
        return taxesAndServiceFee;
    }

    @Override
    public IUser getUser() {
        return user;
    }

    public void setBaseFare(double baseFare) {
        this.baseFare = baseFare;
    }

    public void setBookingId(Long bookingId) {
        this.bookingId = bookingId;
    }

    public void setBookingVsPassengers(Set<BookingPassengerInfoEntity> bookingVsPassengers) {
        this.bookingVsPassengers = bookingVsPassengers;
    }

    public void setCustomer(ICustomer customer) {
        this.customer = customer;
    }

    public void setMiscellaneousChanrges(double miscellaneousChanrges) {
        this.miscellaneousChanrges = miscellaneousChanrges;
    }

    public void setTaxesAndServiceFee(double taxesAndServiceFee) {
        this.taxesAndServiceFee = taxesAndServiceFee;
    }

    public void setUser(IUser user) {
        this.user = user;
    }
}
