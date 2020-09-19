package org.lunasphere.hn.models.actionset.actions;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.lunasphere.hn.models.actionset.Action;

@JacksonXmlRootElement(localName = "AddAsset")
public class AddAsset extends Action {
    @JacksonXmlProperty(localName = "FileName", isAttribute = true)
    String fileName;

    @JacksonXmlProperty(localName = "FileContents", isAttribute = true)
    String fileContents;

    @JacksonXmlProperty(localName = "TargetComp", isAttribute = true)
    String targetComp;

    @JacksonXmlProperty(localName = "TargetComp", isAttribute = true)
    String targetFolderPath;

    public AddAsset(String fileName, String fileContents, String targetComp, String targetFolderPath) {
        this.fileName = fileName;
        this.fileContents = fileContents;
        this.targetComp = targetComp;
        this.targetFolderPath = targetFolderPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileContents() {
        return fileContents;
    }

    public void setFileContents(String fileContents) {
        this.fileContents = fileContents;
    }

    public String getTargetComp() {
        return targetComp;
    }

    public void setTargetComp(String targetComp) {
        this.targetComp = targetComp;
    }

    public String getTargetFolderPath() {
        return targetFolderPath;
    }

    public void setTargetFolderPath(String targetFolderPath) {
        this.targetFolderPath = targetFolderPath;
    }
}
