package org.lunasphere.hn.models.missions.email;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "link")
public class Link extends EmailAttachment {
    String comp;

    public Link(String comp) {
        this.comp = comp;
    }

    public String getComp() {
        return comp;
    }

    public void setComp(String comp) {
        this.comp = comp;
    }
}
