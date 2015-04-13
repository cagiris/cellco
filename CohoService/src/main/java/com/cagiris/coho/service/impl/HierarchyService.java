/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.impl;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.joda.time.DateTimeConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cagiris.coho.service.api.AuthenicationPolicy;
import com.cagiris.coho.service.api.IAttendenceReportingService;
import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.ILeaveManagementService;
import com.cagiris.coho.service.api.IOrganization;
import com.cagiris.coho.service.api.IOrganizationMetaConfiguration;
import com.cagiris.coho.service.api.ITeam;
import com.cagiris.coho.service.api.ITeamUser;
import com.cagiris.coho.service.api.IUser;
import com.cagiris.coho.service.api.IUserProfile;
import com.cagiris.coho.service.api.LeaveAccumulationPolicy;
import com.cagiris.coho.service.api.LeaveType;
import com.cagiris.coho.service.api.UserIdGenerationPolicy;
import com.cagiris.coho.service.api.UserRole;
import com.cagiris.coho.service.db.api.DatabaseManagerException;
import com.cagiris.coho.service.db.api.EntityNotFoundException;
import com.cagiris.coho.service.db.api.IDatabaseManager;
import com.cagiris.coho.service.db.impl.CohoDeleteClause;
import com.cagiris.coho.service.entity.OrganizationEntity;
import com.cagiris.coho.service.entity.OrganizationMetaConfigurationEntity;
import com.cagiris.coho.service.entity.QOrganizationEntity;
import com.cagiris.coho.service.entity.QTeamEntity;
import com.cagiris.coho.service.entity.QTeamUserEntity;
import com.cagiris.coho.service.entity.QUserEntity;
import com.cagiris.coho.service.entity.QUserProfileEntity;
import com.cagiris.coho.service.entity.TeamEntity;
import com.cagiris.coho.service.entity.TeamUserEntity;
import com.cagiris.coho.service.entity.UserEntity;
import com.cagiris.coho.service.entity.UserProfileEntity;
import com.cagiris.coho.service.exception.AttendenceReportingServiceException;
import com.cagiris.coho.service.exception.HierarchyServiceException;
import com.cagiris.coho.service.exception.LeaveManagementServiceException;
import com.cagiris.coho.service.exception.ResourceNotFoundException;
import com.mysema.query.jpa.hibernate.HibernateQuery;

/**
 *
 * @author: ssnk
 */

public class HierarchyService implements IHierarchyService {

    private static final Logger logger = LoggerFactory.getLogger(HierarchyService.class);

    private static final String DEFAULT_TEAM_NAME = "Default Team";

    private static final String DEFAULT_ORG_NAME = "Coho";

    private IDatabaseManager databaseManager;

    public IAttendenceReportingService getAttendenceReportingService() {
        return attendenceReportingService;
    }

    public void setAttendenceReportingService(IAttendenceReportingService attendenceReportingService) {
        this.attendenceReportingService = attendenceReportingService;
    }

    public ILeaveManagementService getLeaveManagementService() {
        return leaveManagementService;
    }

    public void setLeaveManagementService(ILeaveManagementService leaveManagementService) {
        this.leaveManagementService = leaveManagementService;
    }

    private IAttendenceReportingService attendenceReportingService;

    private ILeaveManagementService leaveManagementService;

