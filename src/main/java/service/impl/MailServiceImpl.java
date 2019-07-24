package service.impl;

import model.Order;
import service.MailService;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class MailServiceImpl implements MailService {

    @Override
    public void sendOneTimeCode(Order order) {
        final String username = "onlineshopsupp@gmail.com";
        final String password = "123123123onlineshop";

        Properties prop = new Properties();
        prop.put("mail.smtp.host","smtp.gmail.com");
        prop.put("mail.smtp.port","587");
        prop.put("mail.smtp.auth","true");
        prop.put("mail.smtp.starttls.enable","true");

        Session session = Session.getInstance(prop,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(username));
            message.setRecipients(
                    Message.RecipientType.TO,
                    InternetAddress.parse(order.getUser().getEmail())
            );
            message.setSubject("One-Time Code for buying");
            message.setText("Hi! It's your code " + order.getCode().getCode());
            Transport.send(message);
        } catch(MessagingException e) {
            e.printStackTrace();
        }
    }
}
