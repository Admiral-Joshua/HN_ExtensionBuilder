package org.lunasphere.hn.models.actionset.conditions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.lunasphere.hn.models.actionset.ActionCondition;

@JacksonXmlRootElement(localName = "Instantly")
public class Instantly extends ActionCondition {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(isAttribute = true)
    boolean needsMissionComplete;

    public Instantly() {}

    public Instantly(boolean needsMissionComplete) {
        this.needsMissionComplete = needsMissionComplete;
    }

    public boolean isNeedsMissionComplete() {
        return needsMissionComplete;
    }

    public void setNeedsMissionComplete(boolean needsMissionComplete) {
        this.needsMissionComplete = needsMissionComplete;
    }
}
