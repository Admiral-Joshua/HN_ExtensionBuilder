package org.lunasphere.hn.models.missions;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class MissionPosting {
    @JacksonXmlProperty(localName = "title", isAttribute = true)
    String title;

    @JacksonXmlProperty(localName = "reqs", isAttribute = true)
    String reqs;

    @JacksonXmlProperty(localName = "requiredRank", isAttribute = true)
    int requiredRank;

    @JacksonXmlText
    String content;

    public MissionPosting(String title, String reqs, int requiredRank, String content) {
        this.title = title;
        this.reqs = reqs;
        this.requiredRank = requiredRank;
        this.content = content;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReqs() {
        return reqs;
    }

    public void setReqs(String reqs) {
        this.reqs = reqs;
    }

    public int getRequiredRank() {
        return requiredRank;
    }

    public void setRequiredRank(int requiredRank) {
        this.requiredRank = requiredRank;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
