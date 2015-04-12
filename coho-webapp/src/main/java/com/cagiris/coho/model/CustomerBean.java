/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.model;

import javax.validation.GroupSequence;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;

import com.cagiris.coho.service.flight.api.ICustomer;

/**
 *
 * @author: Ashish Jindal
 */
@GroupSequence({ValidationCheckForEmpty.class, ValidationCheckForLength.class, ValidationCheckForPattern.class,
        CustomerBean.class})
public class CustomerBean extends AbstractBean {

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

    @NotBlank(message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
    @Size(min = 5, max = 15, message = "Too long (Max ({max}))", groups = ValidationCheckForLength.class)
    private String contactNumber;

    @NotBlank(message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
    @Email(message = "Invalid Email", groups = ValidationCheckForPattern.class)
    @Size(min = 5, max = 70, message = "Too long (Max ({max}))", groups = ValidationCheckForLength.class)
    private String emailId;

    @NotBlank(message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
    @Size(min = 1, max = 50, message = "Too long (Max ({max}))", groups = ValidationCheckForLength.class)
    private String addressLine1;

    @Size(min = 1, max = 50, message = "Too long (Max ({max}))", groups = ValidationCheckForLength.class)
    private String addressLine2;

    @NotBlank(message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Invalid city name", groups = ValidationCheckForPattern.class)
    @Size(min = 1, max = 30, message = "Too long (Max ({max}))", groups = ValidationCheckForLength.class)
    private String city;

    @NotBlank(message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
    @Pattern(regexp = "^[0-9]*$", message = "Invalid pin code", groups = ValidationCheckForPattern.class)
    @Size(max = 6, message = "Too long (max {6} characters)", groups = ValidationCheckForLength.class)
    private String pincode;

    @NotBlank(message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Can only contain alphabets", groups = ValidationCheckForPattern.class)
    @Size(min = 1, max = 30, message = "Too long (Max ({max}))", groups = ValidationCheckForLength.class)
    private String state;

    @NotBlank(message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Can only contain alphabets", groups = ValidationCheckForPattern.class)
    @Size(min = 1, max = 30, message = "Too long (Max ({max}))", groups = ValidationCheckForLength.class)
    private String country;

    public CustomerBean() {

    }

    public CustomerBean(ICustomer customer) {
        this.firstName = customer.getFirstName();
        this.middleName = customer.getMiddleName();
        this.lastName = customer.getLastName();
        this.contactNumber = customer.getContactNumber();
        this.emailId = customer.getEmailId();
        this.addressLine1 = customer.getAddressLine1();
        this.addressLine2 = customer.getAddressLine2();
        this.city = customer.getCity();
        this.pincode = customer.getPincode();
        this.state = customer.getState();
        this.country = customer.getCountry();
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

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getAddressLine1() {
        return addressLine1;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public String getAddressLine2() {
        return addressLine2;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getPincode() {
        return pincode;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
