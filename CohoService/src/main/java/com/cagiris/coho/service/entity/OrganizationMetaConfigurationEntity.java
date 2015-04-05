/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import com.cagiris.coho.service.api.AuthenicationPolicy;
import com.cagiris.coho.service.api.IOrganizationMetaConfiguration;
import com.cagiris.coho.service.api.UserIdGenerationPolicy;
import com.cagiris.coho.service.api.UserRole;

/**
 *
 * @author: ssnk
 */

@Entity
public class OrganizationMetaConfigurationEntity implements IOrganizationMetaConfiguration {

    private Long organizationId;

    private OrganizationEntity organizationEntity;

    private UserIdGenerationPolicy UserIdGenerationPolicy;

    private AuthenicationPolicy defaultAuthenticationPolicy;

    private String orgPrefix;

    private Integer maxUsersAllowed;

    private Integer maxTeamsAllowed;

    private Integer hierarchyDepth;

    private Set<UserRole> availableUserRoles;

    @Override
    public String getOrgPrefix() {
        return orgPrefix;
    }

    public void setOrgPrefix(String orgPrefix) {
        this.orgPrefix = orgPrefix;
    }

    @Override
    public Integer getMaxTeamsAllowed() {
        return maxTeamsAllowed;
    }

    public void setMaxTeamsAllowed(Integer maxTeamsAllowed) {
        this.maxTeamsAllowed = maxTeamsAllowed;
    }

    public void setUserIdGenerationPolicy(UserIdGenerationPolicy userIdGenerationPolicy) {
        UserIdGenerationPolicy = userIdGenerationPolicy;
    }

    public void setDefaultAuthenticationPolicy(AuthenicationPolicy defaultAuthenticationPolicy) {
        this.defaultAuthenticationPolicy = defaultAuthenticationPolicy;
    }

    public void setMaxUsersAllowed(Integer maxUsersAllowed) {
        this.maxUsersAllowed = maxUsersAllowed;
    }

    public void setHierarchyDepth(Integer hierarchyDepth) {
        this.hierarchyDepth = hierarchyDepth;
    }

    @Id
    @GeneratedValue(generator = "SharedOrganizationKeyGen")
    @GenericGenerator(name = "SharedOrganizationKeyGen", strategy = "foreign",
                      parameters = @Parameter(name = "property", value = "organizationEntity"))
    @Column(unique = true, nullable = false)
    @Override
    public Long getOrganizationId() {
        return this.organizationId;
    }

    @Enumerated(EnumType.STRING)
    @Override
    public UserIdGenerationPolicy getUserIdGenerationPolicy() {
        return UserIdGenerationPolicy;
    }

    @Enumerated(EnumType.STRING)
    @Override
    public AuthenicationPolicy getDefaultAuthenticationPolicy() {
        return defaultAuthenticationPolicy;
    }

    @Override
    public Integer getMaxUsersAllowed() {
        return maxUsersAllowed;
    }

    @Override
    public Integer getHierarchyDepth() {
        return hierarchyDepth;
    }

    @PrimaryKeyJoinColumn
    @OneToOne(fetch = FetchType.EAGER)
    public OrganizationEntity getOrganizationEntity() {
        return organizationEntity;
    }

    public void setOrganizationEntity(OrganizationEntity organizationEntity) {
        this.organizationEntity = organizationEntity;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    @CollectionTable
    @MapKeyEnumerated(EnumType.STRING)
    @ElementCollection(targetClass = UserRole.class, fetch = FetchType.EAGER)
    @Override
    public Set<UserRole> getAvailableUserRoles() {
        return availableUserRoles;
    }

    public void setAvailableUserRoles(Set<UserRole> availableUserRoles) {
        this.availableUserRoles = availableUserRoles;
    }

}
