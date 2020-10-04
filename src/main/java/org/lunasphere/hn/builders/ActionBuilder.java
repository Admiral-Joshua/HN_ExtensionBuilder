package org.lunasphere.hn.builders;

import org.lunasphere.hn.ErrorStrings;
import org.lunasphere.hn.SQLConfig;
import org.lunasphere.hn.models.actionset.Action;
import org.lunasphere.hn.models.actionset.ActionCondition;
import org.lunasphere.hn.models.actionset.ConditionalActionSet;
import org.lunasphere.hn.models.actionset.conditions.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;

public class ActionBuilder {
    int actionSetId;
    SQLConfig db;

    public ActionBuilder(SQLConfig db, int actionSetId) {
        this.actionSetId = actionSetId;
        this.db = db;
    }

    protected Collection<Action> getActions(int conditionId) {
        Collection<Action> retVal = new ArrayList<>();

        try (Connection conn = db.getConnection()) {
            String query = "";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, actionSetId);

                ResultSet results = stmt.executeQuery();
            } catch (SQLException ex) {
                System.err.println(String.format(ErrorStrings.DB_PSTMT_ERR, query, ex.getMessage()));
            }
        } catch (SQLException ex) {
            System.err.println(String.format(ErrorStrings.DB_CONN_ERR, ex.getMessage()));
        }

            return retVal;
    }

    protected Collection<ActionCondition> getConditions() {
        Collection<ActionCondition> retVal = new ArrayList<>();

        try (Connection conn = db.getConnection()) {

            String query = "SELECT \"hn_ActionCondition\".*, \"hn_ConditionType\".\"typeText\", \"hn_CompNode\".\"id\" FROM \"hn_ActionCondition\" LEFT JOIN \"hn_CompNode\" ON \"hn_CompNode\".\"nodeId\" = \"hn_ActionCondition\".\"targetNodeId\" INNER JOIN \"hn_ConditionType\" ON \"hn_ActionCondition\".\"typeId\" = \"hn_ConditionType\".\"typeId\" WHERE \"actionSetId\" = ?;";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, actionSetId);

                ResultSet results = stmt.executeQuery();

                while (results.next()) {
                    String conditionType = results.getString("typeText");

                    ActionCondition condition;

                    String target;
                    String requiredFlags;
                    boolean needsMissionComplete;

                    switch (conditionType) {
                        case "OnConnect":
                            condition = new OnConnect(
                                    results.getString("id")
                            );
                            break;
                        case "HasFlags":
                            condition = new HasFlags(
                                    results.getString("requiredFlags")
                            );
                            break;
                        case "OnDisconnect":
                            condition = new OnDisconnect(
                                    results.getString("id")
                            );
                            break;
                        case "OnAdminGained":
                            condition = new OnAdminGained(
                                    results.getString("id")
                            );
                            break;
                        case "Instantly":
                        default:
                            condition = new Instantly();
                            break;
                    }

                    target = results.getString("id");

                    if (!results.wasNull()) {
                        condition.setTarget(target);
                    }

                    requiredFlags = results.getString("requiredFlags");
                    if (!results.wasNull()) {
                        condition.setRequiredFlags(requiredFlags);
                    }

                    needsMissionComplete = results.getBoolean("needsMissionComplete");
                    if (!results.wasNull()) {
                        condition.setNeedsMissionComplete(needsMissionComplete);
                    }

                    retVal.add(condition);
                }
            }

        } catch (SQLException ex) {
            System.err.println(String.format(ErrorStrings.DB_CONN_ERR, ex.getMessage()));
        }

        return retVal;
    }

    public ConditionalActionSet Build() {
        try (Connection conn = db.getConnection()) {

            String query = "";

            try (PreparedStatement stmt = conn.prepareStatement(query)) {

            }

        } catch (SQLException ex) {
            System.err.println(String.format(ErrorStrings.DB_CONN_ERR, ex.getMessage()));
        }
        return new ConditionalActionSet();
    }
}
