/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.flight.impl;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.cagiris.coho.service.api.IHierarchyService;
import com.cagiris.coho.service.api.IUserProfile;
import com.cagiris.coho.service.db.api.DatabaseManagerException;
import com.cagiris.coho.service.db.api.EntityNotFoundException;
import com.cagiris.coho.service.db.api.IDatabaseManager;
import com.cagiris.coho.service.exception.CohoException;
import com.cagiris.coho.service.exception.ResourceNotFoundException;
import com.cagiris.coho.service.flight.api.BookingGDSType;
import com.cagiris.coho.service.flight.api.IBookingDetails;
import com.cagiris.coho.service.flight.api.IBookingManagementService;
import com.cagiris.coho.service.flight.api.ICustomer;
import com.cagiris.coho.service.flight.api.IPassenger;
import com.cagiris.coho.service.flight.entity.BookingDetailsEntity;
import com.cagiris.coho.service.flight.entity.CustomerEntity;
import com.cagiris.coho.service.flight.entity.PassengerInfoEntity;
import com.cagiris.coho.service.flight.entity.QBookingDetailsEntity;
import com.cagiris.coho.service.flight.exception.BookingManagementException;
import com.cagiris.coho.service.utils.FreemarkerUtil;
import com.cagiris.coho.service.utils.IEmailService;
import com.cagiris.coho.service.utils.UniqueIDGenerator;
import com.mysema.query.jpa.hibernate.HibernateQuery;

/**
 *
 * @author: Ashish Jindal
 */

public class BookingManagementService implements IBookingManagementService {

    private static final Logger logger = LoggerFactory.getLogger(BookingManagementService.class);

    private IDatabaseManager databaseManager;

    private IHierarchyService hierarchyService;

    private IEmailService emailService;

    private FreemarkerUtil freemarkerUtil;

    private UniqueIDGenerator bookingIdGenerator;

    private List<String> emailRecipients;

    public BookingManagementService(IDatabaseManager databaseManager) {
        this.databaseManager = databaseManager;
        this.bookingIdGenerator = new UniqueIDGenerator("Booking");
    }

    public IHierarchyService getHierarchyService() {
        return hierarchyService;
    }

    public void setHierarchyService(IHierarchyService hierarchyService) {
        this.hierarchyService = hierarchyService;
    }

    @Override
    public IBookingDetails submitBookingDetails(String userId, String pnr, BigInteger customerId,
            List<? extends IPassenger> passengers, BookingGDSType bookingGDSType, BigDecimal baseFare,
            BigDecimal taxesAndServiceFee, BigDecimal miscellaneousCharges) throws BookingManagementException {
        String bookingId = bookingIdGenerator.getNextUID();
        try {
            BookingDetailsEntity bookingDetailsEntity = new BookingDetailsEntity();
            CustomerEntity customer = (CustomerEntity)getCustomer(customerId);
            bookingDetailsEntity.setBookingId(bookingId);
            bookingDetailsEntity.setCustomer(customer);
            bookingDetailsEntity.setBaseFare(baseFare);
            bookingDetailsEntity.setMiscellaneousCharges(miscellaneousCharges);
            bookingDetailsEntity.setTaxesAndServiceFee(taxesAndServiceFee);
            bookingDetailsEntity.setUserId(userId);
            bookingDetailsEntity.setBookingGDSType(bookingGDSType);
            bookingDetailsEntity.setPnr(pnr);

            Date currentTime = new Date();
            bookingDetailsEntity.setDateAdded(currentTime);
            bookingDetailsEntity.setDateModified(currentTime);

            Serializable id = databaseManager.save(bookingDetailsEntity);

            for (IPassenger passenger : passengers) {
                PassengerInfoEntity passengerInfoEntity = new PassengerInfoEntity(passenger.getFirstName(),
                        passenger.getMiddleName(), passenger.getLastName(), passenger.getType(),
                        passenger.getDateOfBirth());
                passengerInfoEntity.setBookingDetailsEntity(bookingDetailsEntity);
                passengerInfoEntity.setDateAdded(currentTime);
                passengerInfoEntity.setDateModified(currentTime);
                databaseManager.save(passengerInfoEntity);
            }
            bookingDetailsEntity = databaseManager.get(BookingDetailsEntity.class, id);
            sendBookingAddedEmail(bookingDetailsEntity);
            return bookingDetailsEntity;
        } catch (ResourceNotFoundException e) {
            throw new BookingManagementException(e);
        } catch (DatabaseManagerException e) {
            throw new BookingManagementException(e);
        } catch (EntityNotFoundException e) {
            throw new BookingManagementException(e);
        } catch (CohoException e) {
            logger.error("Failed to send email for bookingId:{}", bookingId, e);
            throw new BookingManagementException(e);
        }
    }

