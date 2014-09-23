package com.ipuc.base.mail;

import com.ipuc.base.exception.NotSendMailException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author Carlos Sepulveda
 */
public class MailService {

    private static final String TRANSPORT = "smtp";

    private static final String HOST = "smtp.gmail.com";

    private static final String ENABLE = "true";

    private static final String PORT = "587";

    private static final String AUTH = "true";

    private static final String USER = "wilson.rivera.1150010@gmail.com";

    private static final String PASS = "huespedrivera89";

    private Session session;

    private static Logger log = LoggerFactory.getLogger(MailService.class);

    private MailService() {
        try {
            Properties props = buildProperties();
            this.session = Session.getDefaultInstance(props, null);
        } catch (Exception e) {
            log.error("error init", e);
        }
       
    }
    
    
    
    private void addRecipient(List<String> to, Mail mail) throws NotSendMailException {
        for(String r : to) {
            mail.addRecipient(r);
        }
    }

    private void send(Mail mail) throws NotSendMailException {
        mail.setFrom(USER);
        try {
            Message msg = MessageFactory.buildMessage(session, mail);
            Transport t = session.getTransport(TRANSPORT);

            t.connect(USER, PASS);
            t.sendMessage(msg, msg.getAllRecipients());

            t.close();
        } catch(MessagingException e) {
            log.error("Error sending email ", e);
            throw new NotSendMailException("Error enviando correo");
        }
    }

    private Properties buildProperties() {
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", MailService.HOST);
        props.setProperty("mail.smtp.starttls.enable", MailService.ENABLE);
        props.setProperty("mail.smtp.port", MailService.PORT);
        props.setProperty("mail.smtp.auth", MailService.AUTH);
        props.setProperty("mail.smtp.user", MailService.USER);

        return props;
    }
    
    public static void send(List<String> to, String subject, String body) throws NotSendMailException {
        MailService ms = new MailService();
        Mail mail = new Mail();
        ms.addRecipient(to, mail);
        mail.setSubject(subject);
        mail.withHtml().setBody(body);

        ms.send(mail);
    }

    public static void main(String [] args) {
        try {
            System.out.println(System.getenv("ATARTEL_EMAIL"));
            log.info("Trying to send email");
            List<String> to = new ArrayList<String>();
            to.add("wilson.rivera.1150010@gmail.com");
            MailService.send(to, "MailService Test", "<h1>Hello world</h1>");
            log.info("Email sent");
        } catch(NotSendMailException e) {
            log.error("Error trying to send email : " + e.getMessage());
        }
    }
}
