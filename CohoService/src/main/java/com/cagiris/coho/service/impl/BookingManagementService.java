/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.db.api.IDatabaseManager;

/**
 *
 * @author: Ashish Jindal
 */

public class BookingManagementService {

    private static final Logger logger = LoggerFactory.getLogger(BookingManagementService.class);

    private IDatabaseManager databaseManager;

    private IHierarchyService hierarchyService;

    public BookingManagementService(IDatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public IDatabaseManager getDatabaseManager() {
        return databaseManager;
    }

    public void setDatabaseManager(IDatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
    }

    public IHierarchyService getHierarchyService() {
        return hierarchyService;
    }

    public void setHierarchyService(IHierarchyService hierarchyService) {
        this.hierarchyService = hierarchyService;
    }
}
