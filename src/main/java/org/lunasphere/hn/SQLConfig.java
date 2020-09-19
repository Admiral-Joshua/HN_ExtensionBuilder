package org.lunasphere.hn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConfig {
    private String host, instance, user, pass;
    private int port;

    public SQLConfig(String host, int port, String instance, String user, String pass) {
        try {
            Class.forName("org.postgresql.Driver");


            this.host = host;
            this.port = port;
            this.instance = instance;
            this.user = user;
            this.pass = pass;

        } catch (ClassNotFoundException ex) {
            System.err.println("FATAL ERROR - No PostgreSQL JDBC Driver Package found. Cannot continue!");
            System.exit(1);
        }
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(String.format("jdbc:postgresql://%s:%d/%s", this.host, this.port, this.instance), this.user, this.pass);
    }
}
