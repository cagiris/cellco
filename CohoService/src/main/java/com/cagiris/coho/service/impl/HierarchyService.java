/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.impl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cagiris.coho.service.api.AuthenicationPolicy;
import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.IOrganization;
import com.cagiris.coho.service.api.ITeam;
import com.cagiris.coho.service.api.ITeamUser;
import com.cagiris.coho.service.api.UserRole;
import com.cagiris.coho.service.db.api.DatabaseManagerException;
import com.cagiris.coho.service.db.api.IDatabaseManager;
import com.cagiris.coho.service.entity.OrganizationEntity;
import com.cagiris.coho.service.entity.TeamEntity;
import com.cagiris.coho.service.entity.TeamUserEntity;
import com.cagiris.coho.service.entity.UserEntity;
import com.cagiris.coho.service.exception.HierarchyServiceException;

/**
 *
 * @author: ssnk
 */

public class HierarchyService implements IHierarchyService {

	private static final Logger logger = LoggerFactory
			.getLogger(HierarchyService.class);

	private IDatabaseManager databaseManager;

	public HierarchyService(IDatabaseManager databaseManager) {
		this.databaseManager = databaseManager;
	}

	@Override
	public ITeam addTeam(Long organizationId, Long parentTeamId,
			String teamName, String teamDescription)
			throws HierarchyServiceException {
		TeamEntity teamEntity = new TeamEntity();
		teamEntity.setTeamName(teamName);
		teamEntity.setTeamDescription(teamDescription);
		teamEntity.setParentTeamEntity(null);
		OrganizationEntity organizationEntity;
		try {
			organizationEntity = databaseManager.get(OrganizationEntity.class,
					organizationId);
			if (parentTeamId != null) {
				TeamEntity parentTeamEntity = databaseManager.get(
						TeamEntity.class, parentTeamId);
				teamEntity.setParentTeamEntity(parentTeamEntity);
			}
			teamEntity.setOrganizationEntity(organizationEntity);
			databaseManager.save(teamEntity);
		} catch (DatabaseManagerException e) {
			logger.error("Error while adding team:{}", e.getMessage(), e);
			throw new HierarchyServiceException(e);
		}
		return teamEntity;
	}

	@Override
	public void deleteTeam(Long teamId) throws HierarchyServiceException {
		try {
			TeamEntity teamEntity = databaseManager.get(TeamEntity.class,
					teamId);
			databaseManager.delete(teamEntity);
		} catch (DatabaseManagerException e) {
			logger.error("Error while deleting team:{}", e.getMessage(), e);
			throw new HierarchyServiceException(e);
		}
	}

	@Override
	public ITeam getTeam(Long teamId) throws HierarchyServiceException {
		return null;
	}

	@Override
	public List<ITeam> getAllTeams(Long organizationId)
			throws HierarchyServiceException {
		return null;
	}

	@Override
	public List<ITeam> getAllSubTeams(Long parentTeamId)
			throws HierarchyServiceException {
		return null;
	}

	@Override
	public IOrganization getOrganizationInfo(Long organizationId)
			throws HierarchyServiceException {
		return null;
	}

	@Override
	public ITeamUser addUserToTeam(Long teamId, String userId, String userName,
			String authToken, UserRole userRole,
			AuthenicationPolicy authenicationPolicy)
			throws HierarchyServiceException {
		TeamUserEntity userEntity = new TeamUserEntity();
		userEntity.setAuthPolicy(authenicationPolicy);
		userEntity.setUserId(userId);
		userEntity.setAuthToken(authToken);
		userEntity.setUserRole(userRole);
		userEntity.setUserName(userName);
		try {
			TeamEntity teamEntity = databaseManager.get(TeamEntity.class,
					teamId);
			userEntity.setTeamEntity(teamEntity);
			databaseManager.save(userEntity);
			return userEntity;
		} catch (DatabaseManagerException e) {
			logger.error("Error while adding team user:{}", e.getMessage(), e);
			throw new HierarchyServiceException(e);
		}
	}

	@Override
	public ITeamUser assignUserToTeam(Long teamId, String userId)
			throws HierarchyServiceException {
		
		UserEntity userEntity;
		try {
			userEntity = databaseManager.get(UserEntity.class, userId);
		} catch (DatabaseManagerException e1) {
			logger.error("Error while fetching user:{}", e1.getMessage(), e1);
			throw new HierarchyServiceException(e1);
		}
		TeamUserEntity teamUserEntity = new TeamUserEntity();
		teamUserEntity.setAuthPolicy(userEntity.getAuthPolicy());
		teamUserEntity.setUserId(userId);
		teamUserEntity.setAuthToken(userEntity.getAuthToken());
		teamUserEntity.setUserRole(userEntity.getUserRole());
		teamUserEntity.setUserName(userEntity.getUserName());
		try {
			TeamEntity teamEntity = databaseManager.get(TeamEntity.class,
					teamId);
			teamUserEntity.setTeamEntity(teamEntity);
			databaseManager.save(teamUserEntity);
			return teamUserEntity;
		} catch (DatabaseManagerException e) {
			logger.error("Error while adding team user:{}", e.getMessage(), e);
			throw new HierarchyServiceException(e);
		}
	}

	@Override
	public void removeUserFromTeam(Long teamId, String userId)
			throws HierarchyServiceException {
	}

	@Override
	public List<ITeamUser> getAllUsersForTeam(Long teamId)
			throws HierarchyServiceException {
		return null;
	}

	@Override
	public List<ITeamUser> getAllUsersForTeamByRole(Long teamId,
			UserRole userRole) throws HierarchyServiceException {
//		QUserEntity qUserEntity = QUserEntity.userEntity;
//		HibernateQuery hibernateQuery = new HibernateQuery();
//		hibernateQuery.

		return null;
	}

	@Override
	public void deleteUser(String userId) throws HierarchyServiceException {
	}

	@Override
	public List<ITeam> getTeamsForUser(String userId)
			throws HierarchyServiceException {
		return null;
	}

	@Override
	public IOrganization addOrganization(String organizationName,
			String organizationDescription) throws HierarchyServiceException {
		OrganizationEntity organizationEntity = new OrganizationEntity();
		organizationEntity.setOrganizationName(organizationName);
		organizationEntity.setOrganizationDescription(organizationDescription);
		try {
			databaseManager.save(organizationEntity);
		} catch (DatabaseManagerException e) {
			logger.error("Error while adding organization:{}", e.getMessage(),
					e);
			throw new HierarchyServiceException(e);
		}
		return organizationEntity;
	}

}
