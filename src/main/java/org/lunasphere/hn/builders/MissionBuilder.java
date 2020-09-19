package org.lunasphere.hn.builders;

import org.lunasphere.hn.ErrorStrings;
import org.lunasphere.hn.SQLConfig;
import org.lunasphere.hn.models.missions.Mission;
import org.lunasphere.hn.models.missions.MissionAction;
import org.lunasphere.hn.models.missions.goals.MissionGoal;
import org.lunasphere.hn.models.missions.MissionPosting;
import org.lunasphere.hn.models.missions.email.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class MissionBuilder {

    int missionId;
    SQLConfig db;

    public MissionBuilder(SQLConfig db, int missionId) {
        this.missionId = missionId;
        this.db = db;
    }

    public Collection<MissionGoal> getGoals() {
        Collection<MissionGoal> retVal = new ArrayList<>();
        /*
        String type;
        String target;
        String path;
        String file;
        String keyword;
        boolean removal;
        boolean caseSensitive;
        float time;
        String destTarget;
        String destPath;
        boolean decrypt;
        String decryptPass;
        String owner;
        String degree;
        String uni;
        float gpa;
        String mailServer;
        String recipient;
        String subject;
         */

        try (Connection conn = db.getConnection()) {
            String query = "SELECT \"hn_MGoalType\".\"typeText\",\"hn_MissionGoal\".*,\"hn_CompNode\".\"id\" as \"targetNodeName\" FROM \"ln_Goal_Mission\" INNER JOIN \"hn_MissionGoal\" ON \"hn_MissionGoal\".\"goalId\" = \"ln_Goal_Mission\".\"goalId\" INNER JOIN \"hn_MGoalType\" ON \"hn_MGoalType\".\"typeId\" = \"hn_MissionGoal\".\"typeId\" LEFT JOIN \"hn_CompNode\" ON \"hn_CompNode\".\"nodeId\" = \"hn_MissionGoal\".\"targetNodeId\" WHERE \"ln_Goal_Mission\".\"missionId\" = ?;";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, missionId);

                ResultSet results = stmt.executeQuery();

                while (results.next()) {
                    MissionGoal goal = new MissionGoal(results.getString("typeText"));

                    switch(goal.getType()) {
                        case "delay":
                            goal.setTime(results.getFloat("delay"));
                            break;
                        case "filechange":
                            goal.setKeyword(results.getString("keyword"));
                            goal.setRemoval(results.getBoolean("removal"));
                            goal.setCaseSensitive(results.getBoolean("caseSensitive"));
                            break;
                        case "filedownload":
                        case "filedeletion":
                            goal.setFile(results.getString("file"));
                        case "clearfolder":
                            goal.setTarget(results.getString("targetNodeName"));
                            break;
                        case "fileupload":
                            // TODO: Database requires an update to include Destination computer ID.
                            // TODO: Database missing columns for whether this task requires decryption of any data.
                            //goal.setDestTarget(results.getString("destNodeName"));
                            //goal.setDestPath("");
                            //goal.setDecrypt(results.getBoolean("d"));
                            goal.setTarget(results.getString("targetNodeName"));
                            goal.setFile(results.getString("file"));
                            goal.setPath(results.getString("path"));
                            break;
                        case "getadmin":
                        case "getadminpasswordstring":
                            goal.setTarget(results.getString("targetNodeName"));
                            break;
                        case "getstring":
                        case "hasFlag":
                            goal.setTarget(results.getString("target"));
                            break;
                        case "AddDegree":
                            goal.setDegree(results.getString("degree"));
                            goal.setUni(results.getString("uni"));
                            goal.setGpa(results.getFloat("gpa"));
                        case "WipeDegrees":
                            goal.setOwner(results.getString("owner"));
                            break;
                        case "sendemail":
                            goal.setMailServer(results.getString("mailServer"));
                            goal.setRecipient(results.getString("recipient"));
                            goal.setSubject(results.getString("subject"));
                            break;
                    }

                    retVal.add(goal);
                }

            } catch (SQLException ex) {
                System.err.println(String.format(ErrorStrings.DB_PSTMT_ERR, query, ex.getMessage()));
            }
        } catch (SQLException ex) {
            System.err.println(String.format(ErrorStrings.DB_CONN_ERR, ex.getMessage()));
        }

        return retVal;
    }

    protected MissionEmail getEmail(int emailId) {
        System.out.println(String.format("EmailBuilder detected email for Mission %d -> Building email with ID %d", missionId, emailId));
        try (Connection conn = db.getConnection()) {
            String query = "SELECT * FROM \"hn_Email\" WHERE \"emailId\" = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, emailId);

                ResultSet result = stmt.executeQuery();

                if (result.next()) {
                    MissionEmail email = new MissionEmail(
                            result.getString("sender"),
                            result.getString("subject"),
                            result.getString("body")
                    );

                    String attachmentQuery = "SELECT \"hn_EmailAttachment\".* FROM \"ln_attachment_email\" INNER JOIN \"hn_EmailAttachment\" ON \"ln_attachment_email\".\"attachmentId\" = \"hn_EmailAttachment\".\"attachmentId\" WHERE \"ln_attachment_email\".\"emailId\" = ?;";

                    try (PreparedStatement attachmentStmt = conn.prepareStatement(attachmentQuery)) {
                        attachmentStmt.setInt(1, result.getInt(emailId));

                        ResultSet results = attachmentStmt.executeQuery();

                        while (results.next()) {
                            EmailAttachment attachment;

                            switch (results.getInt("typeId")) {
                                case 1:
                                    attachment = new Note(results.getString("title"), results.getString("content"));
                                    break;
                                case 2:
                                    attachment = new Link(results.getString("comp"));
                                    break;
                                default:
                                    attachment = new Account(results.getString("comp"), results.getString("user"), results.getString("pass"));
                            }

                            email.addAttachment(attachment);
                        }

                    } catch (SQLException ex) {
                        System.err.println(String.format(ErrorStrings.DB_PSTMT_ERR, query, ex.getMessage()));
                    }

                    return email;
                }

            } catch (SQLException ex) {
                System.err.println(String.format(ErrorStrings.DB_PSTMT_ERR, query, ex.getMessage()));
            }

        } catch (SQLException ex) {
            System.err.println(String.format(ErrorStrings.DB_CONN_ERR, ex.getMessage()));
        }

        return null;
    }

    protected MissionPosting getPosting(int postingId) {
        System.out.println(String.format("PostingBuilder Detected BoardPost for Mission %d -> Building Posting with ID %d", missionId, postingId));

        try (Connection conn = db.getConnection()) {

            String query = "SELECT * FROM \"hn_BoardPost\" WHERE \"postingId\" = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, postingId);

                ResultSet result = stmt.executeQuery();

                if (result.next()) {
                    /*
                    String title,
                    String reqs,
                    int requiredRank,
                    String content
                     */
                    MissionPosting posting = new MissionPosting(
                            result.getString("title"),
                            result.getString("reqs"),
                            result.getInt("requiredRank"),
                            result.getString("content")
                    );

                    return posting;
                }

            } catch (SQLException ex) {
                System.err.println(String.format(ErrorStrings.DB_PSTMT_ERR, query, ex.getMessage()));
            }

        } catch (SQLException ex) {
            System.err.println(String.format(ErrorStrings.DB_CONN_ERR, ex.getMessage()));
        }

        return null;
    }

    public Mission Build() {
        System.out.println(String.format("MissionBuilder job started for Mission with ID %d", missionId));
        try (Connection conn = db.getConnection()) {

            String query = "SELECT * FROM \"hn_Mission\" WHERE \"missionId\" = ?";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, missionId);

                ResultSet results = stmt.executeQuery();

                if (results.next()) {
                    /*
                    String id,
                    boolean activeCheck,
                    boolean shouldIgnoreSenderVerification,
                    Collection<MissionGoal> goals,
                    MissionAction missionStart,
                    MissionAction missionEnd,
                    MissionEmail email
                     */

                    Collection<MissionGoal> goals = getGoals();

                    MissionAction missionStart = new MissionAction(results.getString("missionStart"));
                    MissionAction missionEnd = new MissionAction(results.getString("missionEnd"));

                    int emailId = results.getInt("emailId");
                    MissionEmail email = null;
                    if (emailId > 0) {
                        email = getEmail(emailId);
                    }

                    Mission mission = new Mission(
                            results.getString("id"),
                            results.getBoolean("activeCheck"),
                            results.getBoolean("shouldIgnoreSenderVerification"),
                            goals,
                            missionStart,
                            missionEnd,
                            email
                    );

                    int postingId = results.getInt("postingId");

                    if (postingId > 0) {
                        mission.setPosting(getPosting(postingId));
                    }

                    return mission;
                }

            } catch (SQLException ex) {
                System.err.println(String.format(ErrorStrings.DB_PSTMT_ERR, query, ex.getMessage()));
            }

        } catch (SQLException ex) {
            System.err.println(String.format(ErrorStrings.DB_CONN_ERR, ex.getMessage()));
        }

        return null;
    }

}
