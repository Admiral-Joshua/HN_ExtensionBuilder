package org.lunasphere.hn.builders;

import org.lunasphere.hn.ErrorStrings;
import org.lunasphere.hn.SQLConfig;
import org.lunasphere.hn.models.extension.ExtensionInfo;
import org.lunasphere.hn.models.internal.DatabaseChange;

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

    public Collection<Integer> getThemeIds() {
        Collection<Integer> retVal = new ArrayList<>();

        try (Connection conn = db.getConnection()) {
            PreparedStatement stmt = conn.prepareStatement("SELECT \"themeId\" FROM \"hn_Theme\" WHERE \"extensionId\" = ?");
            stmt.setInt(1, extensionId);

            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                retVal.add(results.getInt(1));
            }
        } catch (SQLException ex) {
            System.err.println(String.format(ErrorStrings.DB_CONN_ERR, ex.getMessage()));
        }

        return retVal;
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

            String query = "SELECT \"extension_Info\".*,\"extension_Language\".\"lang\",\"hn_Mission\".\"id\" as \"startingMission\",\"hn_Theme\".\"id\" as \"startingTheme\",\"hn_Music\".\"title\" FROM \"extension_Info\" INNER JOIN \"extension_Language\" ON \"extension_Language\".\"langId\" = \"extension_Info\".\"languageId\" LEFT JOIN \"hn_Mission\" ON \"extension_Info\".\"startingMissionId\" = \"hn_Mission\".\"missionId\" LEFT JOIN \"hn_Theme\" ON \"extension_Info\".\"startingThemeId\" = \"hn_Theme\".\"themeId\" LEFT JOIN \"hn_Music\" ON \"hn_Music\".\"musicId\" = \"extension_Info\".\"startingMusic\" WHERE \"extension_Info\".\"extensionId\" = ?";

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
                    String startingMusic;
                    int startingMusicId = results.getInt("startingMusic");
                    if (startingMusicId > 23) {
                        startingMusic = String.format("Music/%d.ogg", startingMusicId);
                    } else {
                        startingMusic = results.getString("title");
                    }

                    String startingMission = results.getString("startingMission");
                    if (!startingMission.equals("NONE")) {
                        startingMission = String.format("Missions/%s.xml", startingMission);
                    }

                    /*String startingActions = results.getString("startingAction");
                    if (startingActions.length() > 0) {
                        startingActions = String.format("Actions/%s.xml", startingActions);
                    } else {
                        startingActions = "NONE";
                    }*/
                    String startingActions = "NONE";

                    String startingTheme = results.getString("startingTheme");
                    if (results.wasNull()) {
                        startingTheme = "HacknetBlue";
                    } else {
                        startingTheme = String.format("Themes/%s.xml", startingTheme);
                    }

                    ExtensionInfo info = new ExtensionInfo(
                            results.getString("lang"),
                            results.getString("extensionName"),
                            results.getBoolean("allowSaves"),
                            startingVisibleNodes,
                            startingMission,
                            startingActions,
                            results.getString("description"),
                            false,
                            false,
                            startingTheme,
                            startingMusic,
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

    private int getUserId(Connection conn) throws SQLException {
        int userId = 0;

        PreparedStatement getUserStmt = conn.prepareStatement("SELECT \"userId\" FROM \"user_Extension\" WHERE \"extensionId\" = ?");
        getUserStmt.setInt(1, extensionId);

        ResultSet getUserResult = getUserStmt.executeQuery();

        if (getUserResult.next()) {
            userId = getUserResult.getInt("userId");
        }

        return userId;
    }

    public Collection<Integer> getTrackIds() {
        Collection<Integer> retVal = new ArrayList<>();
        try (Connection conn = db.getConnection()) {

            int userId = getUserId(conn);

            String query = "SELECT * FROM \"hn_Music\" WHERE \"ownerId\" = ?";
            try (PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, userId);

                ResultSet results = stmt.executeQuery();

                while (results.next()) {
                    retVal.add(results.getInt("musicId"));
                }
            } catch (SQLException ex) {
                System.err.println(String.format(ErrorStrings.DB_PSTMT_ERR, query, ex.getMessage()));
            }
        } catch (SQLException ex) {
            System.err.println(String.format(ErrorStrings.DB_CONN_ERR, ex.getMessage()));
        }
        return retVal;
    }

    public Collection<DatabaseChange> detectChanges() {
        Collection<DatabaseChange> retVal = new ArrayList<>();

        try (Connection conn = db.getConnection()) {

            PreparedStatement getUserStmt = conn.prepareStatement("SELECT \"userId\" FROM \"user_Extension\" WHERE \"extensionId\" = ?");
            getUserStmt.setInt(1, extensionId);

            ResultSet getUserResult = getUserStmt.executeQuery();

            int userId = 0;

            if (getUserResult.next()) {
                userId = getUserResult.getInt("userId");
            }

            PreparedStatement stmt = conn.prepareStatement("SELECT * FROM change_log WHERE (last_build <= last_change or last_build IS NULL) and (meta_id = ? OR meta_id = ?)");
            stmt.setInt(1, extensionId);
            stmt.setInt(2, userId);
            System.out.println(stmt);

            ResultSet results = stmt.executeQuery();

            while (results.next()) {
                DatabaseChange change = new DatabaseChange(
                        results.getLong("change_id"),
                        results.getInt("meta_id"),
                        results.getInt("object_id"),
                        DatabaseChange.ChangeObjectType.fromInt(results.getInt("object_type_id")),
                        results.getTimestamp("last_change"),
                        results.getTimestamp("last_build")
                );

                retVal.add(change);
            }
        } catch (SQLException ex) {
            System.err.println(String.format(ErrorStrings.DB_CONN_ERR, ex.getMessage()));
        }

        return retVal;
    }

}
