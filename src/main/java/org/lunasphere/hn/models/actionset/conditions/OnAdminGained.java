package org.lunasphere.hn.models.actionset.conditions;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.lunasphere.hn.models.actionset.ActionCondition;

@JacksonXmlRootElement(localName = "OnAdminGained")
public class OnAdminGained extends ActionCondition {
    public OnAdminGained(String target) {
        this.setTarget(target);
    }

}
