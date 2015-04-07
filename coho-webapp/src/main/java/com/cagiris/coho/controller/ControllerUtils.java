/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.controller;

import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import com.cagiris.coho.service.api.UserRole;

/**
 *
 * @author: ssnk
 */

public class ControllerUtils {
    public static User getLoggedInUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = (User)authentication.getPrincipal();
        return user;
    }

    public static Set<UserRole> getUserRolesForLoggedInUser() {
        User user = getLoggedInUser();
        Set<UserRole> userRoles = user.getAuthorities().stream().map(GrantedAuthority::getAuthority)
                .map(UserRole::valueOf).collect(Collectors.toSet());
        return userRoles;
    }

}
