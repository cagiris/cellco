/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

import java.util.Date;

/**
 *
 * @author: ssnk
 */

public interface IUserShiftInfo {

    String getShiftId();

    Date getShiftStartTime();

    Date getShiftEndTime();

    Long getShiftDuration();

    Long getTeamId();

    String getUserId();

    String getShiftStartReason();

    String getShiftEndReason();
}
