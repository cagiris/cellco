/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

import java.util.Date;
import java.util.Map;

/**
 *
 * @author: ssnk
 */

public interface IUserLeaveQuota {

    String getUserId();

    Map<LeaveType, Integer> getLeaveTypeVsLeaveQuota();

    Integer getTotalLeaveCount();

    Date getLastLeaveAccumulationDate();
}
