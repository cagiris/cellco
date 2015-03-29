/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.auth.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.IUser;
import com.cagiris.coho.service.exception.HierarchyServiceException;
import com.cagiris.coho.service.exception.ResourceNotFoundException;

/**
 *
 * @author: ssnk
 */

public class CohoUserDetailService implements UserDetailsService {

    private IHierarchyService hierarchyService;

    public CohoUserDetailService(IHierarchyService hierarchyService) {
        this.hierarchyService = hierarchyService;
    }

    @Override
    public UserDetails loadUserByUsername(String userId) throws UsernameNotFoundException {
        try {
            IUser user = hierarchyService.getUser(userId);
            Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
            grantedAuthorities.add(new SimpleGrantedAuthority(user.getUserRole().name()));
            User userDetail = new User(user.getUserId(), user.getAuthToken(), grantedAuthorities);
            return userDetail;
        } catch (HierarchyServiceException | ResourceNotFoundException e) {
            throw new UsernameNotFoundException(e.getMessage(), e);
        }
    }

}
