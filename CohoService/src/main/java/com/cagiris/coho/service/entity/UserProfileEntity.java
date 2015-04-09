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

    @Id
    @GeneratedValue(generator = "SharedUserKeyGen")
    @GenericGenerator(name = "SharedUserKeyGen", strategy = "foreign", parameters = @Parameter(name = "property",
                                                                                               value = "userEntity"))
    @Column(unique = true, nullable = false)
    private String userId;
    
	private String firstName;

	private String lastName;

	private Date dateOfBirth;

	private String gender;

	private String mobileNumber;

	private String emailId;

	private String addressLine1;

	private String addressLine2;

	private String city;

	private String pincode;

	private String state;
	
	private String country;

	private Date joinedOn;

	private Date leftOn;

	private String designation;

    @PrimaryKeyJoinColumn
    @OneToOne(fetch = FetchType.EAGER)
    private UserEntity userEntity;

    public UserProfileEntity() {
    }
    
    public UserProfileEntity(String userId, String firstName, String lastName,
			Date dateOfBirth, String gender, String mobileNumber,
			String emailId, String addressLine1, String addressLine2,
			String city, String pincode, String state, String country,
			Date joinedOn, Date leftOn, String designation) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.dateOfBirth = dateOfBirth;
		this.gender = gender;
		this.mobileNumber = mobileNumber;
		this.emailId = emailId;
		this.addressLine1 = addressLine1;
		this.addressLine2 = addressLine2;
		this.city = city;
		this.pincode = pincode;
		this.state = state;
		this.country = country;
		this.joinedOn = joinedOn;
		this.leftOn = leftOn;
		this.designation = designation;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	/* (non-Javadoc)
	 * @see com.cagiris.coho.service.entity.IUserProfile1#getFirstName()
	 */
	@Override
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/* (non-Javadoc)
	 * @see com.cagiris.coho.service.entity.IUserProfile1#getLastName()
	 */
	@Override
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/* (non-Javadoc)
	 * @see com.cagiris.coho.service.entity.IUserProfile1#getDateOfBirth()
	 */
	@Override
	public Date getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	/* (non-Javadoc)
	 * @see com.cagiris.coho.service.entity.IUserProfile1#getGender()
	 */
	@Override
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	/* (non-Javadoc)
	 * @see com.cagiris.coho.service.entity.IUserProfile1#getMobileNumber()
	 */
	@Override
	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	/* (non-Javadoc)
	 * @see com.cagiris.coho.service.entity.IUserProfile1#getEmailId()
	 */
	@Override
	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/* (non-Javadoc)
	 * @see com.cagiris.coho.service.entity.IUserProfile1#getAddressLine1()
	 */
	@Override
	public String getAddressLine1() {
		return addressLine1;
	}

	public void setAddressLine1(String addressLine1) {
		this.addressLine1 = addressLine1;
	}

	/* (non-Javadoc)
	 * @see com.cagiris.coho.service.entity.IUserProfile1#getAddressLine2()
	 */
	@Override
	public String getAddressLine2() {
		return addressLine2;
	}

	public void setAddressLine2(String addressLine2) {
		this.addressLine2 = addressLine2;
	}

	/* (non-Javadoc)
	 * @see com.cagiris.coho.service.entity.IUserProfile1#getCity()
	 */
	@Override
	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	/* (non-Javadoc)
	 * @see com.cagiris.coho.service.entity.IUserProfile1#getPincode()
	 */
	@Override
	public String getPincode() {
		return pincode;
	}

	public void setPincode(String pincode) {
		this.pincode = pincode;
	}

	/* (non-Javadoc)
	 * @see com.cagiris.coho.service.entity.IUserProfile1#getState()
	 */
	@Override
	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	/* (non-Javadoc)
	 * @see com.cagiris.coho.service.entity.IUserProfile1#getCountry()
	 */
	@Override
	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	/* (non-Javadoc)
	 * @see com.cagiris.coho.service.entity.IUserProfile1#getJoinedOn()
	 */
	@Override
	public Date getJoinedOn() {
		return joinedOn;
	}

	public void setJoinedOn(Date joinedOn) {
		this.joinedOn = joinedOn;
	}

	/* (non-Javadoc)
	 * @see com.cagiris.coho.service.entity.IUserProfile1#getLeftOn()
	 */
	@Override
	public Date getLeftOn() {
		return leftOn;
	}

	public void setLeftOn(Date leftOn) {
		this.leftOn = leftOn;
	}

	/* (non-Javadoc)
	 * @see com.cagiris.coho.service.entity.IUserProfile1#getDesignation()
	 */
	@Override
	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}
}
