package com.ipuc.base.mail;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Carlos Sepulveda
 */
public class MessageFactory {

    public static Message buildMessage(Session session, Mail mail) throws AddressException, MessagingException {
        Message msg = new MimeMessage(session);
        MessageFactory.addRecipientsToMsg(msg, mail);
        if (mail.getFrom() != null) {
            msg.setFrom(new InternetAddress(mail.getFrom()));
        }
        msg.setSubject(mail.getSubject());
        msg.setContent(mail.getBody(), mail.getContentType());

        return msg;
    }

    private static void addRecipientsToMsg(Message msg, Mail mail) throws AddressException, MessagingException {
        for(String email : mail.getRecipients()) {
            msg.addRecipient(Message.RecipientType.TO, new InternetAddress(email));
        }
    }

}
