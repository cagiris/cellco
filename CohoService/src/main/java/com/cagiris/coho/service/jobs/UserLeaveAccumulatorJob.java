/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.jobs;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import org.joda.time.DateTime;
import org.joda.time.Months;
import org.joda.time.Years;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.ILeaveManagementService;
import com.cagiris.coho.service.api.IOrganization;
import com.cagiris.coho.service.api.ITeam;
import com.cagiris.coho.service.api.ITeamUser;
import com.cagiris.coho.service.api.IUser;
import com.cagiris.coho.service.api.IUserLeaveQuota;
import com.cagiris.coho.service.api.IUserRoleLeaveQuota;
import com.cagiris.coho.service.api.LeaveType;
import com.cagiris.coho.service.exception.JobExecutionException;

/**
 *
 * @author: ssnk
 */
public class UserLeaveAccumulatorJob implements IJob {
    private static final Logger logger = LoggerFactory.getLogger(UserLeaveAccumulatorJob.class);
    private IHierarchyService hierarchyService;
    private ILeaveManagementService leaveManagementService;

    public UserLeaveAccumulatorJob(IHierarchyService hierarchyService, ILeaveManagementService leaveManagementService) {
        this.hierarchyService = hierarchyService;
        this.leaveManagementService = leaveManagementService;
    }

    // 7 days .. 
    @Override
    @Scheduled(fixedDelay = 7 * 24 * 60 * 60 * 1000l, initialDelay = 1 * 60 * 1000l)
    public void executeJob() throws JobExecutionException {
        logger.info("Going to execute job");
        IOrganization defaultOrganization;
        DateTime currentDateTime = new DateTime(new Date());
        try {
            defaultOrganization = hierarchyService.getDefaultOrganization();
            List<? extends ITeam> allTeams = hierarchyService.getAllTeams(defaultOrganization.getOrganizationId());
            Set<String> allUserIds = new HashSet<String>();
            for (ITeam team : allTeams) {
                List<? extends ITeamUser> allUsersForTeam = hierarchyService.getAllUsersForTeam(team.getTeamId());
                allUserIds.addAll(allUsersForTeam.stream().map(ITeamUser::getUserId).collect(Collectors.toSet()));
            }
            for (String userId : allUserIds) {
                IUser user = hierarchyService.getUser(userId);
                IUserLeaveQuota userLeaveQuota = leaveManagementService.getUserLeaveQuota(userId);
                DateTime lastLeaveAccDateTime = new DateTime(userLeaveQuota.getLastLeaveAccumulationDate());
                IUserRoleLeaveQuota userRoleQuota = leaveManagementService.getUserRoleQuota(
                        defaultOrganization.getOrganizationId(), user.getUserRole());
                int factor = 0;
                switch (userRoleQuota.getLeaveAccumulationPolicy()) {
                    case MONTHLY:
                        factor = Months.monthsBetween(lastLeaveAccDateTime, currentDateTime).getMonths();
                        break;
                    case ANNUALLY:
                        factor = Years.yearsBetween(lastLeaveAccDateTime, currentDateTime).getYears();
                        break;
                    case BIANNUALLY:
                        factor = Months.monthsBetween(lastLeaveAccDateTime, currentDateTime).getMonths();
                        factor /= 6;
                        break;

                    case QUARTLY:
                        factor = Months.monthsBetween(lastLeaveAccDateTime, currentDateTime).getMonths();
                        factor /= 3;
                        break;
                }
                if (factor > 0) {
                    logger.info("Factor greater than zero. will increment leave quota for user:{}", userId);
                    Map<LeaveType, Integer> newUserLeaveQuota = getNewLeaveQuotaForUser(userLeaveQuota, userRoleQuota,
                            factor);
                    leaveManagementService.updateUserLeaveQuota(userId, newUserLeaveQuota, currentDateTime.toDate());
                }
            }
        } catch (Exception e) {
            logger.error("Error occurred during job execution", e);
            throw new JobExecutionException(e);
        }
        logger.info("Job execution completed successfully");
    }

    private Map<LeaveType, Integer> getNewLeaveQuotaForUser(IUserLeaveQuota userLeaveQuota,
            IUserRoleLeaveQuota userRoleLeaveQuota, int factor) {
        Map<LeaveType, Integer> leaveQuotaForRole = userRoleLeaveQuota.getLeaveTypeVsLeaveCount();
        Map<LeaveType, Integer> existingUserLeaveQuota = userLeaveQuota.getLeaveTypeVsLeaveQuota();
        Map<LeaveType, Integer> newUserLeaveQuota = new HashMap<LeaveType, Integer>();
        for (Map.Entry<LeaveType, Integer> entry : leaveQuotaForRole.entrySet()) {
            Integer newLeaveCount = existingUserLeaveQuota.get(entry.getKey()) + entry.getValue() * factor;
            newUserLeaveQuota.put(entry.getKey(), newLeaveCount);
        }
        return newUserLeaveQuota;
    }
}
