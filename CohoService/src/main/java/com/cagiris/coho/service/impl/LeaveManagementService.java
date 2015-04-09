/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.impl;

import java.io.Serializable;
import java.text.DateFormatSymbols;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cagiris.coho.service.api.IAnnualHoliday;
import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.IHoliday;
import com.cagiris.coho.service.api.ILeaveManagementService;
import com.cagiris.coho.service.api.IOrganization;
import com.cagiris.coho.service.api.IUser;
import com.cagiris.coho.service.api.IUserLeaveQuota;
import com.cagiris.coho.service.api.IUserLeaveRequest;
import com.cagiris.coho.service.api.IUserRoleLeaveQuota;
import com.cagiris.coho.service.api.IWeeklyHoliday;
import com.cagiris.coho.service.api.LeaveAccumulationPolicy;
import com.cagiris.coho.service.api.LeaveRequestStatus;
import com.cagiris.coho.service.api.LeaveType;
import com.cagiris.coho.service.api.UserRole;
import com.cagiris.coho.service.db.api.DatabaseManagerException;
import com.cagiris.coho.service.db.api.EntityNotFoundException;
import com.cagiris.coho.service.db.api.IDatabaseManager;
import com.cagiris.coho.service.entity.AnnualHolidayEntity;
import com.cagiris.coho.service.entity.QAnnualHolidayEntity;
import com.cagiris.coho.service.entity.QUserLeaveRequestEntity;
import com.cagiris.coho.service.entity.QWeeklyHolidayEntity;
import com.cagiris.coho.service.entity.UserLeaveQuotaEntity;
import com.cagiris.coho.service.entity.UserLeaveRequestEntity;
import com.cagiris.coho.service.entity.UserRoleLeaveQuotaEntity;
import com.cagiris.coho.service.entity.UserRoleLeaveQuotaEntity.UserRoleLeaveQuotaPK;
import com.cagiris.coho.service.entity.WeeklyHolidayEntity;
import com.cagiris.coho.service.exception.HierarchyServiceException;
import com.cagiris.coho.service.exception.LeaveManagementServiceException;
import com.cagiris.coho.service.exception.ResourceNotFoundException;
import com.cagiris.coho.service.utils.UniqueIDGenerator;
import com.mysema.query.jpa.hibernate.HibernateQuery;

/**
 *
 * @author: phugga
 */

public class LeaveManagementService implements ILeaveManagementService {

    private static final Logger logger = LoggerFactory.getLogger(HierarchyService.class);

    private IDatabaseManager databaseManager;

    private IHierarchyService hierarchyService;

    private UniqueIDGenerator leaveRequestIdGenerator = new UniqueIDGenerator("leave");

