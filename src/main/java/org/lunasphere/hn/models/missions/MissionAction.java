package org.lunasphere.hn.models.missions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class MissionAction {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "val", isAttribute = true)
    int value;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(isAttribute = true)
    boolean suppress;

    @JacksonXmlText
    String action;

    public MissionAction(String action) {
        this.action = action;
    }

    public MissionAction(int value, String action) {
        this.value = value;
        this.action = action;
    }

    public MissionAction(int value, boolean suppress, String action) {
        this.value = value;
        this.suppress = suppress;
        this.action = action;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public boolean isSuppress() {
        return suppress;
    }

    public void setSuppress(boolean suppress) {
        this.suppress = suppress;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }
}