    public HierarchyService(IDatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public void createDefaultHierarchy() {
        try {
            IOrganization defaultOrganization = addOrganization(DEFAULT_ORG_NAME, "Default Organization");
            ITeam defaultTeam = addTeam(defaultOrganization.getOrganizationId(), null, DEFAULT_TEAM_NAME,
                    "Default team");
            addUserToTeam(defaultTeam.getTeamId(), "agent", "agent", UserRole.AGENT, AuthenicationPolicy.PASSWORD_BASED);
            //            addUserToTeam(defaultTeam.getTeamId(), "supervisor", "supervisor", "supervisor", UserRole.SUPERVISOR,
            //                    AuthenicationPolicy.PASSWORD_BASED);
            addUserToTeam(defaultTeam.getTeamId(), "admin", "admin", UserRole.ADMIN, AuthenicationPolicy.PASSWORD_BASED);
            //            addUserToTeam(defaultTeam.getTeamId(), "root", "root", "root", UserRole.ROOT,
            //                    AuthenicationPolicy.PASSWORD_BASED);
        } catch (HierarchyServiceException e) {
            logger.error("Failed to create default hierarchy, error: {}", e.getMessage());
        }
    }

    @Override
    public ITeam addTeam(Long organizationId, Long parentTeamId, String teamName, String teamDescription)
            throws HierarchyServiceException {
        Date date = new Date();
        TeamEntity teamEntity = new TeamEntity();
        teamEntity.setDateAdded(date);
        teamEntity.setDateModified(date);

        teamEntity.setTeamName(teamName);
        teamEntity.setTeamDescription(teamDescription);
        teamEntity.setParentTeamEntity(null);
        OrganizationEntity organizationEntity;
        try {
            organizationEntity = databaseManager.get(OrganizationEntity.class, organizationId);
            if (parentTeamId != null) {
                TeamEntity parentTeamEntity = databaseManager.get(TeamEntity.class, parentTeamId);
                teamEntity.setParentTeamEntity(parentTeamEntity);
            }
            teamEntity.setOrganizationEntity(organizationEntity);
            databaseManager.save(teamEntity);
        } catch (DatabaseManagerException | EntityNotFoundException e) {
            logger.error("Error while adding team:{}", e.getMessage(), e);
            throw new HierarchyServiceException(e);
        }
        createTeamDependentEntities(teamEntity);
        return teamEntity;
    }

    private void createTeamDependentEntities(TeamEntity teamEntity) throws HierarchyServiceException {
        Calendar shiftStart = new GregorianCalendar();
        shiftStart.set(Calendar.HOUR, 9);
        shiftStart.set(Calendar.MINUTE, 0);

        Calendar shiftEnd = new GregorianCalendar();
        shiftEnd.set(Calendar.HOUR, 18);
        shiftEnd.set(Calendar.MINUTE, 0);
        long defaultShiftBuffer = 3 * 60;
        long minimumGapBetweenShifts = 9 * 60;
        try {
            attendenceReportingService.createTeamShiftDetails(teamEntity.getTeamId(), shiftStart.getTime(),
                    shiftEnd.getTime(), defaultShiftBuffer, minimumGapBetweenShifts, true);
        } catch (AttendenceReportingServiceException e) {
            throw new HierarchyServiceException(e);
        }

    }

    @Override
    public void deleteTeam(Long teamId) throws HierarchyServiceException {
        try {
            TeamEntity teamEntity = databaseManager.get(TeamEntity.class, teamId);
            databaseManager.delete(teamEntity);
        } catch (DatabaseManagerException | EntityNotFoundException e) {
            logger.error("Error while deleting team:{}", e.getMessage(), e);
            throw new HierarchyServiceException(e);
        }
    }

    @Override
    public ITeam getTeam(Long teamId) throws HierarchyServiceException, ResourceNotFoundException {
        try {
            return databaseManager.get(TeamEntity.class, teamId);
        } catch (DatabaseManagerException e) {
            throw new HierarchyServiceException(e);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage(), e);
        }
    }

    @Override
    public List<? extends ITeam> getAllTeams(Long organizationId) throws HierarchyServiceException {
        QTeamEntity qTeamEntity = QTeamEntity.teamEntity;
        HibernateQuery hibernateQuery = new HibernateQuery().from(qTeamEntity).where(
                qTeamEntity.organizationEntity.organizationId.eq(organizationId));
        try {
            return databaseManager.executeQueryAndGetResults(hibernateQuery, qTeamEntity);
        } catch (DatabaseManagerException e) {
            throw new HierarchyServiceException(e);
        }
    }

