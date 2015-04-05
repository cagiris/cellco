/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.api;

import java.util.Date;

/**
 * This will maintain the shift timings for a particular team.
 *
 * @author: ssnk
 */

public interface ITeamShiftDetails {

    Long getTeamId();

    Date getShiftStartTime();

    Date getShiftEndTime();

    // the time in minutes after which the user's session should auto expire ..
    Long getShiftBuffer();
}
