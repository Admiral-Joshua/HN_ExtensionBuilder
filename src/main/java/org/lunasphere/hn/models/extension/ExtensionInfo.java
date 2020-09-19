package org.lunasphere.hn.models.extension;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import java.util.Collection;
import java.util.stream.Collectors;

@JacksonXmlRootElement(localName = "HacknetExtension")
public class ExtensionInfo {

    @JacksonXmlProperty(localName = "Language")
    String language;

    @JacksonXmlProperty(localName = "Name")
    String name;

    @JacksonXmlProperty(localName = "AllowSaves")
    boolean allowSaves;

    @JacksonXmlProperty(localName = "StartingVisibleNodes")
    String startingNodes;

    @JacksonXmlProperty(localName = "StartingMission")
    String startingMission;

    @JacksonXmlProperty(localName = "StartingActions")
    String startingActions;

    @JacksonXmlProperty(localName = "Description")
    String description;

    //@JacksonXmlElementWrapper(useWrapping = false)
    // Collection<Faction> factions;

    @JacksonXmlProperty(localName = "StartsWithTutorial")
    boolean startsWithTutorial;

    @JacksonXmlProperty(localName = "HasIntroStartup")
    boolean hasIntro;

    @JacksonXmlProperty(localName = "StartingTheme")
    String startingTheme;

    @JacksonXmlProperty(localName = "IntroStartupSong")
    String introSong;

    /*
    SEQUENCER PROPERTIES HERE
     */

    @JacksonXmlProperty(localName = "WorkshopDescription")
    String workshopDescription;

    @JacksonXmlProperty(localName = "WorkshopVisibility")
    int workshopVisibility;

    @JacksonXmlProperty(localName = "WorkshopTags")
    String workshopTags;

    @JacksonXmlProperty(localName = "WorkshopPreviewImagePath")
    String workshopPreviewImagePath;

    @JacksonXmlProperty(localName = "WorkshopPublishID")
    String workshopPublishID;

    public ExtensionInfo(String language, String name, boolean allowSaves, Collection<String> startingNodes, String startingMission, String startingActions, String description, boolean startsWithTutorial, boolean hasIntro, String startingTheme, String introSong, String workshopDescription, int workshopVisibility, String workshopTags, String workshopPreviewImagePath, String workshopPublishID) {
        this.language = language;
        this.name = name;
        this.allowSaves = allowSaves;
        this.startingNodes = startingNodes.stream().collect(Collectors.joining(","));
        this.startingMission = startingMission;
        this.startingActions = startingActions;
        this.description = description;
        this.startsWithTutorial = startsWithTutorial;
        this.hasIntro = hasIntro;
        this.startingTheme = startingTheme;
        this.introSong = introSong;
        this.workshopDescription = workshopDescription;
        this.workshopVisibility = workshopVisibility;
        this.workshopTags = workshopTags;
        this.workshopPreviewImagePath = workshopPreviewImagePath;
        this.workshopPublishID = workshopPublishID;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAllowSaves() {
        return allowSaves;
    }

    public void setAllowSaves(boolean allowSaves) {
        this.allowSaves = allowSaves;
    }

    public String getStartingNodes() {
        return startingNodes;
    }

    public void setStartingNodes(String startingNodes) {
        this.startingNodes = startingNodes;
    }

    public String getStartingMission() {
        return startingMission;
    }

    public void setStartingMission(String startingMission) {
        this.startingMission = startingMission;
    }

    public String getStartingActions() {
        return startingActions;
    }

    public void setStartingActions(String startingActions) {
        this.startingActions = startingActions;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStartsWithTutorial() {
        return startsWithTutorial;
    }

    public void setStartsWithTutorial(boolean startsWithTutorial) {
        this.startsWithTutorial = startsWithTutorial;
    }

    public boolean isHasIntro() {
        return hasIntro;
    }

    public void setHasIntro(boolean hasIntro) {
        this.hasIntro = hasIntro;
    }

    public String getStartingTheme() {
        return startingTheme;
    }

    public void setStartingTheme(String startingTheme) {
        this.startingTheme = startingTheme;
    }

    public String getIntroSong() {
        return introSong;
    }

    public void setIntroSong(String introSong) {
        this.introSong = introSong;
    }

    public String getWorkshopDescription() {
        return workshopDescription;
    }

    public void setWorkshopDescription(String workshopDescription) {
        this.workshopDescription = workshopDescription;
    }

    public int getWorkshopVisibility() {
        return workshopVisibility;
    }

    public void setWorkshopVisibility(int workshopVisibility) {
        this.workshopVisibility = workshopVisibility;
    }

    public String getWorkshopTags() {
        return workshopTags;
    }

    public void setWorkshopTags(String workshopTags) {
        this.workshopTags = workshopTags;
    }

    public String getWorkshopPreviewImagePath() {
        return workshopPreviewImagePath;
    }

    public void setWorkshopPreviewImagePath(String workshopPreviewImagePath) {
        this.workshopPreviewImagePath = workshopPreviewImagePath;
    }

    public String getWorkshopPublishID() {
        return workshopPublishID;
    }

    public void setWorkshopPublishID(String workshopPublishID) {
        this.workshopPublishID = workshopPublishID;
    }
}
