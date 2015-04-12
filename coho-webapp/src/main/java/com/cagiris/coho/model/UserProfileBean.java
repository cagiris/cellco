/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.model;

import java.io.Serializable;
import java.util.Date;

import javax.annotation.Nonnull;
import javax.validation.GroupSequence;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.format.annotation.DateTimeFormat;

import com.cagiris.coho.service.api.IUserProfile;

/**
 * @author Ashish Jindal
 *
 */
@GroupSequence({ValidationCheckForEmpty.class, ValidationCheckForLength.class, ValidationCheckForPattern.class,
        UserProfileBean.class})
public class UserProfileBean extends AbstractBean implements ICRUDBean {

    private String userId;

    @NotBlank(message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Can only contain alphabets", groups = ValidationCheckForPattern.class)
    @Size(min = 1, max = 50, message = "Too long (Max ({max}))", groups = ValidationCheckForLength.class)
    private String firstName;

    @Pattern(regexp = "^[a-zA-Z]*$", message = "Can only contain alphabets", groups = ValidationCheckForPattern.class)
    @Size(min = 0, max = 50, message = "Too long (Max ({max}))", groups = ValidationCheckForLength.class)
    private String lastName;

    @Nonnull
    @DateTimeFormat(pattern = Constants.DATE_FORMAT)
    private Date dateOfBirth;

    @NotBlank(message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Invalid Gender", groups = ValidationCheckForPattern.class)
    @Size(min = 4, max = 6, message = "Invalid gender", groups = ValidationCheckForLength.class)
    private String gender;

    @NotBlank(message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
    @Pattern(regexp = "^[0-9]*$", message = "Invalid mobile number", groups = ValidationCheckForPattern.class)
    @Size(min = 10, max = 10, message = "Too long (Max ({max}))", groups = ValidationCheckForLength.class)
    private String mobileNumber;

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
    @Size(max = 6, message = "Too long (max ({max} characters))", groups = ValidationCheckForLength.class)
    private String pincode;

    @NotBlank(message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
    @Pattern(regexp = "^[a-zA-Z ]*$", message = "Can only contain alphabets", groups = ValidationCheckForPattern.class)
    @Size(min = 1, max = 30, message = "Too long (Max ({max}))", groups = ValidationCheckForLength.class)
    private String state;

    @NotBlank(message = "Can't be left empty", groups = ValidationCheckForEmpty.class)
    @Pattern(regexp = "^[a-zA-Z ] *$", message = "Can only contain alphabets", groups = ValidationCheckForPattern.class)
    @Size(min = 1, max = 30, message = "Too long (Max ({max}))", groups = ValidationCheckForLength.class)
    private String country;

    @Nonnull
    @DateTimeFormat(pattern = Constants.DATE_FORMAT)
    private Date joinedOn;

    @DateTimeFormat(pattern = Constants.DATE_FORMAT)
    private Date leftOn;

    @Pattern(regexp = "^[a-zA-Z0-9 ]*$", message = "Invalid designation", groups = ValidationCheckForPattern.class)
    @Size(min = 0, max = 50, message = "Too long (Max ({max}))", groups = ValidationCheckForLength.class)
    private String designation;

    public UserProfileBean() {

    }

    public UserProfileBean(IUserProfile userProfile) {
        this.userId = userProfile.getUserId();
        this.firstName = userProfile.getFirstName();
        this.lastName = userProfile.getLastName();
        this.dateOfBirth = userProfile.getDateOfBirth();
        this.gender = userProfile.getGender();
        this.mobileNumber = userProfile.getMobileNumber();
        this.emailId = userProfile.getEmailId();
        this.addressLine1 = userProfile.getAddressLine1();
        this.addressLine2 = userProfile.getAddressLine2();
        this.city = userProfile.getCity();
        this.pincode = userProfile.getPincode();
        this.state = userProfile.getState();
        this.country = userProfile.getCountry();
        this.joinedOn = userProfile.getJoinedOn();
        this.leftOn = userProfile.getLeftOn();
        this.designation = userProfile.getDesignation();
    }

    @Override
    public Serializable getEntityId() {
        return userId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getMobileNumber() {
        return mobileNumber;
    }

    public void setMobileNumber(String mobileNumber) {
        this.mobileNumber = mobileNumber;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Date getJoinedOn() {
        return joinedOn;
    }

    public void setJoinedOn(Date joinedOn) {
        this.joinedOn = joinedOn;
    }

    public Date getLeftOn() {
        return leftOn;
    }

    public void setLeftOn(Date leftOn) {
        this.leftOn = leftOn;
    }

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