    private void sendBookingAddedEmail(IBookingDetails bookingDetails) throws CohoException {
        IUserProfile userProfile = hierarchyService.getUserProfile(bookingDetails.getUserId());
        List<String> recipients = new ArrayList<String>();
        if (StringUtils.isNotBlank(userProfile.getEmailId())) {
            recipients.add(userProfile.getEmailId());
        }
        if (emailRecipients != null) {
            recipients.addAll(emailRecipients);
        }

        logger.info("Wll send email for bookingId:{} to recipients:{}", bookingDetails.getBookingId(),
                recipients.toArray(new String[0]));
        String emailBody = freemarkerUtil.evaluateTemplate("booking-email-template.ftl", bookingDetails);
        String emailSubject = "Booking id:" + bookingDetails.getBookingId();
        emailService.sendEmail(recipients, emailSubject, emailBody);
        logger.info("Email successfully sent for booking id:{}", bookingDetails.getBookingId());
    }

    @Override
    public ICustomer addCustomer(String firstName, String lastName, String middleName, String addressLine1,
            String addressLine2, String city, String contactNumber, String country, String emailId, String pincode,
            String state) throws BookingManagementException {
        CustomerEntity customerEntity = new CustomerEntity(firstName, middleName, lastName, emailId, contactNumber,
                addressLine1, addressLine2, city, country, pincode, state);
        Date currentTime = new Date();
        customerEntity.setDateAdded(currentTime);
        customerEntity.setDateModified(currentTime);
        try {
            Serializable id = databaseManager.save(customerEntity);
            return databaseManager.get(CustomerEntity.class, id);
        } catch (DatabaseManagerException | EntityNotFoundException e) {
            throw new BookingManagementException(e);
        }
    }

    @Override
    public ICustomer getCustomer(BigInteger customerId) throws ResourceNotFoundException, BookingManagementException {
        try {
            return databaseManager.get(CustomerEntity.class, customerId);
        } catch (DatabaseManagerException e) {
            throw new BookingManagementException(e);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(e);
        }
    }

    public IEmailService getEmailService() {
        return emailService;
    }

    public void setEmailService(IEmailService emailService) {
        this.emailService = emailService;
    }

    @Override
    public IBookingDetails getBookingDetails(String bookingId) throws ResourceNotFoundException,
            BookingManagementException {
        try {
            return databaseManager.get(BookingDetailsEntity.class, bookingId);
        } catch (DatabaseManagerException e) {
            throw new BookingManagementException(e);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException(e);
        }
    }

    public FreemarkerUtil getFreemarkerUtil() {
        return freemarkerUtil;
    }

    public void setFreemarkerUtil(FreemarkerUtil freemarkerUtil) {
        this.freemarkerUtil = freemarkerUtil;
    }

    @Override
    public List<? extends IBookingDetails> queryBookingDetails(List<String> userIds, Date fromDate, Date toDate)
            throws BookingManagementException {
        DateTime fromDateTime = new DateTime(fromDate);
        DateTime toDateTime = new DateTime();
        if (toDate != null) {
            toDateTime = new DateTime(toDate);
        }
        QBookingDetailsEntity qBookingDetailsEntity = QBookingDetailsEntity.bookingDetailsEntity;
        HibernateQuery hibernateQuery = new HibernateQuery().from(qBookingDetailsEntity).where(
                qBookingDetailsEntity.userId.in(userIds).and(
                        qBookingDetailsEntity.dateAdded.after(fromDateTime.toDate()).and(
                                qBookingDetailsEntity.dateAdded.before(toDateTime.toDate()))));
        try {
            return databaseManager.executeQueryAndGetResults(hibernateQuery, qBookingDetailsEntity);
        } catch (DatabaseManagerException e) {
            throw new BookingManagementException(e);
        }
    }

    public List<String> getEmailRecipients() {
        return emailRecipients;
    }

    public void setEmailRecipients(List<String> emailRecipients) {
        this.emailRecipients = emailRecipients;
    }

}
