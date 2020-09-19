package org.lunasphere.hn.models.missions.email;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "account")
public class Account extends Link {
    @JacksonXmlProperty(isAttribute = true)
    String user;

    @JacksonXmlProperty(isAttribute = true)
    String pass;

    public Account(String comp, String user, String pass) {
        super(comp);
        this.user = user;
        this.pass = pass;
    }
}
