package org.lunasphere.hn.models.missions.email;

import com.fasterxml.jackson.databind.annotation.JacksonStdImpl;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

public class Link {
    @JacksonXmlProperty(isAttribute = true)
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