    @Override
    public List<? extends ITeam> getAllSubTeams(Long parentTeamId) throws HierarchyServiceException {
        QTeamEntity qTeamEntity = QTeamEntity.teamEntity;
        QTeamEntity parentQTeamEntity = qTeamEntity.parentTeamEntity;
        HibernateQuery hibernateQuery = new HibernateQuery().from(qTeamEntity).where(
                parentQTeamEntity.teamId.ne(-1l).and(parentQTeamEntity.teamId.eq(parentTeamId)));
        try {
            return databaseManager.executeQueryAndGetResults(hibernateQuery, qTeamEntity);
        } catch (DatabaseManagerException e) {
            throw new HierarchyServiceException(e);
        }
    }

    @Override
    public IOrganization getOrganizationInfo(Long organizationId) throws HierarchyServiceException,
            ResourceNotFoundException {
        try {
            return databaseManager.get(OrganizationEntity.class, organizationId);
        } catch (DatabaseManagerException e) {
            throw new HierarchyServiceException(e);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage(), e);
        }
    }

    @Override
    public ITeamUser addUserToTeam(Long teamId, String userId, String authToken, UserRole userRole,
            AuthenicationPolicy authenicationPolicy) throws HierarchyServiceException {
        try {
            IUser user = getUser(userId);
            if (user != null) {
                throw new HierarchyServiceException("User with Id: " + userId + " already exists");
            }
        } catch (ResourceNotFoundException e1) {
        }
        Date currentTime = new Date();
        UserEntity userEntity = new UserEntity();
        userEntity.setAuthPolicy(authenicationPolicy);
        userEntity.setUserId(userId);
        userEntity.setAuthToken(authToken);
        userEntity.setUserRole(userRole);
        userEntity.setDateAdded(currentTime);
        userEntity.setDateModified(currentTime);
        TeamUserEntity teamUserEntity = new TeamUserEntity();
        teamUserEntity.setUserEntity(userEntity);
        try {
            TeamEntity teamEntity = databaseManager.get(TeamEntity.class, teamId);
            userEntity.setOrganizationEntity(teamEntity.getOrganizationEntity());
            Serializable id = databaseManager.save(userEntity);
            userEntity = databaseManager.get(UserEntity.class, id);
            teamUserEntity.setTeamEntity(teamEntity);
            teamUserEntity.setUserEntity(userEntity);
            teamUserEntity.setDateAdded(currentTime);
            teamUserEntity.setDateModified(currentTime);
            databaseManager.save(teamUserEntity);
            UserProfileEntity userProfileEntity = new UserProfileEntity();
            userProfileEntity.setUserEntity(userEntity);
            databaseManager.save(userProfileEntity);
            createUserDependentEntities(userId);
            return teamUserEntity;
        } catch (DatabaseManagerException | EntityNotFoundException e) {
            logger.error("Error while adding team user:{}", e.getMessage(), e);
            throw new HierarchyServiceException(e);
        }
    }

    private void createUserDependentEntities(String userId) throws HierarchyServiceException {
        try {
            leaveManagementService.addUserLeaveQuota(userId);
        } catch (LeaveManagementServiceException e) {
            throw new HierarchyServiceException(e);
        }
    }

    @Override
    public ITeamUser assignUserToTeam(Long teamId, String userId) throws HierarchyServiceException {
        UserEntity userEntity;
        try {
            userEntity = databaseManager.get(UserEntity.class, userId);
        } catch (DatabaseManagerException | EntityNotFoundException e1) {
            logger.error("Error while fetching user:{}", e1.getMessage(), e1);
            throw new HierarchyServiceException(e1);
        }
        TeamUserEntity teamUserEntity = new TeamUserEntity();
        teamUserEntity.setUserEntity(userEntity);
        Date currentTime = new Date();
        teamUserEntity.setDateAdded(currentTime);
        teamUserEntity.setDateModified(currentTime);
        try {
            TeamEntity teamEntity = databaseManager.get(TeamEntity.class, teamId);
            teamUserEntity.setTeamEntity(teamEntity);
            databaseManager.save(teamUserEntity);
            return teamUserEntity;
        } catch (DatabaseManagerException | EntityNotFoundException e) {
            logger.error("Error while adding team user:{}", e.getMessage(), e);
            throw new HierarchyServiceException(e);
        }
    }

    @Override
    public void removeUserFromTeam(Long teamId, String userId) throws HierarchyServiceException {
        QTeamUserEntity qTeamUserEntity = QTeamUserEntity.teamUserEntity;
        CohoDeleteClause hibernateDeleteClause = new CohoDeleteClause(qTeamUserEntity)
                .where(qTeamUserEntity.userEntity.userId.eq(userId).and(qTeamUserEntity.teamEntity.teamId.eq(teamId)));
        long deletedUserCount;
        try {
            deletedUserCount = databaseManager.executeDeleteClause(hibernateDeleteClause);
            logger.info("No of users deleted:{}", deletedUserCount);
        } catch (DatabaseManagerException e) {
            throw new HierarchyServiceException(e);
        }
    }

    @Override
    public List<? extends ITeamUser> getAllUsersForTeam(Long teamId) throws HierarchyServiceException {
        QTeamUserEntity qTeamUserEntity = QTeamUserEntity.teamUserEntity;
        HibernateQuery hibernateQuery = new HibernateQuery().from(qTeamUserEntity).where(
                qTeamUserEntity.teamEntity.teamId.eq(teamId));
        try {
            return databaseManager.executeQueryAndGetResults(hibernateQuery, qTeamUserEntity);
        } catch (DatabaseManagerException e) {
            throw new HierarchyServiceException(e);
        }
    }

    @Override
    public List<? extends ITeamUser> getAllUsersForTeamByRole(Long teamId, UserRole userRole)
            throws HierarchyServiceException {
        QTeamUserEntity qTeamUserEntity = QTeamUserEntity.teamUserEntity;
        HibernateQuery hibernateQuery = new HibernateQuery();
        hibernateQuery.from(qTeamUserEntity).where(
                qTeamUserEntity.teamEntity.teamId.eq(teamId).and(qTeamUserEntity.userEntity.userRole.eq(userRole)));
        try {
            return databaseManager.executeQueryAndGetResults(hibernateQuery, qTeamUserEntity);
        } catch (DatabaseManagerException e) {
            throw new HierarchyServiceException(e);
        }
    }

    @Override
    public void deleteUser(String userId) throws HierarchyServiceException {
    }

    @Override
    public List<? extends ITeam> getTeamsForUser(String userId) throws HierarchyServiceException {
        QTeamUserEntity qTeamUserEntity = QTeamUserEntity.teamUserEntity;
        HibernateQuery hibernateQuery = new HibernateQuery().from(qTeamUserEntity).where(
                qTeamUserEntity.userEntity.userId.eq(userId));
        List<ITeam> teams = new ArrayList<ITeam>();
        try {
            List<TeamUserEntity> teamUsers = databaseManager.executeQueryAndGetResults(hibernateQuery, qTeamUserEntity);
            for (TeamUserEntity teamUserEntity : teamUsers) {
                TeamEntity teamEntity = databaseManager.get(TeamEntity.class, teamUserEntity.getTeamId());
                teams.add(teamEntity);
            }
        } catch (DatabaseManagerException | EntityNotFoundException e) {
            throw new HierarchyServiceException(e);
        }
        return teams;
    }

    @Override
    public IOrganization addOrganization(String organizationName, String organizationDescription)
            throws HierarchyServiceException {
        OrganizationEntity organizationEntity = new OrganizationEntity();
        organizationEntity.setOrganizationName(organizationName);
        organizationEntity.setOrganizationDescription(organizationDescription);
        Date currentTime = new Date();
        organizationEntity.setDateAdded(currentTime);
        organizationEntity.setDateModified(currentTime);
        try {
            Long organizationId = (Long)databaseManager.save(organizationEntity);
            Set<UserRole> availableUserRoles = new HashSet<UserRole>();
            availableUserRoles.add(UserRole.AGENT);
            availableUserRoles.add(UserRole.ADMIN);
            addOrganizationMetaConfiguration(organizationId, organizationName, UserIdGenerationPolicy.MANUAL,
                    AuthenicationPolicy.PASSWORD_BASED, 100, 10, 3, availableUserRoles);
            createOrgDependentEntities(organizationEntity);
        } catch (DatabaseManagerException e) {
            logger.error("Error while adding organization:{}", e.getMessage(), e);
            throw new HierarchyServiceException(e);
        }
        return organizationEntity;
    }

    private void createOrgDependentEntities(OrganizationEntity organizationEntity) throws HierarchyServiceException {
        IOrganizationMetaConfiguration organizationMetaConfiguration;
        try {
            organizationMetaConfiguration = getOrganizationMetaConfiguration(organizationEntity.getOrganizationId());
            for (UserRole userRole : organizationMetaConfiguration.getAvailableUserRoles()) {
                Map<LeaveType, Integer> leaveTypeVsLeaveCount = new HashMap<LeaveType, Integer>();
                for (LeaveType leaveType : LeaveType.values()) {
                    leaveTypeVsLeaveCount.put(leaveType, 0);
                }
                leaveTypeVsLeaveCount.put(LeaveType.SICK_LEAVE, 1);
                leaveManagementService.createLeaveQuotaForRole(organizationEntity.getOrganizationId(), userRole,
                        leaveTypeVsLeaveCount, LeaveAccumulationPolicy.MONTHLY);
            }

            for (UserRole userRole : organizationMetaConfiguration.getAvailableUserRoles()) {
                leaveManagementService.addWeeklyHoliday(organizationEntity.getOrganizationId(), userRole,
                        DateTimeConstants.SUNDAY, "Sunday");
            }
        } catch (ResourceNotFoundException | LeaveManagementServiceException e) {
            throw new HierarchyServiceException(e);
        }
    }

    private IOrganizationMetaConfiguration addOrganizationMetaConfiguration(Long organizationId, String orgPrefix,
            UserIdGenerationPolicy userIdGenerationPolicy, AuthenicationPolicy authenicationPolicy,
            Integer maxUsersAllowed, Integer maxTeamsAllowed, Integer hierarchyDepth, Set<UserRole> availableUserRoles)
            throws HierarchyServiceException {
        OrganizationMetaConfigurationEntity organizationMetaConfigurationEntity = new OrganizationMetaConfigurationEntity();
        organizationMetaConfigurationEntity.setDefaultAuthenticationPolicy(authenicationPolicy);
        organizationMetaConfigurationEntity.setHierarchyDepth(hierarchyDepth);
        organizationMetaConfigurationEntity.setMaxTeamsAllowed(maxTeamsAllowed);
        organizationMetaConfigurationEntity.setMaxUsersAllowed(maxUsersAllowed);
        organizationMetaConfigurationEntity.setUserIdGenerationPolicy(userIdGenerationPolicy);
        organizationMetaConfigurationEntity.setOrgPrefix(orgPrefix);
        organizationMetaConfigurationEntity.setAvailableUserRoles(availableUserRoles);
        try {
            OrganizationEntity organizationEntity = databaseManager.get(OrganizationEntity.class, organizationId);
            organizationMetaConfigurationEntity.setOrganizationEntity(organizationEntity);
            databaseManager.save(organizationMetaConfigurationEntity);
        } catch (DatabaseManagerException | EntityNotFoundException e) {
            throw new HierarchyServiceException(e.getMessage(), e);
        }
        return organizationMetaConfigurationEntity;
    }

    @Override
    public IUser getUser(String userId) throws HierarchyServiceException, ResourceNotFoundException {
        try {
            UserEntity userEntity = databaseManager.get(UserEntity.class, userId);
            return userEntity;
        } catch (DatabaseManagerException e) {
            logger.error("Error while fetching user:{}", userId, e);
            throw new HierarchyServiceException(e);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage(), e);
        }
    }

    @Override
    public IOrganizationMetaConfiguration getOrganizationMetaConfiguration(Long organizationId)
            throws HierarchyServiceException, ResourceNotFoundException {
        try {
            return databaseManager.get(OrganizationMetaConfigurationEntity.class, organizationId);
        } catch (DatabaseManagerException e) {
            logger.error("Error while fetching organization:{}", organizationId, e);
            throw new HierarchyServiceException(e);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage(), e);
        }
    }

    @Override
    public List<? extends IOrganization> getAllOrganizationInfo() throws HierarchyServiceException {
        QOrganizationEntity qOrganizationEntity = QOrganizationEntity.organizationEntity;
        HibernateQuery hiberateQuery = new HibernateQuery().from(qOrganizationEntity);
        try {
            return databaseManager.executeQueryAndGetResults(hiberateQuery, qOrganizationEntity);
        } catch (DatabaseManagerException e) {
            throw new HierarchyServiceException(e);
        }
    }

    @Override
    public IUserProfile updateUserProfile(String userId, String firstName, String lastName, Date dateOfBirth,
            String gender, String mobileNumber, String emailId, String addressLine1, String addressLine2, String city,
            String pincode, String state, String country, Date joinedOn, Date leftOn, String designation)
            throws HierarchyServiceException {

        try {
            UserProfileEntity userProfileEntity;
            userProfileEntity = databaseManager.get(UserProfileEntity.class, userId);

            userProfileEntity.setFirstName(firstName);
            userProfileEntity.setLastName(lastName);
            userProfileEntity.setDateOfBirth(dateOfBirth);
            userProfileEntity.setGender(gender);
            userProfileEntity.setMobileNumber(mobileNumber);
            userProfileEntity.setEmailId(emailId);
            userProfileEntity.setAddressLine1(addressLine1);
            userProfileEntity.setAddressLine2(addressLine2);
            userProfileEntity.setCity(city);
            userProfileEntity.setPincode(pincode);
            userProfileEntity.setState(state);
            userProfileEntity.setCountry(country);
            userProfileEntity.setJoinedOn(joinedOn);
            userProfileEntity.setLeftOn(leftOn);
            userProfileEntity.setDesignation(designation);

            databaseManager.saveOrUpdate(userProfileEntity);
            return userProfileEntity;
        } catch (DatabaseManagerException | EntityNotFoundException e) {
            throw new HierarchyServiceException(e);
        }
    }

    @Override
    public IUserProfile getUserProfile(String userId) throws HierarchyServiceException, ResourceNotFoundException {
        try {
            return databaseManager.get(UserProfileEntity.class, userId);
        } catch (DatabaseManagerException e) {
            logger.error("Error while fetching user profile:{}", userId, e);
            throw new HierarchyServiceException(e);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(e.getMessage(), e);
        }
    }

    @Override
    public Set<UserRole> getAvailableUserRoles(Long organizationId) throws HierarchyServiceException {
        try {
            IOrganizationMetaConfiguration organizationMetaConfiguration = getOrganizationMetaConfiguration(organizationId);
            return organizationMetaConfiguration.getAvailableUserRoles();
        } catch (ResourceNotFoundException e) {
            throw new HierarchyServiceException(e);
        }
    }

    @Override
    public ITeam getDefaultTeam(Long organizationId) throws HierarchyServiceException {
        List<? extends ITeam> allTeams = getAllTeams(organizationId);
        for (ITeam team : allTeams) {
            if (DEFAULT_TEAM_NAME.equals(team.getTeamName())) {
                return team;
            }
        }
        throw new HierarchyServiceException("No default team found");
    }

    @Override
    public IOrganization getDefaultOrganization() throws HierarchyServiceException {
        List<? extends IOrganization> allOrganizationInfo = getAllOrganizationInfo();
        for (IOrganization organization : allOrganizationInfo) {
            if (DEFAULT_ORG_NAME.equals(organization.getOrganizationName())) {
                return organization;
            }
        }
        throw new HierarchyServiceException("No default org found");
    }

    @Override
    public List<? extends IUser> getReportingUsers(String userId) throws HierarchyServiceException {
        try {
            IUser user = getUser(userId);
            IOrganizationMetaConfiguration organizationMetaConfiguration = getOrganizationMetaConfiguration(getDefaultOrganization()
                    .getOrganizationId());
            List<UserRole> reportinUserRoles = new ArrayList<UserRole>();
            for (UserRole userRole : organizationMetaConfiguration.getAvailableUserRoles()) {
                if (user.getUserRole().getLevel() < userRole.getLevel()) {
                    reportinUserRoles.add(userRole);
                }
            }
            QUserEntity qUserEntity = QUserEntity.userEntity;
            HibernateQuery hibernateQuery = new HibernateQuery().from(qUserEntity).where(
                    qUserEntity.userRole.in(reportinUserRoles));
            return databaseManager.executeQueryAndGetResults(hibernateQuery, qUserEntity);
        } catch (ResourceNotFoundException | DatabaseManagerException e) {
            throw new HierarchyServiceException(e);
        }
    }

    @Override
    public List<? extends ITeamUser> getAllTeamUserByUserId(String userId) throws HierarchyServiceException {
        QTeamUserEntity qTeamUserEntity = QTeamUserEntity.teamUserEntity;
        HibernateQuery hibernateQuery = new HibernateQuery().from(qTeamUserEntity).where(
                qTeamUserEntity.userEntity.userId.eq(userId));
        try {
            return databaseManager.executeQueryAndGetResults(hibernateQuery, qTeamUserEntity);
        } catch (DatabaseManagerException e) {
            throw new HierarchyServiceException(e);
        }
    }

    @Override
    public ITeamUser getTeamUserByUserId(Long teamId, String userId) throws HierarchyServiceException,
            ResourceNotFoundException {
        QTeamUserEntity qTeamUserEntity = QTeamUserEntity.teamUserEntity;
        HibernateQuery hibernateQuery = new HibernateQuery().from(qTeamUserEntity).where(
                qTeamUserEntity.teamEntity.teamId.eq(teamId).and(qTeamUserEntity.userEntity.userId.eq(userId)));
        List<TeamUserEntity> teamUsers;
        try {
            teamUsers = databaseManager.executeQueryAndGetResults(hibernateQuery, qTeamUserEntity);

        } catch (DatabaseManagerException e) {
            throw new HierarchyServiceException(e);
        }
        if (teamUsers.size() > 0) {
            return teamUsers.get(0);
        } else {
            throw new ResourceNotFoundException("User not found");
        }
    }

    @Override
    public List<? extends IUserProfile> getAllUserProfiles(Long organizationId) throws HierarchyServiceException {
        QUserEntity qUserEntity = QUserEntity.userEntity;
        HibernateQuery userEntityQuery = new HibernateQuery().from(qUserEntity).where(
                qUserEntity.organizationEntity.organizationId.eq(organizationId));
        try {
            List<UserEntity> userList = databaseManager.executeQueryAndGetResults(userEntityQuery, qUserEntity);
            Set<String> userIds = userList.stream().map(UserEntity::getUserId).collect(Collectors.toSet());
            QUserProfileEntity qUserProfileEntity = QUserProfileEntity.userProfileEntity;
            HibernateQuery hibernateQuery = new HibernateQuery().from(qUserProfileEntity).where(
                    qUserProfileEntity.userId.in(userIds));
            return databaseManager.executeQueryAndGetResults(hibernateQuery, qUserProfileEntity);
        } catch (DatabaseManagerException e) {
            throw new HierarchyServiceException(e);
        }
    }

    @Override
    public IUser updateUser(String userId, String authToken, UserRole userRole) throws HierarchyServiceException {
        try {
            IUser user = getUser(userId);
            if (user == null) {
                throw new HierarchyServiceException("User with Id: " + userId + " does not exists");
            }
            UserEntity userEntity = databaseManager.get(UserEntity.class, userId);
            userEntity.setAuthToken(authToken);
            userEntity.setUserRole(userRole);
            userEntity.setDateModified(new Date());
            databaseManager.saveOrUpdate(userEntity);
            return userEntity;
        } catch (ResourceNotFoundException | DatabaseManagerException | EntityNotFoundException e) {
            logger.error("Not able to update user", e.getMessage(), e);
            throw new HierarchyServiceException(e);
        }
    }
}
