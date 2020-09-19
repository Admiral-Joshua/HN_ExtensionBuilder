package org.lunasphere.hn.models.actionset.actions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.lunasphere.hn.models.actionset.Action;

public class AddMissionToHubServer extends Action {
    @JacksonXmlProperty(localName = "MissionFilepath", isAttribute = true)
    String missionFilepath;

    @JacksonXmlProperty(localName = "TargetComp", isAttribute = true)
    String targetComp;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "AssignmentTag", isAttribute = true)
    String assignmentTag;

    public AddMissionToHubServer(String missionFilepath, String targetComp) {
        this.missionFilepath = missionFilepath;
        this.targetComp = targetComp;
    }

    public AddMissionToHubServer(String missionFilepath, String targetComp, String assignmentTag) {
        this.missionFilepath = missionFilepath;
        this.targetComp = targetComp;
        this.assignmentTag = assignmentTag;
    }

    public String getMissionFilepath() {
        return missionFilepath;
    }

    public void setMissionFilepath(String missionFilepath) {
        this.missionFilepath = missionFilepath;
    }

    public String getTargetComp() {
        return targetComp;
    }

    public void setTargetComp(String targetComp) {
        this.targetComp = targetComp;
    }

    public String getAssignmentTag() {
        return assignmentTag;
    }

    public void setAssignmentTag(String assignmentTag) {
        this.assignmentTag = assignmentTag;
    }
}
