package org.lunasphere.hn.models.nodes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import org.lunasphere.hn.models.nodes.eos.eosDevice;

import java.util.Collection;
import java.util.stream.Collectors;

@JacksonXmlRootElement(localName = "Computer")
public class CompNode {
    @JacksonXmlProperty(isAttribute = true)
    String id;
    @JacksonXmlProperty(isAttribute = true)
    String name;
    @JacksonXmlProperty(isAttribute = true)
    String ip;
    @JacksonXmlProperty(isAttribute = true)
    int security;
    @JacksonXmlProperty(isAttribute = true)
    boolean allowsDefaultBootModule;
    @JacksonXmlProperty(isAttribute = true)
    String icon;
    @JacksonXmlProperty(isAttribute = true)
    int type;

    AdminPass adminPass;

    @JsonInclude(Include.NON_NULL)
    @JacksonXmlElementWrapper(useWrapping = false)
    Collection<Account> accounts;

    String ports;

    @JsonInclude(Include.NON_NULL)
    PortsForCrack portsForCrack;

    Trace trace;

    @JsonInclude(Include.NON_NULL)
    Admin admin;

    @JsonInclude(Include.NON_NULL)
    String portRemap;

    @JsonInclude(Include.NON_NULL)
    Tracker tracker;

    @JacksonXmlElementWrapper(useWrapping = false)
    Collection<Dlink> dlink;

    @JsonInclude(Include.NON_NULL)
    @JacksonXmlElementWrapper(useWrapping = false)
    eosDevice eosDevice;

    @JacksonXmlElementWrapper(useWrapping = false)
    Collection<CompFile> file;

    public CompNode(String id, String name, String ip, int security, boolean allowsDefaultBootModule, String icon, int type, AdminPass adminPass, Collection<Account> accounts, Collection<Integer> ports, PortsForCrack portsForCrack, Trace trace, Admin admin, Collection<String> portRemap, boolean tracker, Collection<Dlink> dlink, Collection<CompFile> file) {
        this.id = id;
        this.name = name;
        this.ip = ip;
        this.security = security;
        this.allowsDefaultBootModule = allowsDefaultBootModule;
        this.icon = icon;
        this.type = type;
        this.adminPass = adminPass;
        this.accounts = accounts;
        this.ports = ports.stream().map(val -> Integer.toString(val)).collect(Collectors.joining(","));
        this.portsForCrack = portsForCrack;
        this.trace = trace;
        this.admin = admin;
        this.portRemap = portRemap.stream().collect(Collectors.joining(","));
        if (tracker) {
            this.tracker = new Tracker();
        }
        this.dlink = dlink;
        this.file = file;
    }

    public void addLink(Dlink link) {
        this.dlink.add(link);
    }

    public void remapPort(String portName, int port) {
        if (this.portRemap.length() > 0) {
            this.portRemap += ",";
        }

        this.portRemap += (portName + "=" + Integer.toString(port));
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getSecurity() {
        return security;
    }

    public void setSecurity(int security) {
        this.security = security;
    }

    public boolean isAllowsDefaultBootModule() {
        return allowsDefaultBootModule;
    }

    public void setAllowsDefaultBootModule(boolean allowsDefaultBootModule) {
        this.allowsDefaultBootModule = allowsDefaultBootModule;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public AdminPass getAdminPass() {
        return adminPass;
    }

    public void setAdminPass(AdminPass adminPass) {
        this.adminPass = adminPass;
    }

    public Collection<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(Collection<Account> accounts) {
        this.accounts = accounts;
    }

    public String getPorts() {
        return ports;
    }

    public void setPorts(String ports) {
        this.ports = ports;
    }

    public PortsForCrack getPortsForCrack() {
        return portsForCrack;
    }

    public void setPortsForCrack(PortsForCrack portsForCrack) {
        this.portsForCrack = portsForCrack;
    }

    public Trace getTrace() {
        return trace;
    }

    public void setTrace(Trace trace) {
        this.trace = trace;
    }

    public Admin getAdmin() {
        return admin;
    }

    public void setAdmin(Admin admin) {
        this.admin = admin;
    }

    public String getPortRemap() {
        return portRemap;
    }

    public void setPortRemap(String portRemap) {
        this.portRemap = portRemap;
    }

    public boolean hasTracker() {
        return this.tracker != null;
    }

    public void setTracker(Tracker tracker) {
        this.tracker = tracker;
    }

    public Collection<Dlink> getDlink() {
        return dlink;
    }

    public void setDlink(Collection<Dlink> dlink) {
        this.dlink = dlink;
    }

    public org.lunasphere.hn.models.nodes.eos.eosDevice getEosDevice() {
        return eosDevice;
    }

    public void setEosDevice(org.lunasphere.hn.models.nodes.eos.eosDevice eosDevice) {
        this.eosDevice = eosDevice;
    }

    public Collection<CompFile> getFile() {
        return file;
    }

    public void setFile(Collection<CompFile> file) {
        this.file = file;
    }
}
