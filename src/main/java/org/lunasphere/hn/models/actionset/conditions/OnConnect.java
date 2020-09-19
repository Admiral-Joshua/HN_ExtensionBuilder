package org.lunasphere.hn.models.actionset.conditions;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.lunasphere.hn.models.actionset.ActionCondition;

@JacksonXmlRootElement(localName = "OnConnect")
public class OnConnect extends ActionCondition {
    public OnConnect(String target, boolean needsMissionComplete, String requiredFlags) {
        this.setTarget(target);
        this.setNeedsMissionComplete(needsMissionComplete);
        this.setRequiredFlags(requiredFlags);
    }


}
