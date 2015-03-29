/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.utils;

import java.util.Date;
import java.util.UUID;

/**
 * 
 * @author: ssnk
 */

public class UniqueIDGenerator {
    private String initContext;
    private long startTime;

    public UniqueIDGenerator(String initContext) {
        this.initContext = initContext;
        this.startTime = new Date().getTime();
    }

    public String getNextUID() {
        UUID randomUUID = UUID.randomUUID();
        String prefix = Long.toHexString(randomUUID.getLeastSignificantBits());
        startTime = (startTime + 1) % Long.MAX_VALUE;
        return prefix + "-" + initContext + "-" + Long.toHexString(startTime);
    }

    public String getNextUID(String currentContext) {
        UUID randomUUID = UUID.randomUUID();
        String prefix = Long.toHexString(randomUUID.getLeastSignificantBits());
        startTime = (startTime + 1) % Long.MAX_VALUE;
        return prefix + "-" + initContext + "-" + currentContext + "-" + Long.toHexString(startTime);
    }
}
