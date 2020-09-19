package org.lunasphere.hn.models.actionset.actions;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.lunasphere.hn.models.actionset.Action;

public class AddConditionalActions extends Action {
    @JacksonXmlProperty(localName = "FilePath", isAttribute = true)
    String filePath;
    @JacksonXmlProperty(localName = "DelayHost", isAttribute = true)
    String delayHost;
    @JacksonXmlProperty(localName = "Delay", isAttribute = true)
    Float delay;

    public AddConditionalActions(String filePath, String delayHost, Float delay) {
        this.filePath = filePath;
        this.delayHost = delayHost;
        this.delay = delay;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getDelayHost() {
        return delayHost;
    }

    public void setDelayHost(String delayHost) {
        this.delayHost = delayHost;
    }

    public Float getDelay() {
        return delay;
    }

    public void setDelay(Float delay) {
        this.delay = delay;
    }
}
