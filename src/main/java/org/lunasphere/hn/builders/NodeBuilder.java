package org.lunasphere.hn.builders;

import org.lunasphere.hn.ErrorStrings;
import org.lunasphere.hn.SQLConfig;
import org.lunasphere.hn.models.nodes.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class NodeBuilder {

    SQLConfig db;
    int nodeId;

    public NodeBuilder(SQLConfig db, int nodeId) {
        this.db = db;
        this.nodeId = nodeId;
    }

    protected Collection<Integer> getPorts() {
        Collection<Integer> retVal = new ArrayList<>();

        try (Connection conn = db.getConnection()) {

            String query = "SELECT \"nodeId\", \"port\" FROM \"ln_Comp_Ports\" INNER JOIN \"hn_Ports\" ON \"hn_Ports\".\"portId\" = \"ln_Comp_Ports\".\"portId\" WHERE \"nodeId\" = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, nodeId);

                ResultSet results = stmt.executeQuery();

                while (results.next()) {
                    int portNumber = results.getInt("port");
                    retVal.add(portNumber);
                }
            } catch (SQLException ex) {
                System.err.println(String.format(ErrorStrings.DB_PSTMT_ERR, "", ex.getMessage()));
            }
        } catch (SQLException ex) {
            System.err.println(String.format(ErrorStrings.DB_CONN_ERR, ex.getMessage()));
        }

        return retVal;
    }

    protected Collection<CompFile> getFiles() {
        Collection<CompFile> retVal = new ArrayList<>();

        try (Connection conn = db.getConnection()) {
            String query = "SELECT \"hn_CompFile\".* FROM \"ln_Comp_File\" INNER JOIN \"hn_CompFile\" ON \"hn_CompFile\".\"fileId\" = \"ln_Comp_File\".\"fileId\" WHERE \"nodeId\" = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, nodeId);

                ResultSet results = stmt.executeQuery();

                while (results.next()) {
                    //String path, String name, String contents
                    CompFile file = new CompFile(
                            results.getString("path"),
                            results.getString("name"),
                            results.getString("contents")
                    );
                    retVal.add(file);
                }

            } catch (SQLException ex) {
                System.err.println(String.format(ErrorStrings.DB_PSTMT_ERR, query, ex.getMessage()));
            }
        } catch (SQLException ex) {
            System.err.println(String.format(ErrorStrings.DB_CONN_ERR, ex.getMessage()));
        }

        return retVal;
    }

    protected Admin getAdminInfo(int adminInfoId) {
        if (adminInfoId > 0) {
            try (Connection conn = db.getConnection()) {

                String query = "SELECT * FROM \"hn_Admin\" INNER JOIN \"hn_AdminType\" ON \"hn_AdminType\".\"adminTypeId\" = \"hn_Admin\".\"adminTypeId\" WHERE \"adminId\" = ?";

                try (PreparedStatement stmt = conn.prepareStatement(query)) {
                    stmt.setInt(1, adminInfoId);

                    ResultSet results = stmt.executeQuery();

                    if (results.next()) {
                        //String type, boolean resetPassword, boolean isSuper
                        return new Admin(
                                results.getString("adminType"),
                                results.getBoolean("resetPassword"),
                                results.getBoolean("isSuper")
                        );
                    }

                } catch (SQLException ex) {
                    System.err.println(String.format(ErrorStrings.DB_PSTMT_ERR, query, ex.getMessage()));
                }

            } catch (SQLException ex) {
                System.err.println(String.format(ErrorStrings.DB_CONN_ERR, ex.getMessage()));
            }
        }

        return null;
    }

    protected void updatePortRemaps(CompNode node) {
        try (Connection conn = db.getConnection()) {

            String query = "SELECT \"hn_Ports\".\"portType\" as \"portName\", \"hn_PortRemap\".\"port\" as \"newValue\" FROM \"hn_PortRemap\" INNER JOIN \"hn_Ports\" ON \"hn_Ports\".\"portId\" = \"hn_PortRemap\".\"portId\" WHERE \"nodeId\" = ?;";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, nodeId);

                ResultSet results = stmt.executeQuery();

                while (results.next()) {
                    String portName = results.getString("portName").toLowerCase();
                    int newPort = results.getInt("newValue");

                    node.remapPort(portName, newPort);

                }

            } catch (SQLException ex) {
                System.err.println(String.format(ErrorStrings.DB_PSTMT_ERR, query, ex.getMessage()));
            }

        } catch (SQLException ex) {
            System.err.println(String.format(ErrorStrings.DB_CONN_ERR, ex.getMessage()));
        }
    }

    public CompNode Build() {
        System.out.println(String.format("NodeBuilder job started for Node with ID: %d", nodeId));

        try (Connection conn = db.getConnection()) {

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM \"hn_CompNode\" WHERE \"nodeId\" = ?");
            stmt.setInt(1, nodeId);

            Collection<CompFile> files = getFiles();
            Collection<Integer> ports = getPorts();

            ResultSet results = stmt.executeQuery();
            if (results.next()) {
                /*
                String id,
                String name,
                String ip,
                int security,
                boolean allowsDefaultBootModule,
                String icon,
                int type,
                AdminPass adminPass,
                Collection<Account> accounts,
                Collection<Integer> ports,
                PortsForCrack portsForCrack,
                Trace trace,
                Admin admin,
                Collection<String> portRemap,
                boolean tracker,
                Collection<Dlink> dlink,
                Collection<CompFile> file
                 */

                Trace trace = new Trace(results.getFloat("traceTime"));

                AdminPass aPass = new AdminPass(results.getString("adminPass"));

                PortsForCrack pCrack = new PortsForCrack(results.getInt("portsForCrack"));


                //String type, boolean resetPassword, boolean isSuper
                Admin admin = getAdminInfo(results.getInt("adminInfoId"));

                CompNode node = new CompNode(
                        results.getString("id"),
                        results.getString("name"),
                        results.getString("ip"),
                        results.getInt("securityLevel"),
                        results.getBoolean("allowsDefaultBootModule"),
                        results.getString("icon"),
                        results.getInt("typeId"),
                        aPass,
                        new ArrayList<>(),
                        ports,
                        pCrack,
                        trace,
                        admin,
                        new ArrayList<>(),
                        results.getBoolean("tracker"),
                        new ArrayList<>(),
                        files
                        );

                // Fetch and load in port re-mappings.
                updatePortRemaps(node);

                return node;
            }
        } catch (SQLException ex) {
            System.err.println(String.format(ErrorStrings.DB_CONN_ERR, ex.getMessage()));
        }
        return null;
    }
}
