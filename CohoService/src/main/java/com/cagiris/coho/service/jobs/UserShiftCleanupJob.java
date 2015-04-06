/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.jobs;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.springframework.scheduling.annotation.Scheduled;

import com.cagiris.coho.service.api.IAttendenceReportingService;
import com.cagiris.coho.service.api.ITeamShiftDetails;
import com.cagiris.coho.service.api.IUserShiftInfo;
import com.cagiris.coho.service.exception.JobExecutionException;

/**
 *
 * @author: ssnk
 */
public class UserShiftCleanupJob implements IJob {

    private IAttendenceReportingService attendenceReportingService;

    public UserShiftCleanupJob(IAttendenceReportingService attendenceReportingService) {
        this.attendenceReportingService = attendenceReportingService;
    }

    @Scheduled(fixedDelay = 1 * 1 * 60 * 1000l, initialDelay = 1 * 60 * 1000l)
    @Override
    public void executeJob() throws JobExecutionException {
        DateTime currentTime = new DateTime(new Date());
        List<? extends IUserShiftInfo> activeUserShiftInfos;
        try {
            activeUserShiftInfos = attendenceReportingService.getAllActiveShiftInfos();
            for (IUserShiftInfo userShiftInfo : activeUserShiftInfos) {
                Long teamId = userShiftInfo.getTeamId();
                ITeamShiftDetails teamShiftDetails = attendenceReportingService.getTeamShiftDetails(teamId);
                DateTime expectedShiftEndTime = new DateTime(userShiftInfo.getShiftStartTime())
                        .plusMinutes(teamShiftDetails.getShiftBuffer().intValue());
                if (expectedShiftEndTime.isBefore(currentTime)) {
                    attendenceReportingService.updateUserShiftInfo(userShiftInfo.getShiftId(),
                            userShiftInfo.getShiftStartTime(), currentTime.toDate(), "Shift timed out");
                }
            }
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }

}
