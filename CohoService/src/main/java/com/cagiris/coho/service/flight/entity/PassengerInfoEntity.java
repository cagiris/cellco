/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.flight.entity;

import java.math.BigInteger;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.cagiris.coho.service.entity.BaseEntity;
import com.cagiris.coho.service.flight.api.IPassenger;
import com.cagiris.coho.service.flight.api.PassengerType;

/**
 *
 * @author: Ashish Jindal
 */

@Entity
public class PassengerInfoEntity extends BaseEntity implements IPassenger {

    private BigInteger passengerId;

    private String firstName;

    private String middleName;

    private String lastName;

    private PassengerType type;

    private Date dateOfBirth;

    private BookingDetailsEntity bookingDetailsEntity;

    public PassengerInfoEntity() {

    }

    public PassengerInfoEntity(String firstName, String middleName, String lastName, PassengerType type,
            Date dateOfBirth) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.type = type;
        this.dateOfBirth = dateOfBirth;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public BigInteger getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(BigInteger passengerId) {
        this.passengerId = passengerId;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Override
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Enumerated(EnumType.STRING)
    @Override
    public PassengerType getType() {
        return type;
    }

    public void setType(PassengerType type) {
        this.type = type;
    }

    @Override
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @ManyToOne
    @JoinColumn(name = "booking_id")
    public BookingDetailsEntity getBookingDetailsEntity() {
        return bookingDetailsEntity;
    }

    public void setBookingDetailsEntity(BookingDetailsEntity bookingDetailsEntity) {
        this.bookingDetailsEntity = bookingDetailsEntity;
    }
}
