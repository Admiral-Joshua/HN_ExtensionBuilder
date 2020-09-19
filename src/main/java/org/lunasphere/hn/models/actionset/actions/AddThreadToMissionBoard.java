package org.lunasphere.hn.models.actionset.actions;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.lunasphere.hn.models.actionset.Action;

public class AddThreadToMissionBoard extends Action {
    @JacksonXmlProperty(localName = "ThreadFilepath", isAttribute = true)
    String threadFilepath;

    @JacksonXmlProperty(localName = "TargetComp", isAttribute = true)
    String targetComp;

    public AddThreadToMissionBoard(String threadFilepath, String targetComp) {
        this.threadFilepath = threadFilepath;
        this.targetComp = targetComp;
    }

    public String getThreadFilepath() {
        return threadFilepath;
    }

    public void setThreadFilepath(String threadFilepath) {
        this.threadFilepath = threadFilepath;
    }

    public String getTargetComp() {
        return targetComp;
    }

    public void setTargetComp(String targetComp) {
        this.targetComp = targetComp;
    }
}
