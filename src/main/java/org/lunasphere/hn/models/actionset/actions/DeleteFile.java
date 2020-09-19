package org.lunasphere.hn.models.actionset.actions;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.lunasphere.hn.models.actionset.Action;

public class DeleteFile extends Action {
    @JacksonXmlProperty(localName = "TargetComp", isAttribute = true)
    String targetComp;
    @JacksonXmlProperty(localName = "FilePath", isAttribute = true)
    String filePath;
    @JacksonXmlProperty(localName = "FileName", isAttribute = true)
    String fileName;
    @JacksonXmlProperty(localName = "DelayHost", isAttribute = true)
    String delayHost;
    @JacksonXmlProperty(localName = "Delay", isAttribute = true)
    Float delay;

    public DeleteFile(String targetComp, String filePath, String fileName, String delayHost, Float delay) {
        this.targetComp = targetComp;
        this.filePath = filePath;
        this.fileName = fileName;
        this.delayHost = delayHost;
        this.delay = delay;
    }

    public String getTargetComp() {
        return targetComp;
    }

    public void setTargetComp(String targetComp) {
        this.targetComp = targetComp;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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
