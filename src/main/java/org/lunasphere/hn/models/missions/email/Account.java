package org.lunasphere.hn.models.missions.email;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

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

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
