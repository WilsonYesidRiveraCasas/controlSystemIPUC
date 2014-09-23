
package com.ipuc.base.mail;

import com.ipuc.base.exception.NotSendMailException;
import com.ipuc.base.util.RegexValidator;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author wilson-rivera
 */
public class Mail {
    
    private String from;

    private List<String> recipients;

    private String subject;

    private String body;

    private String contentType;
 
    private static String HTML = "text/html; charset=UTF-8";

    private static String TEXT = "text/plain";

    public Mail() {
        this.contentType = TEXT;
        recipients = new ArrayList<String>();
    }

    public void setRecipients(List<String> recipients) {
        this.recipients = recipients;
    }

    public void addRecipient(String email) throws NotSendMailException {
        if(!RegexValidator.isValidateEmail(email)) {
            throw new NotSendMailException("Invalid Email : " + email);
        }
        recipients.add(email);
    }

    public List<String> getRecipients() {
        return recipients;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getFrom() {
        return from;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getSubject() {
        return subject;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getBody() {
        return body;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public String getContentType() {
        return this.contentType;
    }

    public Mail withHtml() {
        this.setContentType(HTML);
        return this;
    }
}
