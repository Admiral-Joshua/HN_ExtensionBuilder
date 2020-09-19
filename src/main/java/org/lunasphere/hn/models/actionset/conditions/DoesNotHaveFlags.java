package org.lunasphere.hn.models.actionset.conditions;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.lunasphere.hn.models.actionset.ActionCondition;

// DEPRECATED: DoesNotHaveFlags current unsupported by the Hacknet Engine.
@Deprecated
public class DoesNotHaveFlags extends ActionCondition {
    @JacksonXmlProperty(isAttribute = true, localName = "Flags")
    String flags;

    public DoesNotHaveFlags(String flags) {
        this.flags = flags;
    }

    public String getFlags() {
        return flags;
    }

    public void setFlags(String flags) {
        this.flags = flags;
    }
}
