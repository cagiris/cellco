/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cagiris.coho.model.QuickStatsBean;
import com.cagiris.coho.service.api.IAttendenceReportingService;
import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.ILeaveManagementService;
import com.cagiris.coho.service.api.IUserLeaveQuota;
import com.cagiris.coho.service.api.LeaveRequestStatus;
import com.cagiris.coho.service.api.LeaveType;
import com.cagiris.coho.service.exception.AttendenceReportingServiceException;
import com.cagiris.coho.service.exception.HierarchyServiceException;
import com.cagiris.coho.service.exception.LeaveManagementServiceException;

/**
 * @author Ashish Jindal
 *
 */
@Controller
public class QuickStatisticsController extends AbstractController {

    public static final String GET_AGENT_QUICK_STATISTICS = "get-agent-quick-statistics";
    public static final String GET_ADMIN_QUICK_STATISTICS = "get-admin-quick-statistics";

    private static final Logger logger = LoggerFactory.getLogger(QuickStatisticsController.class);

    @Autowired
    private ILeaveManagementService leaveManagementService;

    @Autowired
    private IHierarchyService hierarchyService;

    @Autowired
    private IAttendenceReportingService attendenceReportingService;

    @RequestMapping(GET_AGENT_QUICK_STATISTICS)
    @PreAuthorize("hasRole('AGENT')")
    public @ResponseBody QuickStatsBean getAgentQuickStats() {

        QuickStatsBean quickStatsBean = new QuickStatsBean();

        try {
            Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            User user = (User)authentication.getPrincipal();
            IUserLeaveQuota userLeaveQuota;
            userLeaveQuota = leaveManagementService.getUserLeaveQuota(user.getUsername());
            Map<LeaveType, Integer> userLeaveQuotaMap = userLeaveQuota.getLeaveTypeVsLeaveQuota();
            for (Map.Entry<LeaveType, Integer> entry : userLeaveQuotaMap.entrySet()) {
                quickStatsBean.getData().put(entry.getKey().toString(), entry.getValue().toString());
            }

            return quickStatsBean;
        } catch (LeaveManagementServiceException e) {
            logger.error("Unable to fetch agent statistics");
            logger.error(e.getMessage());
        }

        return null;
    }

    @RequestMapping(GET_ADMIN_QUICK_STATISTICS)
    @PreAuthorize("hasRole('ADMIN')")
    public @ResponseBody QuickStatsBean getAdminQuickStats() {

        Integer countPendingLeaveApprovals = 0;
        Integer countActiveUsers = 0;
        Integer countTotalUsers = 0;

        try {
            List<LeaveRequestStatus> leaveStatuses = Arrays.asList(LeaveRequestStatus.NEW, LeaveRequestStatus.PENDING);
            countPendingLeaveApprovals = leaveManagementService.getAllPendingLeaveRequestsByLeaveStatus(
                    ControllerUtils.getLoggedInUser().getUsername(), leaveStatuses).size();
            countActiveUsers = attendenceReportingService.getAllActiveShiftInfos().size();

            countTotalUsers = hierarchyService.getAllUsersForTeam(
                    ControllerUtils.getDefaultTeam(hierarchyService).getTeamId()).size();

        } catch (LeaveManagementServiceException | AttendenceReportingServiceException | HierarchyServiceException e) {
            logger.error("Unable to fetch admin statistics");
            logger.error(e.getMessage());
        }

        QuickStatsBean quickStatsBean = new QuickStatsBean();
        quickStatsBean.getData().put("Pending Approvals", countPendingLeaveApprovals.toString());
        quickStatsBean.getData().put("Active Users", countActiveUsers.toString());
        quickStatsBean.getData().put("Total Users", countTotalUsers.toString());

        return quickStatsBean;
    }
}
