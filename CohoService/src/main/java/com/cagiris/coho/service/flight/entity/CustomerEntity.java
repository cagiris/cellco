/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.flight.entity;

import java.math.BigInteger;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.cagiris.coho.service.entity.BaseEntity;
import com.cagiris.coho.service.flight.api.ICustomer;

/**
 *
 * @author: Ashish Jindal
 */
@Entity
public class CustomerEntity extends BaseEntity implements ICustomer {

    private BigInteger customerId;

    private String firstName;

    private String middleName;

    private String lastName;

    private String emailId;

    private String contactNumber;

    private String addressLine1;

    private String addressLine2;

    private String city;

    private String pincode;

    private String state;

    private String country;

    public CustomerEntity() {

    }

    public CustomerEntity(String firstName, String middleName, String lastName, String emailId, String contactNumber,
            String addressLine1, String addressLine2, String city, String country, String pincode, String state) {
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.contactNumber = contactNumber;
        this.addressLine1 = addressLine1;
        this.addressLine2 = addressLine2;
        this.city = city;
        this.pincode = pincode;
        this.state = state;
        this.country = country;
    }

    @Override
    public String getAddressLine1() {
        return addressLine1;
    }

    @Override
    public String getAddressLine2() {
        return addressLine2;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public String getContactNumber() {
        return contactNumber;
    }

    @Override
    public String getCountry() {
        return country;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public BigInteger getCustomerId() {
        return customerId;
    }

    @Override
    public String getEmailId() {
        return emailId;
    }

    @Override
    public String getFirstName() {
        return firstName;
    }

    @Override
    public String getLastName() {
        return lastName;
    }

    @Override
    public String getMiddleName() {
        return middleName;
    }

    @Override
    public String getPincode() {
        return pincode;
    }

    @Override
    public String getState() {
        return state;
    }

    public void setAddressLine1(String addressLine1) {
        this.addressLine1 = addressLine1;
    }

    public void setAddressLine2(String addressLine2) {
        this.addressLine2 = addressLine2;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCustomerId(BigInteger customerId) {
        this.customerId = customerId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public void setPincode(String pincode) {
        this.pincode = pincode;
    }

    public void setState(String state) {
        this.state = state;
    }
}
