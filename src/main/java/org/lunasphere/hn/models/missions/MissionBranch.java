package org.lunasphere.hn.models.missions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

@JacksonXmlRootElement(localName = "branch")
public class MissionBranch {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(isAttribute = true, localName = "IsSilent")
    boolean isSilent;

    @JacksonXmlText
    String path;

    public MissionBranch(boolean isSilent, String path) {
        this.isSilent = isSilent;
        this.path = path;
    }

    public MissionBranch(String path) {
        this.path = path;
    }

    public boolean isSilent() {
        return isSilent;
    }

    public void setSilent(boolean silent) {
        isSilent = silent;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