    public LeaveManagementService(IDatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    @Override
    public IUserLeaveRequest applyForLeave(String userId, Map<LeaveType, Integer> leaveTypeVsLeaveCount,
            Date leaveStartDate, Date leaveEndDate, String requestDescription, String requestSubject)
            throws LeaveManagementServiceException {
        Integer requiredLeaveCount = getTotalNoOfDays(leaveStartDate, leaveEndDate)
                - getNoOfHolidays(leaveStartDate, leaveEndDate);
        Date currentTime = new Date();
        //        validateLeaveRequest(userId, requiredLeaveCount);

        UserLeaveRequestEntity userLeaveRequestEntity = new UserLeaveRequestEntity();
        userLeaveRequestEntity.setLeaveApplicationId(leaveRequestIdGenerator.getNextUID(userId));
        userLeaveRequestEntity.setUserId(userId);

        if (leaveTypeVsLeaveCount == null) {
            userLeaveRequestEntity.setLeaveTypeVsLeaveCount(getLeaveTypeVsLeaveCount(userId, requiredLeaveCount));
        } else {
            userLeaveRequestEntity.setLeaveTypeVsLeaveCount(leaveTypeVsLeaveCount);
        }
        userLeaveRequestEntity.setRequestDescription(requestDescription);
        userLeaveRequestEntity.setLeaveStartDate(leaveStartDate);
        userLeaveRequestEntity.setLeaveEndDate(leaveEndDate);
        userLeaveRequestEntity.setRequiredLeaveCount(requiredLeaveCount);
        userLeaveRequestEntity.setLeaveApplicationStatus(LeaveRequestStatus.NEW);
        userLeaveRequestEntity.setDateAdded(currentTime);
        userLeaveRequestEntity.setDateModified(currentTime);
        userLeaveRequestEntity.setRequestSubject(requestSubject);
        try {
            Serializable id = databaseManager.save(userLeaveRequestEntity);
            return databaseManager.get(UserLeaveRequestEntity.class, id);
        } catch (DatabaseManagerException | EntityNotFoundException e) {
            logger.error("error while adding user leave request", e);
            throw new LeaveManagementServiceException(e.getMessage(), e);
        }
    }

    private Map<LeaveType, Integer> getLeaveTypeVsLeaveCount(String userId, Integer requiredLeaveCount)
            throws LeaveManagementServiceException {
        IUserLeaveQuota userLeaveQuota = getUserLeaveQuota(userId);
        Map<LeaveType, Integer> leaveTypeVsLeaveCount = new HashMap<LeaveType, Integer>();
        LeaveType[] availableLeaveTypes = LeaveType.values();
        for (LeaveType leaveType : availableLeaveTypes) {
            Integer leaveCount = userLeaveQuota.getLeaveTypeVsLeaveQuota().get(leaveType);
            if (requiredLeaveCount <= leaveCount) {
                leaveTypeVsLeaveCount.put(leaveType, requiredLeaveCount);
            } else {
                leaveTypeVsLeaveCount.put(leaveType, leaveCount);
                requiredLeaveCount -= leaveCount;
            }
        }
        if (requiredLeaveCount > 0 && availableLeaveTypes.length > 0) {
            leaveTypeVsLeaveCount.put(availableLeaveTypes[0], leaveTypeVsLeaveCount.get(availableLeaveTypes[0])
                    + requiredLeaveCount);
        }
        return leaveTypeVsLeaveCount;
    }

    @Override
    public IUserLeaveRequest updateLeaveRequestStatus(String approvingUserId, String leaveApplicationId,
            LeaveRequestStatus leaveStatus, String comments) throws LeaveManagementServiceException {
        try {
            Date currentTime = new Date();
            UserLeaveRequestEntity userLeaveRequestEntity = databaseManager.get(UserLeaveRequestEntity.class,
                    leaveApplicationId);
            userLeaveRequestEntity.setLeaveApplicationStatus(leaveStatus);
            userLeaveRequestEntity.setDateModified(currentTime);
            userLeaveRequestEntity.setApprovingUserComments(comments);
            userLeaveRequestEntity.setApprovingUserId(approvingUserId);
            Map<LeaveType, Integer> leaveTypeVsLeaveCount = approveLeave(userLeaveRequestEntity);
            databaseManager.saveOrUpdate(userLeaveRequestEntity);
            if (LeaveRequestStatus.APPROVED.equals(leaveStatus)) {
                updateUserLeaveQuota(userLeaveRequestEntity.getUserId(), leaveTypeVsLeaveCount, null);
            }
            return userLeaveRequestEntity;
        } catch (DatabaseManagerException | EntityNotFoundException e) {
            logger.error("error while adding user leave request", e);
            throw new LeaveManagementServiceException(e.getMessage(), e);
        }

    }

    private Map<LeaveType, Integer> approveLeave(UserLeaveRequestEntity userLeaveRequestEntity)
            throws LeaveManagementServiceException {
        IUserLeaveQuota userLeaveQuota = getUserLeaveQuota(userLeaveRequestEntity.getUserId());
        Map<LeaveType, Integer> leaveTypeVsLeaveQuota = userLeaveQuota.getLeaveTypeVsLeaveQuota();
        for (Map.Entry<LeaveType, Integer> entry : userLeaveRequestEntity.getLeaveTypeVsLeaveCount().entrySet()) {
            LeaveType leaveType = entry.getKey();
            Integer leaveCount = entry.getValue();
            //            if (leaveTypeVsLeaveQuota.get(leaveType) - leaveCount < 0) {
            //                throw new LeaveManagementServiceException("User does not have enough leaves");
            //            } else {
            leaveTypeVsLeaveQuota.put(leaveType, leaveTypeVsLeaveQuota.get(leaveType) - leaveCount);
            //            }
        }
        return leaveTypeVsLeaveQuota;
    }

    private void validateLeaveRequest(String userId, Integer requredLeaveCount) throws LeaveManagementServiceException {
        IUserLeaveQuota userLeaveQuota = getUserLeaveQuota(userId);
        if (userLeaveQuota.getTotalLeaveCount() < requredLeaveCount) {
            throw new LeaveManagementServiceException("User does not have enough leaves");
        }
    }

    @Override
    public IUserLeaveQuota getUserLeaveQuota(String userId) throws LeaveManagementServiceException {
        try {
            UserLeaveQuotaEntity userLeaveQuotaEntity = databaseManager.get(UserLeaveQuotaEntity.class, userId);
            return userLeaveQuotaEntity;
        } catch (DatabaseManagerException | EntityNotFoundException e) {
            logger.error("error while fetching User Leave Quota", e);
            throw new LeaveManagementServiceException(e.getMessage(), e);
        }
    }

    // creates or updates ... 
    @Override
    public IUserRoleLeaveQuota updateLeaveQuotaForRole(Long organizationId, UserRole userRole,
            Map<LeaveType, Integer> leaveTypeVsLeaveCount, LeaveAccumulationPolicy leaveAccumulationPolicy)
            throws LeaveManagementServiceException {
        try {
            Date currentTime = new Date();
            UserRoleLeaveQuotaEntity userRoleLeaveQuotaEntity = new UserRoleLeaveQuotaEntity();
            userRoleLeaveQuotaEntity.setOrganizationId(organizationId);
            userRoleLeaveQuotaEntity.setLeaveTypeVsLeaveCount(leaveTypeVsLeaveCount);
            userRoleLeaveQuotaEntity.setLeaveAccumulationPolicy(leaveAccumulationPolicy);
            userRoleLeaveQuotaEntity.setUserRole(userRole);
            userRoleLeaveQuotaEntity.setDateModified(currentTime);
            databaseManager.saveOrUpdate(userRoleLeaveQuotaEntity);
            return userRoleLeaveQuotaEntity;
        } catch (DatabaseManagerException e) {
            logger.error("error while adding user leave request", e);
            throw new LeaveManagementServiceException(e.getMessage(), e);
        }
    }

    @Override
    public List<? extends IUserLeaveRequest> getLeaveRequestsByUserId(String userId)
            throws LeaveManagementServiceException {
        try {
            QUserLeaveRequestEntity qUserLeaveRequestEntity = QUserLeaveRequestEntity.userLeaveRequestEntity;
            HibernateQuery hibernateQuery = new HibernateQuery().from(qUserLeaveRequestEntity).where(
                    qUserLeaveRequestEntity.userId.eq(userId));

            List<UserLeaveRequestEntity> executeQueryAndGetResults = databaseManager.executeQueryAndGetResults(
                    hibernateQuery, qUserLeaveRequestEntity);
            return executeQueryAndGetResults;
        } catch (DatabaseManagerException e) {
            logger.error("Error while retrieving Leave Request by User Id", e.getMessage(), e);
            throw new LeaveManagementServiceException(e.getMessage(), e);
        }

    }

    @Override
    public List<? extends IUserLeaveRequest> getAllPendingLeaveRequestsByLeaveStatus(String approvingUserId,
            List<LeaveRequestStatus> leaveStatus) throws LeaveManagementServiceException {

        List<String> reportingUserIds;
        try {
            List<? extends IUser> reportingUsers = getHierarchyService().getReportingUsers(approvingUserId);
            reportingUserIds = reportingUsers.stream().map(IUser::getUserId).collect(Collectors.toList());

        } catch (HierarchyServiceException e) {
            throw new LeaveManagementServiceException(e);
        }
        try {
            QUserLeaveRequestEntity qUserLeaveRequestEntity = QUserLeaveRequestEntity.userLeaveRequestEntity;
            HibernateQuery hibernateQuery = new HibernateQuery().from(qUserLeaveRequestEntity).where(
                    qUserLeaveRequestEntity.leaveApplicationStatus.in(leaveStatus).and(
                            qUserLeaveRequestEntity.userId.in(reportingUserIds)));
            List<UserLeaveRequestEntity> executeQueryAndGetResults = databaseManager.executeQueryAndGetResults(
                    hibernateQuery, qUserLeaveRequestEntity);
            return executeQueryAndGetResults;
        } catch (DatabaseManagerException e) {
            logger.error("Error while retrieving Leave Request by User Id", e.getMessage(), e);
            throw new LeaveManagementServiceException(e.getMessage(), e);
        }
    }

    @Override
    public void cancelLeaveRequest(String leaveApplicationId) throws LeaveManagementServiceException {
    }

    @Override
    public IAnnualHoliday addAnnualHoliday(Long organizationId, Integer year, Integer day, String description)
            throws LeaveManagementServiceException {
        AnnualHolidayEntity annualHolidayEntity = new AnnualHolidayEntity();
        annualHolidayEntity.setOrganizationId(organizationId);
        annualHolidayEntity.setDay(day);
        annualHolidayEntity.setYear(year);
        annualHolidayEntity.setDescription(description);
        try {
            Serializable id = databaseManager.save(annualHolidayEntity);
            return databaseManager.get(AnnualHolidayEntity.class, id);
        } catch (DatabaseManagerException | EntityNotFoundException e) {
            throw new LeaveManagementServiceException(e);
        }
    }

    @Override
    public IWeeklyHoliday addWeeklyHoliday(Long organizationId, UserRole userRole, Integer weekDay, String description)
            throws LeaveManagementServiceException {
        WeeklyHolidayEntity weeklyHolidayEntity = new WeeklyHolidayEntity();
        weeklyHolidayEntity.setDescription(description);
        weeklyHolidayEntity.setOrganizationId(organizationId);
        weeklyHolidayEntity.setUserRole(userRole);
        weeklyHolidayEntity.setWeekDay(weekDay);
        try {
            Serializable id = databaseManager.save(weeklyHolidayEntity);
            return databaseManager.get(WeeklyHolidayEntity.class, id);
        } catch (DatabaseManagerException e) {
            throw new LeaveManagementServiceException(e);
        } catch (EntityNotFoundException e) {
            throw new LeaveManagementServiceException(e);
        }
    }

    Integer getNoOfHolidays(Date startDate, Date endDate) throws LeaveManagementServiceException {
        Integer noOfWorkingDays = 0;
        Integer noOfHolidays = 0;
        List<? extends IWeeklyHoliday> allWeeklyHolidays;
        try {
            allWeeklyHolidays = getAllWeeklyHolidays(hierarchyService.getDefaultOrganization().getOrganizationId());
        } catch (LeaveManagementServiceException | HierarchyServiceException e) {
            throw new LeaveManagementServiceException(e);
        }
        Set<Integer> weeklyHolidays = allWeeklyHolidays.stream().map(IWeeklyHoliday::getWeekDay)
                .collect(Collectors.toSet());
        LocalDate localStartDate = new LocalDate(startDate);
        LocalDate localEndDate = new LocalDate(endDate);
        while (localStartDate.isBefore(localEndDate) || localStartDate.isEqual(localEndDate)) {
            if (weeklyHolidays.contains(localStartDate.getDayOfWeek())) {
                noOfHolidays++;
            } else {
                noOfWorkingDays++;
            }
            localStartDate = localStartDate.plusDays(1);

        }
        return noOfHolidays;
    }

    Integer getTotalNoOfDays(Date startDate, Date endDate) throws LeaveManagementServiceException {
        LocalDate localStartDate = new LocalDate(startDate);
        LocalDate localEndDate = new LocalDate(endDate);
        if (localStartDate.isAfter(localEndDate)) {
            throw new LeaveManagementServiceException("Leave end date is greater than leave start date");
        }
        return Days.daysBetween(localStartDate, localEndDate).getDays() + 1;
    }

    @Override
    public List<IHoliday> getAllHolidaysForOrganization(Long organizationId) throws LeaveManagementServiceException {
        List<IHoliday> holidays = new ArrayList<IHoliday>();
        holidays.addAll(getAllAnnualHolidays(organizationId));
        holidays.addAll(getAllWeeklyHolidays(organizationId));
        return holidays;
    }

    @Override
    public List<? extends IAnnualHoliday> getAllAnnualHolidays(Long organizationId)
            throws LeaveManagementServiceException {
        QAnnualHolidayEntity qAnnualHolidayEntity = QAnnualHolidayEntity.annualHolidayEntity;
        HibernateQuery hibernateQuery = new HibernateQuery().from(qAnnualHolidayEntity).where(
                qAnnualHolidayEntity.organizationId.eq(organizationId));
        try {
            return databaseManager.executeQueryAndGetResults(hibernateQuery, qAnnualHolidayEntity);
        } catch (DatabaseManagerException e) {
            throw new LeaveManagementServiceException(e);
        }
    }

    @Override
    public List<? extends IWeeklyHoliday> getAllWeeklyHolidays(Long organizationId)
            throws LeaveManagementServiceException {
        QWeeklyHolidayEntity qWeeklyHolidayEntity = QWeeklyHolidayEntity.weeklyHolidayEntity;
        HibernateQuery hibernateQuery = new HibernateQuery().from(qWeeklyHolidayEntity).where(
                qWeeklyHolidayEntity.organizationId.eq(organizationId));
        try {
            return databaseManager.executeQueryAndGetResults(hibernateQuery, qWeeklyHolidayEntity);
        } catch (DatabaseManagerException e) {
            throw new LeaveManagementServiceException(e);
        }
    }

    public IHierarchyService getHierarchyService() {
        return hierarchyService;
    }

    public void setHierarchyService(IHierarchyService hierarchyService) {
        this.hierarchyService = hierarchyService;
    }

    public static void main(String[] args) {
        LocalDate start = new LocalDate(new Date());
        LocalDate localDate = start;
        System.err.println(localDate);
        DateFormatSymbols dateFormatSymbols = new DateFormatSymbols();
        String[] weekdays = dateFormatSymbols.getWeekdays();
        System.out.println(Days.daysBetween(start, start.plusDays(1)).getDays());
        System.out.println(weekdays[0]);
    }

    @Override
    public IUserLeaveQuota addUserLeaveQuota(String userId) throws LeaveManagementServiceException {
        logger.info("Going to add user leave quota for userId:{}", userId);
        UserLeaveQuotaEntity userLeaveQuotaEntity = new UserLeaveQuotaEntity();
        userLeaveQuotaEntity.setUserId(userId);
        IOrganization defaultOrganization;
        IUser user;
        try {
            defaultOrganization = hierarchyService.getDefaultOrganization();
            user = hierarchyService.getUser(userId);
        } catch (HierarchyServiceException | ResourceNotFoundException e) {
            throw new LeaveManagementServiceException(e);
        }

        try {
            IUserRoleLeaveQuota userRoleQuota = getUserRoleQuota(defaultOrganization.getOrganizationId(),
                    user.getUserRole());
            Map<LeaveType, Integer> leaveTypeVsLeaveCount = new HashMap<LeaveType, Integer>(
                    userRoleQuota.getLeaveTypeVsLeaveCount());
            userLeaveQuotaEntity.setLeaveTypeVsLeaveQuota(leaveTypeVsLeaveCount);
            userLeaveQuotaEntity.setLastLeaveAccumulationDate(new Date());
            Serializable id = databaseManager.save(userLeaveQuotaEntity);
            return databaseManager.get(UserLeaveQuotaEntity.class, id);
        } catch (ResourceNotFoundException e) {
            throw new LeaveManagementServiceException(e);
        } catch (DatabaseManagerException e) {
            throw new LeaveManagementServiceException(e);
        } catch (EntityNotFoundException e) {
            throw new LeaveManagementServiceException(e);
        }

    }

    @Override
    public IUserRoleLeaveQuota getUserRoleQuota(Long organizationId, UserRole userRole)
            throws LeaveManagementServiceException, ResourceNotFoundException {
        UserRoleLeaveQuotaPK userRoleLeaveQuotaPK = new UserRoleLeaveQuotaPK(organizationId, userRole);
        try {
            return databaseManager.get(UserRoleLeaveQuotaEntity.class, userRoleLeaveQuotaPK);
        } catch (DatabaseManagerException e) {
            throw new LeaveManagementServiceException(e);
        } catch (EntityNotFoundException e) {
            logger.error("User role quota not found for orgId:{}, userRole:{}", organizationId, userRole);
            throw new ResourceNotFoundException(e);
        }
    }

    @Override
    public IUserLeaveRequest getLeaveRequestById(String leaveApplicationId) throws LeaveManagementServiceException {
        try {
            return databaseManager.get(UserLeaveRequestEntity.class, leaveApplicationId);
        } catch (DatabaseManagerException | EntityNotFoundException e) {
            throw new LeaveManagementServiceException(e);
        }
    }

    @Override
    public IUserLeaveQuota updateUserLeaveQuota(String userId, Map<LeaveType, Integer> leaveTypeVsLeaveCount,
            Date leaveAccumulationDate) throws LeaveManagementServiceException {
        try {
            UserLeaveQuotaEntity userLeaveQuotaEntity = databaseManager.get(UserLeaveQuotaEntity.class, userId);
            userLeaveQuotaEntity.setLeaveTypeVsLeaveQuota(leaveTypeVsLeaveCount);
            if (leaveAccumulationDate != null) {
                userLeaveQuotaEntity.setLastLeaveAccumulationDate(leaveAccumulationDate);
            }
            databaseManager.update(userLeaveQuotaEntity);
            return userLeaveQuotaEntity;
        } catch (DatabaseManagerException | EntityNotFoundException e) {
            throw new LeaveManagementServiceException(e);
        }

    }
}
