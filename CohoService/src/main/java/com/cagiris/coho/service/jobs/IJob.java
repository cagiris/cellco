/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.jobs;

import com.cagiris.coho.service.exception.JobExecutionException;

/**
 *
 * @author: ssnk
 */

public interface IJob {
    public void executeJob() throws JobExecutionException;
}
