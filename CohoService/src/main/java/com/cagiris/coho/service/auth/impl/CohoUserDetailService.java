/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.auth.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.joda.time.DateTime;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cagiris.coho.service.api.IAttendenceReportingService;
import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.ITeam;
import com.cagiris.coho.service.api.ITeamShiftDetails;
import com.cagiris.coho.service.api.IUser;
import com.cagiris.coho.service.api.IUserShiftInfo;
import com.cagiris.coho.service.exception.AttendenceReportingServiceException;
import com.cagiris.coho.service.exception.HierarchyServiceException;
import com.cagiris.coho.service.exception.ResourceNotFoundException;

/**
 *
 * @author: ssnk
 */

public class CohoUserDetailService implements UserDetailsService {

    private IHierarchyService hierarchyService;

    private IAttendenceReportingService attendenceReportingService;

    public CohoUserDetailService(IHierarchyService hierarchyService,
            IAttendenceReportingService attendenceReportingService) {
        this.hierarchyService = hierarchyService;
        this.attendenceReportingService = attendenceReportingService;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        try {
            IUser user = hierarchyService.getUser(userId);
            List<? extends ITeam> teamsForUser = hierarchyService.getTeamsForUser(userId);
            if (teamsForUser.size() == 0) {
                throw new UsernameNotFoundException("User does not belong to any team");
            }
            Long teamId = teamsForUser.get(0).getTeamId();
            IUserShiftInfo lastUserShiftInfo = attendenceReportingService.getLastShiftDetailsForUser(teamId, userId);
            ITeamShiftDetails teamShiftDetails = attendenceReportingService.getTeamShiftDetails(teamId);
            DateTime currentDateTime = new DateTime();
            if (lastUserShiftInfo != null) {
                Integer minimumGapBetweenShifts = Optional.ofNullable(teamShiftDetails.getMinimumGapBetweenShifts())
                        .map(Long::intValue).orElse(0);
                DateTime nextEligibleLoginTime = new DateTime(lastUserShiftInfo.getShiftEndTime())
                        .plusMinutes(minimumGapBetweenShifts.intValue());
                if (currentDateTime.isBefore(nextEligibleLoginTime)) {
                    throw new UsernameNotFoundException("Cannot login before minimum gap");
                }
            }
            Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getUserRole().name()));
            User userDetail = new User(user.getUserId(), user.getAuthToken(), grantedAuthorities);
            return userDetail;
        } catch (HierarchyServiceException | ResourceNotFoundException | AttendenceReportingServiceException e) {
            throw new UsernameNotFoundException(e.getMessage(), e);
        }
    }
}
