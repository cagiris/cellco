/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

import java.util.List;

import com.cagiris.coho.service.exception.HierarchyServiceException;

/**
 * This service provide apis to manage the organization hierarchy. The root of
 * hierarchy is Organization. An Organization can have multiple teams, each team
 * can has sub teams and so on upto any levels. User can be a part of multiple
 * teams.
 * 
 * @author: ssnk
 */

public interface IHierarchyService {

	/**
	 * The parentTeamId will be null for top level teams.
	 */
	ITeam addTeam(Long organizationId, Long parentTeamId, String teamName, String teamDescription)
			throws HierarchyServiceException;

	void deleteTeam(Long teamId) throws HierarchyServiceException;

	ITeam getTeam(Long teamId) throws HierarchyServiceException;

	List<ITeam> getAllTeams(Long organizationId) throws HierarchyServiceException;

	List<ITeam> getAllSubTeams(Long parentTeamId) throws HierarchyServiceException;

	IOrganization getOrganizationInfo(Long organizationId) throws HierarchyServiceException;

	ITeamUser addUserToTeam(Long teamId, String userId, String userName) throws HierarchyServiceException;

	ITeamUser assignUserToTeam(Long teamId, String userId) throws HierarchyServiceException;

	void removeUserFromTeam(Long teamId, String userId) throws HierarchyServiceException;

	List<ITeamUser> getAllUsersForTeam(Long teamId) throws HierarchyServiceException;

	List<ITeamUser> getAllUsersForTeamByRole(Long teamId, UserRole userRole) throws HierarchyServiceException;

	void deleteUser(String userId) throws HierarchyServiceException;

	List<ITeam> getTeamsForUser(String userId) throws HierarchyServiceException;

}
