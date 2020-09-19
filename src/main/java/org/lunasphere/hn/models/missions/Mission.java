package org.lunasphere.hn.models.missions;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.lunasphere.hn.models.missions.email.MissionEmail;
import org.lunasphere.hn.models.missions.goals.MissionGoal;

import java.util.Collection;

@JacksonXmlRootElement(localName = "mission")
public class Mission {
    @JacksonXmlProperty(isAttribute = true)
    String id;

    @JacksonXmlProperty(isAttribute = true)
    boolean activeCheck;

    @JacksonXmlProperty(isAttribute = true)
    boolean shouldIgnoreSenderVerification;

    @JacksonXmlElementWrapper(useWrapping = false)
    Collection<MissionGoal> goals;

    @JacksonXmlProperty(localName = "missionStart")
    MissionAction missionStart;

    @JacksonXmlProperty(localName = "missionEnd")
    MissionAction missionEnd;

    @JacksonXmlProperty(localName = "nextMission")
    MissionBranch nextMission;

    @JacksonXmlElementWrapper(localName = "branchMissions")
    Collection<MissionBranch> branchMissions;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "posting")
    MissionPosting posting;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(localName = "email")
    MissionEmail email;

    public Mission(String id, boolean activeCheck, boolean shouldIgnoreSenderVerification, Collection<MissionGoal> goals, MissionAction missionStart, MissionAction missionEnd, MissionEmail email) {
        this.id = id;
        this.activeCheck = activeCheck;
        this.shouldIgnoreSenderVerification = shouldIgnoreSenderVerification;
        this.goals = goals;
        this.missionStart = missionStart;
        this.missionEnd = missionEnd;
        this.email = email;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isActiveCheck() {
        return activeCheck;
    }

    public void setActiveCheck(boolean activeCheck) {
        this.activeCheck = activeCheck;
    }

    public boolean isShouldIgnoreSenderVerification() {
        return shouldIgnoreSenderVerification;
    }

    public void setShouldIgnoreSenderVerification(boolean shouldIgnoreSenderVerification) {
        this.shouldIgnoreSenderVerification = shouldIgnoreSenderVerification;
    }

    public Collection<MissionGoal> getGoals() {
        return goals;
    }

    public void setGoals(Collection<MissionGoal> goals) {
        this.goals = goals;
    }

    public MissionAction getMissionStart() {
        return missionStart;
    }

    public void setMissionStart(MissionAction missionStart) {
        this.missionStart = missionStart;
    }

    public MissionAction getMissionEnd() {
        return missionEnd;
    }

    public void setMissionEnd(MissionAction missionEnd) {
        this.missionEnd = missionEnd;
    }

    public MissionBranch getNextMission() {
        return nextMission;
    }

    public void setNextMission(MissionBranch nextMission) {
        this.nextMission = nextMission;
    }

    public Collection<MissionBranch> getBranchMissions() {
        return branchMissions;
    }

    public void setBranchMissions(Collection<MissionBranch> branchMissions) {
        this.branchMissions = branchMissions;
    }

    public MissionPosting getPosting() {
        return posting;
    }

    public void setPosting(MissionPosting posting) {
        this.posting = posting;
    }

    public MissionEmail getEmail() {
        return email;
    }

    public void setEmail(MissionEmail email) {
        this.email = email;
    }
}
