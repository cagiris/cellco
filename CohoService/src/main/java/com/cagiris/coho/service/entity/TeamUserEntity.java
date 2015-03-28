/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import com.cagiris.coho.service.api.ITeamUser;

/**
 *
 * @author: ssnk
 */

@Entity
public class TeamUserEntity extends UserEntity implements ITeamUser {

	private TeamEntity teamEntity;

	@Transient
	@Override
	public Long getTeamId() {
		if (teamEntity != null) {
			return teamEntity.getTeamId();
		}
		return null;
	}

	@JoinColumn(name = "team_id")
	@ManyToOne
	public TeamEntity getTeamEntity() {
		return teamEntity;
	}

	public void setTeamEntity(TeamEntity teamEntity) {
		this.teamEntity = teamEntity;
	}

}
