/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.auth.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
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

public class CohoAuthenticationProvider implements AuthenticationProvider {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private IHierarchyService hierarchyService;

    private IAttendenceReportingService attendenceReportingService;

    public CohoAuthenticationProvider(IHierarchyService hierarchyService,
            IAttendenceReportingService attendenceReportingService) {
        this.hierarchyService = hierarchyService;
        this.attendenceReportingService = attendenceReportingService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        IUser user;
        logger.info("Authenticating user:{}", authentication.getPrincipal());
        try {
            user = hierarchyService.getUser(authentication.getName());
        } catch (HierarchyServiceException e) {
            logger.warn("Error while fetching user:{}", authentication.getName(), e);
            throw new UsernameNotFoundException("User does not exist");
        } catch (ResourceNotFoundException e) {
            logger.warn("Failed to fetch user:{}", authentication.getName());
            throw new UsernameNotFoundException("User does not exist");
        }
        if (!StringUtils.equalsIgnoreCase(user.getAuthToken(), authentication.getCredentials().toString())) {
            logger.warn("Invalid password entered by user:{}", authentication.getName());
            throw new BadCredentialsException("Invalid password");
        }
        String userId = user.getUserId();
        List<? extends ITeam> teamsForUser;
        try {
            teamsForUser = hierarchyService.getTeamsForUser(userId);
            if (teamsForUser.size() == 0) {
                logger.warn("User:{} does not belong to any team", userId);
                throw new UsernameNotFoundException("User does not exist");
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
                    logger.warn("User:{} cannot start shift before:{}", userId, nextEligibleLoginTime);
                    throw new LockedException("Cannot login before " + nextEligibleLoginTime);
                }
            }
            Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getUserRole().name()));
            User userDetail1 = new User(user.getUserId(), user.getAuthToken(), grantedAuthorities);
            Authentication userDetail = new UsernamePasswordAuthenticationToken(userDetail1, user.getAuthToken(),
                    grantedAuthorities);
            logger.info("User:{} authenticated successfully", userId);
            return userDetail;
        } catch (HierarchyServiceException | AttendenceReportingServiceException | ResourceNotFoundException e) {
            logger.warn("Error while authenticating user:{}", authentication.getName(), e);
            throw new AuthenticationServiceException(e.getMessage(), e);
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
