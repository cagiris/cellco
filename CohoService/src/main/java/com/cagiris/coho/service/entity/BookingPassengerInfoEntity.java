/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cagiris.coho.service.api.IBookingDetails;
import com.cagiris.coho.service.api.IPassenger;

/**
 *
 * @author: Ashish Jindal
 */
@Entity
public class BookingPassengerInfoEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "bookingId")
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = BookingDetailsEntity.class)
    private IBookingDetails booking;

    @JoinColumn(name = "passengerId")
    @ManyToOne(fetch = FetchType.EAGER, targetEntity = PassengerInfoEntity.class)
    private IPassenger passenger;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public IBookingDetails getBooking() {
        return booking;
    }

    public void setBooking(IBookingDetails booking) {
        this.booking = booking;
    }

    public IPassenger getPassenger() {
        return passenger;
    }

    public void setPassenger(IPassenger passenger) {
        this.passenger = passenger;
    }
}
