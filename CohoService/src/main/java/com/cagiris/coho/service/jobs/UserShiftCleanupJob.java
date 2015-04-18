/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.jobs;

import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger logger = LoggerFactory.getLogger(UserShiftCleanupJob.class);

    private IAttendenceReportingService attendenceReportingService;

    public UserShiftCleanupJob(IAttendenceReportingService attendenceReportingService) {
        this.attendenceReportingService = attendenceReportingService;
    }

    // 5 minutes
    @Scheduled(fixedDelay = 5 * 60 * 1000l, initialDelay = 1 * 60 * 1000l)
    @Override
    public void executeJob() throws JobExecutionException {
        DateTime currentTime = new DateTime(new Date());
        List<? extends IUserShiftInfo> activeUserShiftInfos;
        try {
            activeUserShiftInfos = attendenceReportingService.getAllActiveShiftInfos();
            for (IUserShiftInfo userShiftInfo : activeUserShiftInfos) {
                Long teamId = userShiftInfo.getTeamId();
                ITeamShiftDetails teamShiftDetails = attendenceReportingService.getTeamShiftDetails(teamId);
                Long shiftDurationInMillis = teamShiftDetails.getShiftDuration();
                Long shiftBuffer = shiftDurationInMillis / (60 * 1000) + teamShiftDetails.getShiftBuffer();
                DateTime expectedShiftEndTime = new DateTime(userShiftInfo.getShiftStartTime()).plusMinutes(shiftBuffer
                        .intValue());
                if (expectedShiftEndTime.isBefore(currentTime)) {
                    logger.info("Ending shift for user:{} as used logged in more than :{} minutes",
                            userShiftInfo.getUserId(), shiftBuffer);
                    attendenceReportingService.updateUserShiftInfo(userShiftInfo.getShiftId(),
                            userShiftInfo.getShiftStartTime(), currentTime.toDate(), "Shift timed out");
                }
            }
        } catch (Exception e) {
            throw new JobExecutionException(e);
        }
    }
}
