package org.lunasphere.hn.models.missions.email;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;

import java.util.ArrayList;
import java.util.Collection;

public class EmailAttachments {
    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "note")
    Collection<Note> notes;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "account")
    Collection<Account> accounts;

    @JacksonXmlElementWrapper(useWrapping = false)
    @JacksonXmlProperty(localName = "link")
    Collection<Link> links;

    public EmailAttachments(Collection<Note> notes, Collection<Account> accounts, Collection<Link> links) {
        this.notes = notes;
        this.accounts = accounts;
        this.links = links;
    }

    public EmailAttachments() {
        this.notes = new ArrayList<>();
        this.accounts = new ArrayList<>();
        this.links = new ArrayList<>();
    }

    public Collection<Note> getNotes() {
        return notes;
    }

    public void setNotes(Collection<Note> notes) {
        this.notes = notes;
    }

    public Collection<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection<Account> accounts) {
        this.accounts = accounts;
    }

    public Collection<Link> getLinks() {
        return links;
    }

    public void setLinks(Collection<Link> links) {
        this.links = links;
    }

    public void addAccount(Account acc) {
        this.accounts.add(acc);
    }

    public void addNote(Note note) {
        this.notes.add(note);
    }

    public void addLink(Link link) {
        this.links.add(link);
    }
}
