/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.cagiris.coho.service.api.IUserProfile;

/**
 *
 * @author: ssnk
 */

@Entity
public class UserProfileEntity implements IUserProfile {

    private String userId;

    private String address;

    private String city;

    private String phone;

    private Date dateOfBirth;

    private String email;

    private String userName;

    private String postalCode;

    private Date workStartDate;

    private Date workEndDate;

    private UserEntity userEntity;

    public UserProfileEntity() {
    }

    public UserProfileEntity(String userId, String userName, String email, String phone, String address, String city,
            String postalCode, Date dateOfBirth, Date workStartDate, Date workEndDate) {
        this.userId = userId;
        this.setUserName(userName);
        this.setEmail(email);
        this.setPhone(phone);
        this.setAddress(address);
        this.setCity(city);
        this.setPostalCode(postalCode);
        this.setDateOfBirth(dateOfBirth);
        this.setWorkStartDate(workStartDate);
        this.setWorkEndDate(workEndDate);
    }

    @Id
    @GeneratedValue(generator = "SharedUserKeyGen")
    @GenericGenerator(name = "SharedUserKeyGen", strategy = "foreign", parameters = @Parameter(name = "property",
                                                                                               value = "userEntity"))
    @Column(unique = true, nullable = false)
    @Override
    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    @Override
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public Date getWorkStartDate() {
        return workStartDate;
    }

    public void setWorkStartDate(Date workStartDate) {
        this.workStartDate = workStartDate;
    }

    @Override
    public Date getWorkEndDate() {
        return workEndDate;
    }

    public void setWorkEndDate(Date workEndDate) {
        this.workEndDate = workEndDate;
    }

    @PrimaryKeyJoinColumn
    @OneToOne(fetch = FetchType.EAGER)
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Column(nullable = false)
    @Override
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
