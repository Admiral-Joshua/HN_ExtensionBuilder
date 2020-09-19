package org.lunasphere.hn.models.actionset.actions;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "CopyAsset")
public class CopyAsset {
    @JacksonXmlProperty(localName = "DestFilePath", isAttribute = true)
    String destFilePath;
    @JacksonXmlProperty(localName = "DestComp", isAttribute = true)
    String destComp;
    @JacksonXmlProperty(localName = "SourceComp", isAttribute = true)
    String sourceComp;
    @JacksonXmlProperty(localName = "SourceFileName", isAttribute = true)
    String sourceFileName;
    @JacksonXmlProperty(localName = "SourceFilePath", isAttribute = true)
    String sourceFilePath;

    public CopyAsset(String destFilePath, String destComp, String sourceComp, String sourceFileName, String sourceFilePath) {
        this.destFilePath = destFilePath;
        this.destComp = destComp;
        this.sourceComp = sourceComp;
        this.sourceFileName = sourceFileName;
        this.sourceFilePath = sourceFilePath;
    }

    public String getDestFilePath() {
        return destFilePath;
    }

    public void setDestFilePath(String destFilePath) {
        this.destFilePath = destFilePath;
    }

    public String getDestComp() {
        return destComp;
    }

    public void setDestComp(String destComp) {
        this.destComp = destComp;
    }

    public String getSourceComp() {
        return sourceComp;
    }

    public void setSourceComp(String sourceComp) {
        this.sourceComp = sourceComp;
    }

    public String getSourceFileName() {
        return sourceFileName;
    }

    public void setSourceFileName(String sourceFileName) {
        this.sourceFileName = sourceFileName;
    }

    public String getSourceFilePath() {
        return sourceFilePath;
    }

    public void setSourceFilePath(String sourceFilePath) {
        this.sourceFilePath = sourceFilePath;
    }
}
