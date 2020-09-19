package org.lunasphere.hn.models.actionset.conditions;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.lunasphere.hn.models.actionset.ActionCondition;

@JacksonXmlRootElement(localName = "HasFlags")
public class HasFlags extends ActionCondition {
    public HasFlags(String requiredFlags) {
        this.setRequiredFlags(requiredFlags);
    }
}
