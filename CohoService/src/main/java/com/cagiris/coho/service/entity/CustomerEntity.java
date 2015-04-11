/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.cagiris.coho.service.api.ICustomer;

/**
 *
 * @author: Ashish Jindal
 */
@Entity
public class CustomerEntity extends BaseEntity implements ICustomer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;

    private String firstName;

    private String middleName;

    private String lastName;

    private String emailId;

    private String contactNumber;

    private String billingAddress;

    public CustomerEntity() {

    }

    public CustomerEntity(Long customerId, String firstName, String middleName, String lastName, String emailId,
            String contactNumber, String billingAddress) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.emailId = emailId;
        this.contactNumber = contactNumber;
        this.billingAddress = billingAddress;
    }

    @Override
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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

    @Override
    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    @Override
    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    @Override
    public String getBillingAddress() {
        return billingAddress;
    }

    public void setBillingAddress(String billingAddress) {
        this.billingAddress = billingAddress;
    }
}
