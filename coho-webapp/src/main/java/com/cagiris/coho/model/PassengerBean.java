/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.model;

import java.util.Date;

import javax.annotation.Nonnull;
import javax.validation.GroupSequence;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.cagiris.coho.service.flight.api.IPassenger;
import com.cagiris.coho.service.flight.api.PassengerInfoBean;
import com.cagiris.coho.service.flight.api.PassengerType;

/**
 *
 * @author: Ashish Jindal
 */
@GroupSequence({ValidationCheckForEmpty.class, ValidationCheckForLength.class, ValidationCheckForPattern.class,
        PassengerBean.class})
public class PassengerBean extends AbstractBean {

    @NotBlank(message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Can only contain alphabets", groups = ValidationCheckForPattern.class)
    @Size(min = 1, max = 50, message = "Too long (Max ({max}))", groups = ValidationCheckForLength.class)
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]*$", message = "Can only contain alphabets", groups = ValidationCheckForPattern.class)
    @Size(min = 0, max = 50, message = "Too long (Max ({max}))", groups = ValidationCheckForLength.class)
    private String middleName;

    @Pattern(regexp = "^[a-zA-Z]*$", message = "Can only contain alphabets", groups = ValidationCheckForPattern.class)
    @Size(min = 0, max = 50, message = "Too long (Max ({max}))", groups = ValidationCheckForLength.class)
    private String lastName;

    @NotBlank(message = "Field can't be left empty", groups = ValidationCheckForEmpty.class)
    private String type;

    @Nonnull
    @DateTimeFormat(pattern = Constants.DATE_FORMAT_BOOKING)
    private Date dateOfBirth;

    public PassengerBean() {

    }

    public PassengerBean(IPassenger passenger) {
        this.firstName = passenger.getFirstName();
        this.middleName = passenger.getMiddleName();
        this.lastName = passenger.getLastName();
        this.type = passenger.getType().toString();
        this.dateOfBirth = passenger.getDateOfBirth();
    }

    public PassengerInfoBean mapToPassengerInfoBean() {
        PassengerInfoBean passengerInfoBean = new PassengerInfoBean();
        passengerInfoBean.setDateOfBirth(dateOfBirth);
        passengerInfoBean.setFirstName(firstName);
        passengerInfoBean.setLastName(lastName);
        passengerInfoBean.setMiddleName(middleName);
        passengerInfoBean.setType(PassengerType.valueOf(type));
        return passengerInfoBean;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
}
