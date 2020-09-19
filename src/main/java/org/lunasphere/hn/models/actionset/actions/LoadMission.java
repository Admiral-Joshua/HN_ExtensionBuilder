package org.lunasphere.hn.models.actionset.actions;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.lunasphere.hn.models.actionset.Action;

@JacksonXmlRootElement(localName = "LoadMission")
public class LoadMission extends Action {
    @JacksonXmlProperty(localName = "MissionName")
    String missionName;

    public LoadMission(String missionName) {
        this.missionName = missionName;
    }

    public String getMissionName() {
        return missionName;
    }

    public void setMissionName(String missionName) {
        this.missionName = missionName;
    }
}
