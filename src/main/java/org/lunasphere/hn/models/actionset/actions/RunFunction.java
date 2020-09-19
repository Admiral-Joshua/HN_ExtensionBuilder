package org.lunasphere.hn.models.actionset.actions;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.lunasphere.hn.models.actionset.Action;

@JacksonXmlRootElement(localName = "RunFunction")
public class RunFunction extends Action {

    @JacksonXmlProperty(localName = "FunctionName", isAttribute = true)
    String functionName;

    @JacksonXmlProperty(localName = "FunctionValue", isAttribute = true)
    int functionValue;

    public RunFunction(String functionName, int functionValue) {
        this.functionName = functionName;
        this.functionValue = functionValue;
    }

    public String getFunctionName() {
        return functionName;
    }

    public void setFunctionName(String functionName) {
        this.functionName = functionName;
    }

    public int getFunctionValue() {
        return functionValue;
    }

    public void setFunctionValue(int functionValue) {
        this.functionValue = functionValue;
    }
}
