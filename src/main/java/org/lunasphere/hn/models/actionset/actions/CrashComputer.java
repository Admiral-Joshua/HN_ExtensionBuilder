package org.lunasphere.hn.models.actionset.actions;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.lunasphere.hn.models.actionset.Action;

public class CrashComputer extends Action {
    @JacksonXmlProperty(localName = "TargetComp", isAttribute = true)
    String targetComp;
    @JacksonXmlProperty(localName = "CrashSource", isAttribute = true)
    String crashSource;
    @JacksonXmlProperty(localName = "DelayHost", isAttribute = true)
    String delayHost;
    @JacksonXmlProperty(localName = "Delay", isAttribute = true)
    Float delay;

    public CrashComputer(String targetComp, String crashSource, String delayHost, Float delay) {
        this.targetComp = targetComp;
        this.crashSource = crashSource;
        this.delayHost = delayHost;
        this.delay = delay;
    }

    public String getTargetComp() {
        return targetComp;
    }

    public void setTargetComp(String targetComp) {
        this.targetComp = targetComp;
    }

    public String getCrashSource() {
        return crashSource;
    }

    public void setCrashSource(String crashSource) {
        this.crashSource = crashSource;
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
