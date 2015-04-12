/*
 * Copyright (c) 2015, Cagiris Pvt. Ltd.
 * All rights reserved.
 */
package com.cagiris.coho.service.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import javax.mail.Address;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author: ssnk
 */

public class EmailService implements IEmailService {

    private Session session;

    public void init() {
        String username = "coho.cagiris@gmail.com";
        String password = "jklabc123";
        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");

        session = Session.getInstance(props, new javax.mail.Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
    }

    @Override
    public void sendEmail(List<String> recipients, String subject, String body) {
        try {
            Message message = new MimeMessage(session);

            recipients.add("coho.cagiris@gmail.com");
            List<Address> addresses = new ArrayList<Address>();
            for (String recipient : recipients) {
                InternetAddress[] parsedAddress = InternetAddress.parse(recipient);
                if (parsedAddress.length > 0) {
                    addresses.add(parsedAddress[0]);
                }
            }
            message.setRecipients(Message.RecipientType.TO, addresses.toArray(new Address[0]));
            message.setSubject(subject);
            message.setContent(body, "text/html; charset=utf-8");
            Transport.send(message);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
