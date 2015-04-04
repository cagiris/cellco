/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

import java.util.List;

import com.cagiris.coho.service.exception.HierarchyServiceException;
import com.cagiris.coho.service.exception.ResourceNotFoundException;

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

    ITeam getTeam(Long teamId) throws HierarchyServiceException, ResourceNotFoundException;

    List<? extends ITeam> getAllTeams(Long organizationId) throws HierarchyServiceException;

    List<? extends ITeam> getAllSubTeams(Long parentTeamId) throws HierarchyServiceException;

    IOrganization addOrganization(String organizationName, String organizationDesription)
            throws HierarchyServiceException;

    IOrganization getOrganizationInfo(Long organizationId) throws HierarchyServiceException, ResourceNotFoundException;
    
    List<? extends IOrganization> getAllOrganizationInfo() throws HierarchyServiceException, ResourceNotFoundException;

    ITeamUser addUserToTeam(Long teamId, String userId, String userName, String authToken, UserRole userRole,
            AuthenicationPolicy authenicationPolicy) throws HierarchyServiceException;

    ITeamUser assignUserToTeam(Long teamId, String userId) throws HierarchyServiceException;

    void removeUserFromTeam(Long teamId, String userId) throws HierarchyServiceException;

    List<? extends ITeamUser> getAllUsersForTeam(Long teamId) throws HierarchyServiceException;

    List<? extends ITeamUser> getAllUsersForTeamByRole(Long teamId, UserRole userRole) throws HierarchyServiceException;

    void deleteUser(String userId) throws HierarchyServiceException;

    List<? extends ITeam> getTeamsForUser(String userId) throws HierarchyServiceException;

    IUser getUser(String userId) throws HierarchyServiceException, ResourceNotFoundException;

}
