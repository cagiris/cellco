/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import com.cagiris.coho.service.api.ITeamUser;

/**
 *
 * @author: ssnk
 */
@Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
public class TeamUserEntity extends BaseEntity implements ITeamUser {

    private TeamEntity teamEntity;

    private Long teamUserId;

    private UserEntity userEntity;

    @Transient
    @Override
    public Long getTeamId() {
        if (teamEntity != null) {
            return teamEntity.getTeamId();
        }
        return null;
    }

    @JoinColumn(name = "team_id")
    @ManyToOne(fetch = FetchType.EAGER)
    public TeamEntity getTeamEntity() {
        return teamEntity;
    }

    public void setTeamEntity(TeamEntity teamEntity) {
        this.teamEntity = teamEntity;
    }

    @Id
    @GeneratedValue
    @Override
    public Long getTeamUserId() {
        return teamUserId;
    }

    public void setTeamUserId(Long teamUserId) {
        this.teamUserId = teamUserId;
    }

    @Transient
    @Override
    public String getUserId() {
        if (getUserEntity() != null) {
            return getUserEntity().getUserId();
        }
        return null;
    }

    @JoinColumn(name = "user_id")
    @ManyToOne(fetch = FetchType.EAGER)
    public UserEntity getUserEntity() {
        return userEntity;
    }

    public void setUserEntity(UserEntity userEntity) {
        this.userEntity = userEntity;
    }

    @Transient
    @Override
    public String getUserRole() {
        if (userEntity != null && userEntity.getUserRole() != null) {
            return userEntity.getUserRole().toString();
        }
        return null;
    }

}
