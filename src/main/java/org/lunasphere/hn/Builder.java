package org.lunasphere.hn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.cli.*;
import org.lunasphere.hn.builders.*;
import org.lunasphere.hn.models.actionset.Action;
import org.lunasphere.hn.models.actionset.ConditionalActionSet;
import org.lunasphere.hn.models.extension.ExtensionInfo;
import org.lunasphere.hn.models.internal.DatabaseChange;
import org.lunasphere.hn.models.missions.Mission;
import org.lunasphere.hn.models.nodes.CompNode;
import org.lunasphere.hn.models.themes.Theme;

import java.io.IOException;
import java.security.cert.Extension;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Date;


public class Builder {

    public static void main(String[] args) {
        SQLConfig sql = new SQLConfig("localhost", 5432, "EXTENSIONS_DB", "postgres", "postgres");

        Options opts = new Options();
        opts.addOption(new Option("e", "extension-id", true, "Extension ID"));
        opts.addOption(new Option("r", "rebuild", false, "Whether to rebuild the entire extension or not"));
        opts.addOption(new Option("u", "user", true, "User ID of the requesting user for this build. (Identifies where to find uploaded media files)"));

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine line = parser.parse(opts, args);

            if (!line.hasOption("e") || !line.hasOption("u")) {
                throw new ParseException("CLI requires Extension ID and User ID to work.");
            }

            int extId = Integer.parseInt(line.getOptionValue("e"));
            int userId = Integer.parseInt(line.getOptionValue("u"));
            boolean rebuildAll = line.hasOption("r");

            Writer writer = new Writer(extId, userId);

            if (rebuildAll) {
                XmlMapper mapper = new XmlMapper();
                mapper.enable(SerializationFeature.INDENT_OUTPUT);
                System.out.println(String.format("Preparing to rebuild Extension with ID %d", extId));

                ExtInfoBuilder extBuilder = new ExtInfoBuilder(sql, extId);
                ExtensionInfo info = extBuilder.Build();

                if (info != null) {

                    try {
                        String extensionXML = mapper.writeValueAsString(info);
                        writer.WriteExtensionInfo(extensionXML);
                    } catch (IOException ex) {
                        System.err.println(String.format(ErrorStrings.SERIALISE_XML_ERR, ex.getMessage()));
                    }

                    Collection<Integer> missionsToBuild = extBuilder.getMissionIds();

                    missionsToBuild.forEach(missionId -> {
                        MissionBuilder mBuilder = new MissionBuilder(sql, missionId);
                        Mission mission = mBuilder.Build();

                        try {
                            String missionXML = mapper.writeValueAsString(mission);
                            writer.WriteMission(mission.getId(), missionXML);
                        } catch (IOException ex) {
                            System.err.println(String.format(ErrorStrings.SERIALISE_XML_ERR, ex.getMessage()));
                        }
                    });

                    Collection<Integer> nodesToBuild = extBuilder.getNodeIds();

                    nodesToBuild.forEach(nodeId -> {
                        NodeBuilder nBuilder = new NodeBuilder(sql, nodeId);
                        CompNode node = nBuilder.Build();

                        try {
                            String nodeXML = mapper.writeValueAsString(node);
                            writer.WriteNode(node.getId(), nodeXML);
                        } catch (IOException ex) {
                            System.err.println(String.format(ErrorStrings.SERIALISE_XML_ERR, ex.getMessage()));
                        }
                    });

                    Collection<Integer> themesToBuild = extBuilder.getThemeIds();

                    themesToBuild.forEach(themeId -> {
                        ThemeBuilder tBuilder = new ThemeBuilder(sql, themeId);

                        Theme theme = tBuilder.Build();

                        try {
                            String themeXML = mapper.writeValueAsString(theme);
                            writer.WriteTheme(theme.getId(), themeXML);

                            writer.copyThemeBackground(themeId);
                        } catch (IOException ex) {
                            System.err.println(String.format(ErrorStrings.SERIALISE_XML_ERR, ex.getMessage()));
                        }
                    });

                    Collection<Integer> musicToCopy = extBuilder.getTrackIds();

                    musicToCopy.forEach(trackId -> {
                        try {
                            writer.copyMusicTrack(trackId);
                        } catch (IOException ex) {
                            System.err.println("Something went wrong whilst trying to copy Music Track.\nError Details: " + ex.getMessage());
                        }
                    });

                    System.exit(0);
                }
            } else {
                // Only build changes for this extension...
                ExtInfoBuilder extBuilder = new ExtInfoBuilder(sql, extId);

                Collection<DatabaseChange> changes = extBuilder.detectChanges();

                XmlMapper mapper = new XmlMapper();

                System.out.println(String.format("Processing changes made on Extension %d", extId));
                if (changes.size() < 1) {
                    System.out.println("No changes detected. Exiting...");
                    System.exit(0);
                }
                changes.forEach(change -> {
                    boolean ok = false;

                    System.out.print(String.format("Processing change %d", change.getChangeId()));
                    switch (change.getChangeType()) {
                        case EXTENSION:
                            System.out.print(" -- type: Extension Info... Rebuilding...\n");
                            ExtensionInfo info = extBuilder.Build();

                            try {
                                String xml = mapper.writeValueAsString(info);
                                //System.out.println(xml);
                                writer.WriteExtensionInfo(xml);
                                ok = true;
                            }  catch (IOException ex) {
                                System.err.println(String.format(ErrorStrings.SERIALISE_XML_ERR, ex.getMessage()));
                            }
                            break;
                        case MUSIC:
                            System.out.println(" -- type: MusicTrack... Copying... \n");
                            try {
                                writer.copyMusicTrack(change.getObjectId());
                            } catch (IOException ex) {
                                System.err.println("Failed to copy requested music track.\nExcpetion Details: " + ex.getMessage());
                            }
                            break;
                        case MISSION:
                            System.out.print(" -- type: Mission... Rebuilding...\n");
                            MissionBuilder mBuilder = new MissionBuilder(sql, change.getObjectId());
                            Mission mission = mBuilder.Build();

                            try {
                                String xml = mapper.writeValueAsString(mission);
                                writer.WriteMission(mission.getId(), xml);
                                ok = true;
                            }  catch (IOException ex) {
                                System.err.println(String.format(ErrorStrings.SERIALISE_XML_ERR, ex.getMessage()));
                            }
                            break;
                        case THEME:
                            System.out.print(" -- type: Theme... Rebuilding...\n");
                            ThemeBuilder tBuilder = new ThemeBuilder(sql, change.getObjectId());
                            Theme theme = tBuilder.Build();

                            try {
                                String xml = mapper.writeValueAsString(theme);
                                writer.WriteTheme(theme.getId(), xml);
                                writer.copyThemeBackground(change.getObjectId());
                                ok = true;
                            } catch (IOException ex) {
                                System.err.println(String.format(ErrorStrings.SERIALISE_XML_ERR, ex.getMessage()));
                            }
                            break;
                        case NODE:
                            System.out.print(" -- type: Node... Rebuilding...\n");
                            NodeBuilder nBuilder = new NodeBuilder(sql, change.getObjectId());
                            CompNode node = nBuilder.Build();

                            try {
                                String xml = mapper.writeValueAsString(node);
                                writer.WriteNode(node.getId(), xml);
                                ok = true;
                            } catch (IOException ex) {
                                System.err.println(String.format(ErrorStrings.SERIALISE_XML_ERR, ex.getMessage()));
                            }
                            break;
                        case ACTION_SET:
                            System.out.print(" -- type: ActionSet... Rebuilding...\n");
                            ActionBuilder aBuilder = new ActionBuilder(sql, change.getObjectId());
                            ConditionalActionSet actions = aBuilder.Build();

                            try {
                                String xml = mapper.writeValueAsString(actions);
                                writer.WriteActionSet(actions.getActionSetId(), xml);
                                ok = true;
                            } catch (IOException ex) {
                                System.err.println(String.format(ErrorStrings.SERIALISE_XML_ERR, ex.getMessage()));
                            }
                            break;
                        default:
                            System.err.println("Change of this type is currently not supported!");
                            break;
                    }

                    if (ok) {
                        try (Connection conn = sql.getConnection()) {
                            PreparedStatement updateStmt = conn.prepareStatement("UPDATE change_log SET last_build = CURRENT_TIMESTAMP WHERE change_id = ?");
                            updateStmt.setLong(1, change.getChangeId());

                            updateStmt.executeUpdate();

                        } catch (SQLException ex) {
                            System.err.println(String.format(ErrorStrings.DB_CONN_ERR, ex.getMessage()));
                        }
                    }
                });

                System.exit(0);
            }

        } catch (ParseException ex) {
            System.err.println("Could not run Extension Builder. Required properties missing or invalid.");
        }


        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("build", opts);
        System.exit(1);
    }

}
