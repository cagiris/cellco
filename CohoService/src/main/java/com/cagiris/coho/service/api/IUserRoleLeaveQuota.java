/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

import java.util.Map;

/**
 *
 * @author: ssnk
 */

public interface IUserRoleLeaveQuota {

    Long getUserLeaveQuotaId();

    UserRole getUserRole();

    Long getOrganizationId();

    Map<LeaveType, Integer> getLeaveTypeVsLeaveCount();

    LeaveAccumulationPolicy getLeaveAccumulationPolicy();
}
