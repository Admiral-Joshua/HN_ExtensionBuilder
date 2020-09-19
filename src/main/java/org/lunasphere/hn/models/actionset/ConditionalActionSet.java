package org.lunasphere.hn.models.actionset;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.ArrayList;
import java.util.Collection;

@JacksonXmlRootElement(localName = "ConditionalActions")
public class ConditionalActionSet {
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlElementWrapper(useWrapping = false)
    Collection<ActionCondition> conditions;

    public ConditionalActionSet() {
        this.conditions = new ArrayList<>();
    }

    public ConditionalActionSet(Collection<ActionCondition> conditions) {
        this.conditions = conditions;
    }

    public void addCondition(ActionCondition condition) {
        this.conditions.add(condition);
    }

    public Collection<ActionCondition> getConditions() {
        return conditions;
    }

    public void setConditions(Collection<ActionCondition> conditions) {
        this.conditions = conditions;
    }
}
