package org.lunasphere.hn.builders;

import org.lunasphere.hn.ErrorStrings;
import org.lunasphere.hn.SQLConfig;
import org.lunasphere.hn.models.extension.ExtensionInfo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ExtInfoBuilder {
    int extensionId;
    SQLConfig db;

    public ExtInfoBuilder(SQLConfig db, int extensionId) {
        this.extensionId = extensionId;
        this.db = db;
    }

    public Collection<Integer> getNodeIds() {
        Collection<Integer> retVal = new ArrayList<>();

        try (Connection conn = db.getConnection()) {
            String query = "SELECT \"nodeId\" FROM \"hn_CompNode\" WHERE \"extensionId\" = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, extensionId);

                ResultSet results = stmt.executeQuery();

                while (results.next()) {
                    retVal.add(results.getInt("nodeId"));
                }

            } catch (SQLException ex) {
                System.err.println(String.format(ErrorStrings.DB_PSTMT_ERR, query, ex.getMessage()));
            }

        } catch (SQLException ex) {
            System.err.println(String.format(ErrorStrings.DB_CONN_ERR, ex.getMessage()));
        }

        return retVal;
    }

    public Collection<Integer> getMissionIds() {
        Collection<Integer> retVal = new ArrayList<>();

        try (Connection conn = db.getConnection()) {
            String query = "SELECT \"missionId\" FROM \"hn_Mission\" WHERE \"extensionId\" = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, extensionId);

                ResultSet results = stmt.executeQuery();

                while (results.next()) {
                    retVal.add(results.getInt("missionId"));
                }

            } catch (SQLException ex) {
                System.err.println(String.format(ErrorStrings.DB_PSTMT_ERR, query, ex.getMessage()));
            }
        } catch (SQLException ex) {
            System.err.println(String.format(ErrorStrings.DB_CONN_ERR, ex.getMessage()));
        }

        return retVal;
    }

    protected Collection<String> getStartingNodes() {
        Collection<String> retVal = new ArrayList<>();

        try (Connection conn = db.getConnection()) {
            String query = "SELECT \"ln_Starting_Comp\".\"extensionId\", \"hn_CompNode\".\"id\" FROM \"ln_Starting_Comp\" INNER JOIN \"hn_CompNode\" ON \"ln_Starting_Comp\".\"nodeId\" = \"hn_CompNode\".\"nodeId\" WHERE \"ln_Starting_Comp\".\"extensionId\" = ?;";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, extensionId);

                ResultSet results = stmt.executeQuery();

                while (results.next()) {
                    retVal.add(results.getString("id"));
                }

            } catch (SQLException ex) {
                System.err.println(String.format(ErrorStrings.DB_PSTMT_ERR, query, ex.getMessage()));
            }
        } catch (SQLException ex) {
            System.err.println(String.format(ErrorStrings.DB_CONN_ERR, ex.getMessage()));
        }

        return retVal;
    }

    public ExtensionInfo Build() {
        System.out.println(String.format("ExtensionInfo builder job started for Extension with ID %d", extensionId));

        try (Connection conn = db.getConnection()) {

            String query = "SELECT \"extension_Info\".*, \"extension_Language\".\"lang\" FROM \"extension_Info\" INNER JOIN \"extension_Language\" ON \"extension_Info\".\"languageId\" = \"extension_Language\".\"langId\" WHERE \"extensionId\" = ?";

            Collection<String> startingVisibleNodes = getStartingNodes();

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, extensionId);

                ResultSet results = stmt.executeQuery();

                if (results.next()) {
                    /*
                    String language,
                    String name,
                    boolean allowSaves,
                    Collection<String> startingNodes,
                    String startingMission,
                    String startingActions,
                    String description,
                    boolean startsWithTutorial,
                    boolean hasIntro,
                    String startingTheme,
                    String introSong,
                    String workshopDescription,
                    int workshopVisibility,
                    String workshopTags,
                    String workshopPreviewImagePath,
                    String workshopPublishID
                     */

                    ExtensionInfo info = new ExtensionInfo(
                            results.getString("lang"),
                            results.getString("extensionName"),
                            results.getBoolean("allowSaves"),
                            startingVisibleNodes,
                            "",
                            "",
                            results.getString("description"),
                            false,
                            true,
                            "",
                            "",
                            results.getString("workshop_description"),
                            results.getInt("workshop_visibility"),
                            results.getString("workshop_tags"),
                            results.getString("workshop_img"),
                            ""
                    );

                    return info;

                }
            }

        } catch (SQLException ex) {
            System.err.println(String.format(ErrorStrings.DB_CONN_ERR, ex.getMessage()));
        }

        return null;
    }

}
