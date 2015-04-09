/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

import java.util.Date;
import java.util.List;
import java.util.Set;

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

    List<? extends IOrganization> getAllOrganizationInfo() throws HierarchyServiceException;

    ITeamUser addUserToTeam(Long teamId, String userId, String userName, String authToken, UserRole userRole,
            AuthenicationPolicy authenicationPolicy) throws HierarchyServiceException;

    IUser updateUser(String userId, String userName, String authToken, UserRole userRole)
            throws HierarchyServiceException;

    ITeamUser assignUserToTeam(Long teamId, String userId) throws HierarchyServiceException;

    void removeUserFromTeam(Long teamId, String userId) throws HierarchyServiceException;

    List<? extends ITeamUser> getAllUsersForTeam(Long teamId) throws HierarchyServiceException;

    List<? extends ITeamUser> getAllUsersForTeamByRole(Long teamId, UserRole userRole) throws HierarchyServiceException;

    void deleteUser(String userId) throws HierarchyServiceException;

    List<? extends ITeam> getTeamsForUser(String userId) throws HierarchyServiceException;

    IUser getUser(String userId) throws HierarchyServiceException, ResourceNotFoundException;

    IOrganizationMetaConfiguration getOrganizationMetaConfiguration(Long organizationId)
            throws HierarchyServiceException, ResourceNotFoundException;

    IUserProfile updateUserProfile(String userId, String firstName, String lastName, Date dateOfBirth, String gender,
            String mobileNumber, String emailId, String addressLine1, String addressLine2, String city, String pincode,
            String state, String country, Date joinedOn, Date leftOn, String designation)
            throws HierarchyServiceException;

    IUserProfile getUserProfile(String userId) throws HierarchyServiceException, ResourceNotFoundException;

    Set<UserRole> getAvailableUserRoles(Long organizationId) throws HierarchyServiceException;

    IOrganization getDefaultOrganization() throws HierarchyServiceException;

    ITeam getDefaultTeam(Long organizationId) throws HierarchyServiceException;

    List<? extends IUser> getReportingUsers(String userId) throws HierarchyServiceException;

    List<? extends ITeamUser> getAllTeamUserByUserId(String userId) throws HierarchyServiceException;

    ITeamUser getTeamUserByUserId(Long teamId, String userId) throws HierarchyServiceException,
            ResourceNotFoundException;

    List<? extends IUserProfile> getAllUserProfiles(Long organizationId) throws HierarchyServiceException;
}
