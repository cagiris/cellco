/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.db.impl;

import org.hibernate.stat.Statistics;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;

import com.cagiris.coho.service.db.api.DatabaseManagerException;
import com.cagiris.coho.service.db.api.IDatabaseManager;
import com.cagiris.coho.service.exception.JobExecutionException;
import com.cagiris.coho.service.jobs.IJob;

/**
 *
 * @author: ssnk
 */

public class HibernateStatisticsJob implements IJob {

    private Logger logger = LoggerFactory.getLogger("statistics");

    private IDatabaseManager datbaseManager;

    public HibernateStatisticsJob(IDatabaseManager databaseManager) {
        this.datbaseManager = databaseManager;
    }

    @Scheduled(fixedDelay = 30 * 1000, initialDelay = 60 * 1000)
    @Override
    public void executeJob() throws JobExecutionException {
        logger.info("Fetching hibernate statistics");
        try {
            Statistics statistics = datbaseManager.getSessionFactory().getStatistics();
            logger.info("Second level cache stats");
            logger.info(" Fetch count:{}, Hit Count:{}, Miss Count:{}, Put Count", statistics.getEntityFetchCount(),
                    statistics.getSecondLevelCacheHitCount(), statistics.getSecondLevelCacheMissCount(),
                    statistics.getSecondLevelCachePutCount());
        } catch (DatabaseManagerException e) {
            logger.error("Error", e);
        }
    }

}
