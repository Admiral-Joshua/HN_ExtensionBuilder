package org.lunasphere.hn.models.actionset.conditions;

import org.lunasphere.hn.models.actionset.ActionCondition;

public class OnDisconnect extends ActionCondition {
    public OnDisconnect(String target) {
        this.setTarget(target);
    }
}
