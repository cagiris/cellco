/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cagiris.coho.service.api.IOrganization;

/**
 *
 * @author: ssnk
 */
// read only as no update api exit as of now
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
public class OrganizationEntity extends BaseEntity implements IOrganization {

    private Long organizationId;

    private String organizationName;

    private String organizationDescription;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getOrganizationId() {
        return this.organizationId;
    }

    @Override
    @Column(unique = true)
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
