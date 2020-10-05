package org.lunasphere.hn.models.missions.email;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

public class MissionEmail {
    String sender;
    String subject;
    String body;

    //@JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlElementWrapper(localName = "attachments", useWrapping = false)
    EmailAttachments attachments;

    public MissionEmail(String sender, String subject, String body) {
        this.sender = sender;
        this.subject = subject;
        this.body = body;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public EmailAttachments getAttachments() {
        return attachments;
    }

    public void setAttachments(EmailAttachments attachments) {
        this.attachments = attachments;
    }
}
