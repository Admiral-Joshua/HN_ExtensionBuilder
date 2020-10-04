package org.lunasphere.hn;

import org.postgresql.PGConnection;
import org.postgresql.PGNotification;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class ChangeDetector extends Thread{
    private Connection conn;
    private PGConnection pgconn;

    public ChangeDetector(SQLConfig db) {
        try {
            this.conn = db.getConnection();
            this.pgconn = (PGConnection) this.conn;
        } catch (SQLException ex) {
            System.err.println(ex.getMessage());
        }
    }

    public void run() {
        while (true) {
            try {
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT 1");
                rs.close();
                stmt.close();

                PGNotification notifications[] = pgconn.getNotifications();
                if (notifications != null) {
                    for (PGNotification notification : notifications) {
                        System.out.println("change detection fired: " + notification.getName());
                    }
                }

                Thread.sleep(1000);
            } catch (SQLException | InterruptedException ex) {
                System.err.println(ex.getMessage());
                ex.printStackTrace();
            }
        }
    }


}
