/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

/**
 *
 * @author: ssnk
 */

public interface IUser extends IBaseEntity {

    String getUserId();

    String getAuthToken();

    AuthenicationPolicy getAuthPolicy();

    UserRole getUserRole();

    Long getOrganizationId();

}
