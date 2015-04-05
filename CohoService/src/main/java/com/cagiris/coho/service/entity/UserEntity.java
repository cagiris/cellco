/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.cagiris.coho.service.api.AuthenicationPolicy;
import com.cagiris.coho.service.api.IUser;
import com.cagiris.coho.service.api.UserRole;

/**
 *
 * @author: ssnk
 */
@Entity
public class UserEntity extends BaseEntity implements IUser {

    private String userId;

    private String userName;

    private String authToken;

    private AuthenicationPolicy authPolicy;

    private UserRole userRole;

    private OrganizationEntity organizationEntity;

    @Enumerated(EnumType.STRING)
    @Override
    public AuthenicationPolicy getAuthPolicy() {
        return this.authPolicy;
    }

    @Override
    public String getAuthToken() {
        return this.authToken;
    }

    @Id
    @Override
    @Column(unique = true)
    public String getUserId() {
        return this.userId;
    }

    @Override
    public String getUserName() {
        return this.userName;
    }

    public void setAuthPolicy(AuthenicationPolicy authPolicy) {
        this.authPolicy = authPolicy;
    }

    public void setAuthToken(String authToken) {
        this.authToken = authToken;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Enumerated(EnumType.STRING)
    @Override
    public UserRole getUserRole() {
        return this.userRole;
    }

    public void setUserRole(UserRole userRole) {
        this.userRole = userRole;
    }

    @Transient
    @Override
    public Long getOrganizationId() {
        if (organizationEntity != null) {
            return organizationEntity.getOrganizationId();
        }
        return null;
    }

    @ManyToOne(fetch = FetchType.EAGER)
    public OrganizationEntity getOrganizationEntity() {
        return organizationEntity;
    }

    public void setOrganizationEntity(OrganizationEntity organizationEntity) {
        this.organizationEntity = organizationEntity;
    }
}
