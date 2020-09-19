package org.lunasphere.hn.models.actionset.actions;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;
import org.lunasphere.hn.models.actionset.Action;

public class AddIRCMessage extends Action {
    @JacksonXmlProperty(localName = "Author", isAttribute = true)
    String author;
    @JacksonXmlProperty(localName = "TargetComp", isAttribute = true)
    String targetComp;
    @JacksonXmlProperty(localName = "Delay", isAttribute = true)
    Float delay;
    @JacksonXmlText
    String content;

    public AddIRCMessage(String author, String targetComp, Float delay, String content) {
        this.author = author;
        this.targetComp = targetComp;
        this.delay = delay;
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getTargetComp() {
        return targetComp;
    }

    public void setTargetComp(String targetComp) {
        this.targetComp = targetComp;
    }

    public Float getDelay() {
        return delay;
    }

    public void setDelay(Float delay) {
        this.delay = delay;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
