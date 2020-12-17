package org.lunasphere.hn;

import com.fasterxml.jackson.dataformat.xml.XmlMapper;

import javax.xml.bind.annotation.XmlElement;
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConfig {
    private String host, instance, user, pass;
    private int port;

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(String.format("jdbc:postgresql://%s:%d/%s", this.host, this.port, this.instance), this.user, this.pass);
    }

    public SQLConfig() {}

    public SQLConfig(String host, String instance, String user, String pass, int port) {
        this.host = host;
        this.instance = instance;
        this.user = user;
        this.pass = pass;
        this.port = port;
    }

    protected static String inputStreamToString(InputStream is) throws IOException {
        StringBuilder builder = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));

        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line);
        }
        return builder.toString();
    }

    public static SQLConfig loadConfig(String confPath) throws IOException {
        XmlMapper mapper = new XmlMapper();

        return mapper.readValue(inputStreamToString(new FileInputStream(new File(confPath))), SQLConfig.class);
    }

    @XmlElement(name="host")
    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    @XmlElement(name="instance")
    public String getInstance() {
        return instance;
    }

    public void setInstance(String instance) {
        this.instance = instance;
    }

    @XmlElement(name="user")
    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @XmlElement(name="pass")
    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    @XmlElement(name="port")
    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
