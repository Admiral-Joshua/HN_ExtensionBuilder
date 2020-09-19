package org.lunasphere.hn.models.nodes;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlText;

public class CompFile {
    @JacksonXmlProperty(isAttribute = true)
    String path;
    @JacksonXmlProperty(isAttribute = true)
    String name;
    @JacksonXmlText
    String contents;

    public CompFile(String path, String name, String contents) {
        this.path = path;
        this.name = name;
        this.contents = contents;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }
}
