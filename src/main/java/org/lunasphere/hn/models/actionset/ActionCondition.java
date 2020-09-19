package org.lunasphere.hn.models.actionset;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.Collection;

public abstract class ActionCondition {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlElementWrapper(useWrapping = false)
    Collection<Action> actions;

    @JacksonXmlProperty(isAttribute = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String target;

    @JacksonXmlProperty(isAttribute = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    boolean needsMissionComplete;

    @JacksonXmlProperty(isAttribute = true)
    @JsonInclude(JsonInclude.Include.NON_NULL)
    String requiredFlags;

    public ActionCondition() {
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public boolean isNeedsMissionComplete() {
        return needsMissionComplete;
    }

    public void setNeedsMissionComplete(boolean needsMissionComplete) {
        this.needsMissionComplete = needsMissionComplete;
    }

    public String getRequiredFlags() {
        return requiredFlags;
    }

    public void setRequiredFlags(String requiredFlags) {
        this.requiredFlags = requiredFlags;
    }
}
