/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.utils;

import java.util.List;

import com.cagiris.coho.service.exception.CohoException;

/**
 *
 * @author: ssnk
 */

public interface IEmailService {
    void sendEmail(List<String> recipients, String subject, String body) throws CohoException;
}
