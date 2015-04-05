/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

import java.util.Set;

/**
 * This will be used to impose restrictions on various organization wide configurations.
 * @author: ssnk
 */

public interface IOrganizationMetaConfiguration {

    Long getOrganizationId();

    UserIdGenerationPolicy getUserIdGenerationPolicy();

    AuthenicationPolicy getDefaultAuthenticationPolicy();

    String getOrgPrefix();

    Integer getMaxUsersAllowed();

    Integer getMaxTeamsAllowed();

    Integer getHierarchyDepth();

    Set<UserRole> getAvailableUserRoles();
}
