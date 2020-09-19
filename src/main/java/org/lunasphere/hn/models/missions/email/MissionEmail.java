package org.lunasphere.hn.models.missions.email;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;

import java.util.ArrayList;
import java.util.Collection;

public class MissionEmail {
    String sender;
    String subject;
    String body;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlElementWrapper(localName = "attachments")
    Collection<EmailAttachment> attachments;

    public MissionEmail(String sender, String subject, String body) {
        this.sender = sender;
        this.subject = subject;
        this.body = body;
    }

    public void addAttachment(EmailAttachment attachment) {
        if (this.attachments == null) {
            this.attachments = new ArrayList<>();
        }

        this.attachments.add(attachment);
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

    public Collection<EmailAttachment> getAttachments() {
        return attachments;
    }

    public void setAttachments(Collection<EmailAttachment> attachments) {
        this.attachments = attachments;
    }
}
