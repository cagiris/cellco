/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import com.cagiris.coho.service.api.IOrganization;

/**
 *
 * @author: ssnk
 */
@Entity(name = "organizations")
public class OrganizationEntity extends BaseEntity implements IOrganization {

	private Long organizationId;

	private String organizationName;

	private String organizationDescription;

	@Id
	@GeneratedValue
	@Override
	public Long getOrganizationId() {
		return this.organizationId;
	}

	@Override
	public String getOrganizationName() {
		return this.organizationName;
	}

	@Override
	public String getOrganizationDescription() {
		return this.organizationDescription;
	}

	public void setOrganizationId(Long organizationId) {
		this.organizationId = organizationId;
	}

	public void setOrganizationName(String organizationName) {
		this.organizationName = organizationName;
	}

	public void setOrganizationDescription(String organizationDescription) {
		this.organizationDescription = organizationDescription;
	}

}