package org.lunasphere.hn.models.nodes.eos;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import org.lunasphere.hn.models.nodes.Account;
import org.lunasphere.hn.models.nodes.CompFile;

import java.util.Collection;

public class eosDevice {
    @JacksonXmlProperty(isAttribute = true)
    String name;
    @JacksonXmlProperty(isAttribute = true)
    String id;
    @JacksonXmlProperty(isAttribute = true)
    String icon;
    @JacksonXmlProperty(isAttribute = true)
    boolean empty;
    @JacksonXmlProperty(isAttribute = true)
    String passOverride;

    @JacksonXmlElementWrapper(useWrapping = false)
    Collection<String> note;

    @JacksonXmlElementWrapper(useWrapping = false)
    Collection<Account> mail;

    @JacksonXmlElementWrapper(useWrapping = false)
    Collection<CompFile> file;

    public eosDevice(String name, String id, String icon, boolean empty, String passOverride, Collection<String> note, Collection<Account> mail, Collection<CompFile> file) {
        this.name = name;
        this.id = id;
        this.icon = icon;
        this.empty = empty;
        this.passOverride = passOverride;
        this.note = note;
        this.mail = mail;
        this.file = file;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public boolean isEmpty() {
        return empty;
    }

    public void setEmpty(boolean empty) {
        this.empty = empty;
    }

    public String getPassOverride() {
        return passOverride;
    }

    public void setPassOverride(String passOverride) {
        this.passOverride = passOverride;
    }

    public Collection<String> getNote() {
        return note;
    }

    public void setNote(Collection<String> note) {
        this.note = note;
    }

    public Collection<Account> getMail() {
        return mail;
    }

    public void setMail(Collection<Account> mail) {
        this.mail = mail;
    }

    public Collection<CompFile> getFile() {
        return file;
    }

    public void setFile(Collection<CompFile> file) {
        this.file = file;
    }
}
