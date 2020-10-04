package org.lunasphere.hn.models.actionset.conditions;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.lunasphere.hn.models.actionset.ActionCondition;

@JacksonXmlRootElement(localName = "OnConnect")
public class OnConnect extends ActionCondition {
    public OnConnect(String target) {
        this.setTarget(target);
    }
}
