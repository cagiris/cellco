/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.cagiris.coho.service.api.ILeaveManagementService;
import com.cagiris.coho.service.exception.CohoException;
import com.cagiris.coho.service.flight.api.IBookingDetails;
import com.cagiris.coho.service.flight.api.IBookingManagementService;
import com.cagiris.coho.service.flight.impl.BookingManagementService;
import com.cagiris.coho.service.utils.EmailService;
import com.cagiris.coho.service.utils.FreemarkerUtil;
import com.cagiris.coho.service.utils.IEmailService;

/**
 *
 * @author: ssnk
 */

public class LeaveManagementServiceTest extends AbstractTestCase {

    private ILeaveManagementService leaveManagementService;

    private IBookingManagementService bookingManagementService;

    private IEmailService emailService;

    @Before
    public void setUp() {
        this.leaveManagementService = applicationContext.getBean(LeaveManagementService.class);
        this.bookingManagementService = applicationContext.getBean(BookingManagementService.class);
        this.emailService = applicationContext.getBean(EmailService.class);
    }

    @Ignore
    @Test
    public void applyLeaveTest() throws CohoException {
        //        HashMap<LeaveType, Integer> leaveTypeVsLeaveCount = new HashMap<LeaveType, Integer>();
        //        leaveTypeVsLeaveCount.put(LeaveType.CASUAL_LEAVE, 2);
        //        Date leaveStartDate = new Date(new Date().getTime() - 1 * 24 * 60 * 60 * 1000l);
        //
        //        leaveManagementService.applyForLeave("agent", null, leaveStartDate, new Date(), "having fun", "fun");

        IBookingDetails bookingDetails = bookingManagementService
                .getBookingDetails("892eef61e377ecc0-Booking-agent-14caa94ba6a");

        FreemarkerUtil freemarkerUtil = new FreemarkerUtil(Arrays.asList("booking-email-template.ftl"));
        String evaluateTemplate = freemarkerUtil.evaluateTemplate("booking-email-template.ftl", bookingDetails);
        List<String> recipients = new ArrayList<String>();
        recipients.add("sanjeev90an@gmail.com");
        emailService.sendEmail(recipients, "yo", evaluateTemplate);
        System.out.println(evaluateTemplate);

    }
}
