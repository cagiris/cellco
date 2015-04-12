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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cagiris.coho.service.api.ITeam;

/**
 *
 * @author: ssnk
 */
//read only as no update api exit as of now
@Cache(usage = CacheConcurrencyStrategy.READ_ONLY)
@Entity
public class TeamEntity extends BaseEntity implements ITeam {

    private Long teamId;
    private String teamName;
    private String teamDescription;
    private TeamEntity parentTeamEntity;
    private OrganizationEntity organizationEntity;
    private Boolean isDefault;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Override
    public Long getTeamId() {
        return this.teamId;
    }

    @Column(unique = true)
    @Override
    public String getTeamName() {
        return this.teamName;
    }

    @Override
    public String getTeamDescription() {
        return this.teamDescription;
    }

    @Transient
    @Override
    public Long getOrganizationId() {
        if (organizationEntity != null) {
            return organizationEntity.getOrganizationId();
        }
        return null;
    }

    @Transient
    @Override
    public Long getParentTeamId() {
        if (parentTeamEntity != null) {
            return parentTeamEntity.getTeamId();
        }
        return null;
    }

    @ManyToOne
    @JoinColumn(name = "parent_team_id")
    public TeamEntity getParentTeamEntity() {
        return parentTeamEntity;
    }

    public void setParentTeamEntity(TeamEntity parentTeamEntity) {
        this.parentTeamEntity = parentTeamEntity;
    }

    @ManyToOne
    @JoinColumn(name = "organization_id")
    public OrganizationEntity getOrganizationEntity() {
        return organizationEntity;
    }

    public void setOrganizationEntity(OrganizationEntity organizationEntity) {
        this.organizationEntity = organizationEntity;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public void setTeamDescription(String teamDescription) {
        this.teamDescription = teamDescription;
    }

    public Boolean getIsDefault() {
        return isDefault;
    }

    public void setIsDefault(Boolean isDefault) {
        this.isDefault = isDefault;
    }

}
