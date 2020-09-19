package org.lunasphere.hn.models.missions.goals;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "goal")
public class MissionGoal {

    @JacksonXmlProperty(isAttribute = true)
    String type;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(isAttribute = true)
    String target;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(isAttribute = true)
    String path;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(isAttribute = true)
    String file;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(isAttribute = true)
    String keyword;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(isAttribute = true)
    boolean removal;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(isAttribute = true)
    boolean caseSensitive;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(isAttribute = true)
    float time;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(isAttribute = true)
    String destTarget;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(isAttribute = true)
    String destPath;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(isAttribute = true)
    boolean decrypt;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(isAttribute = true)
    String decryptPass;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(isAttribute = true)
    String owner;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(isAttribute = true)
    String degree;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(isAttribute = true)
    String uni;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(isAttribute = true)
    float gpa;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(isAttribute = true)
    String mailServer;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(isAttribute = true)
    String recipient;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JacksonXmlProperty(isAttribute = true)
    String subject;

    public MissionGoal(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public String getKeyword() {
        return keyword;
    }

    public void setKeyword(String keyword) {
        this.keyword = keyword;
    }

    public boolean isRemoval() {
        return removal;
    }

    public void setRemoval(boolean removal) {
        this.removal = removal;
    }

    public boolean isCaseSensitive() {
        return caseSensitive;
    }

    public void setCaseSensitive(boolean caseSensitive) {
        this.caseSensitive = caseSensitive;
    }

    public float getTime() {
        return time;
    }

    public void setTime(float time) {
        this.time = time;
    }

    public String getDestTarget() {
        return destTarget;
    }

    public void setDestTarget(String destTarget) {
        this.destTarget = destTarget;
    }

    public String getDestPath() {
        return destPath;
    }

    public void setDestPath(String destPath) {
        this.destPath = destPath;
    }

    public boolean isDecrypt() {
        return decrypt;
    }

    public void setDecrypt(boolean decrypt) {
        this.decrypt = decrypt;
    }

    public String getDecryptPass() {
        return decryptPass;
    }

    public void setDecryptPass(String decryptPass) {
        this.decryptPass = decryptPass;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public String getUni() {
        return uni;
    }

    public void setUni(String uni) {
        this.uni = uni;
    }

    public float getGpa() {
        return gpa;
    }

    public void setGpa(float gpa) {
        this.gpa = gpa;
    }

    public String getMailServer() {
        return mailServer;
    }

    public void setMailServer(String mailServer) {
        this.mailServer = mailServer;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    //float            delay
}
