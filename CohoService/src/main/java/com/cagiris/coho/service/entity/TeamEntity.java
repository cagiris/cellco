/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.cagiris.coho.service.api.ITeam;

/**
 *
 * @author: ssnk
 */
@Entity(name = "teams")
public class TeamEntity extends BaseEntity implements ITeam {

	private Long teamId;
	private String teamName;
	private String teamDescription;
	private TeamEntity parentTeamEntity;
	private OrganizationEntity organizationEntity;

	@Id
	@GeneratedValue
	@Override
	public Long getTeamId() {
		return this.teamId;
	}

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

}
