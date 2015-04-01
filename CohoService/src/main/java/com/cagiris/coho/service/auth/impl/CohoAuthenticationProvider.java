/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.auth.impl;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.IUser;
import com.cagiris.coho.service.exception.HierarchyServiceException;
import com.cagiris.coho.service.exception.ResourceNotFoundException;

/**
 *
 * @author: ssnk
 */

public class CohoAuthenticationProvider implements AuthenticationProvider {

    private IHierarchyService hierarchyService;

    public CohoAuthenticationProvider(IHierarchyService hierarchyService) {
        this.hierarchyService = hierarchyService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String userId = authentication.getName();
        String password = authentication.getCredentials().toString();
        try {
            IUser user = hierarchyService.getUser(userId);
            //            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            if (user.getUserId().equals(userId) && user.getAuthToken().equals(password)) {
                return new UsernamePasswordAuthenticationToken(user.getUserId(), user.getAuthToken(), null);
            }
        } catch (HierarchyServiceException | ResourceNotFoundException e) {
        }
        throw new BadCredentialsException("Authentication failed");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
