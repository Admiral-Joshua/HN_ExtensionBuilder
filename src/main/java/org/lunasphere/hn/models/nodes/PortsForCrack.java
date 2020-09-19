package org.lunasphere.hn.models.nodes;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class PortsForCrack {
    @JacksonXmlProperty(isAttribute = true)
    int val;

    public PortsForCrack(int val) {
        this.val = val;
    }

    public int getVal() {
        return val;
    }

    public void setVal(int val) {
        this.val = val;
    }
}
