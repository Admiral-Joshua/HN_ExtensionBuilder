package org.lunasphere.hn.models.nodes;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

public class Trace {
    @JacksonXmlProperty(isAttribute = true, localName = "time")
    float traceTime;

    public Trace(float traceTime) {
        this.traceTime = traceTime;
    }

    public float getTraceTime() {
        return traceTime;
    }

    public void setTraceTime(float traceTime) {
        this.traceTime = traceTime;
    }
}
