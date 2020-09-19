package org.lunasphere.hn;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import org.apache.commons.cli.*;
import org.lunasphere.hn.builders.ExtInfoBuilder;
import org.lunasphere.hn.builders.MissionBuilder;
import org.lunasphere.hn.builders.NodeBuilder;
import org.lunasphere.hn.models.extension.ExtensionInfo;
import org.lunasphere.hn.models.missions.Mission;
import org.lunasphere.hn.models.nodes.CompNode;

import java.util.Collection;


public class Builder {

    public static void main(String[] args) {
        SQLConfig sql = new SQLConfig("localhost", 5432, "EXTENSIONS_DB", "postgres", "postgres");

        Options opts = new Options();
        opts.addOption(new Option("e", "extension-id", true, "Extension ID"));
        opts.addOption(new Option("r", "rebuild", false, "Whether to rebuild the entire extension or not"));

        CommandLineParser parser = new DefaultParser();

        try {
            CommandLine line = parser.parse(opts, args);

            if (!line.hasOption("e")) {
                throw new ParseException("CLI requires Extension ID.");
            }

            int extId = Integer.parseInt(line.getOptionValue("e"));
            boolean rebuildAll = line.hasOption("r");

            if (rebuildAll) {
                XmlMapper mapper = new XmlMapper();
                System.out.println(String.format("Preparing to rebuild Extension with ID %d", extId));

                ExtInfoBuilder eBuilder = new ExtInfoBuilder(sql, 1);
                ExtensionInfo info = eBuilder.Build();

                if (info != null) {

                    try {
                        String extensionXML = mapper.writeValueAsString(info);
                        System.out.println(extensionXML + "\n");
                    } catch (JsonProcessingException ex) {
                        System.err.println(String.format(ErrorStrings.SERIALISE_XML_ERR, ex.getMessage()));
                    }

                    Collection<Integer> missionsToBuild = eBuilder.getMissionIds();

                    missionsToBuild.forEach(missionId -> {
                        MissionBuilder mBuilder = new MissionBuilder(sql, missionId);
                        Mission mission = mBuilder.Build();

                        try {
                            String missionXML = mapper.writeValueAsString(mission);
                            System.out.println(missionXML + "\n");
                        } catch (JsonProcessingException ex) {
                            System.err.println(String.format(ErrorStrings.SERIALISE_XML_ERR, ex.getMessage()));
                        }
                    });

                    Collection<Integer> nodesToBuild = eBuilder.getNodeIds();

                    nodesToBuild.forEach(nodeId -> {
                        NodeBuilder nBuilder = new NodeBuilder(sql, nodeId);
                        CompNode node = nBuilder.Build();

                        try {
                            String nodeXML = mapper.writeValueAsString(node);
                            System.out.println(nodeXML + "\n");
                        } catch (JsonProcessingException ex) {
                            System.err.println(String.format(ErrorStrings.SERIALISE_XML_ERR, ex.getMessage()));
                        }
                    });

                }


            }

        } catch (ParseException ex) {
            System.err.println("Could not run Extension Builder. Required properties missing or invalid.");
        }


        HelpFormatter formatter = new HelpFormatter();
        formatter.printHelp("build", opts);
        System.exit(1);
    }

}
