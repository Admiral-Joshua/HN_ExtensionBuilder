package org.lunasphere.hn.models.nodes;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class AdminPass {
    @JacksonXmlProperty(isAttribute = true)
    String pass;

    public AdminPass(String pass) {
        this.pass = pass;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }
}
