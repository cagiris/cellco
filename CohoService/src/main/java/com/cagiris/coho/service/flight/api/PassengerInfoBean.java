/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.flight.api;

import java.math.BigInteger;
import java.util.Date;

/**
 *
 * @author: ssnk
 */

public class PassengerInfoBean implements IPassenger {

    private BigInteger passengerId;

    private String firstName;

    private String lastName;
    private String middleName;
    private PassengerType type;

    private Date dateOfBirth;

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
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Override
    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

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

}
