package org.lunasphere.hn;

import org.apache.commons.cli.*;


public class BuilderCLI {

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

            ExtensionBuilder extensionBuilder = new ExtensionBuilder(sql, 0L);
            if (rebuildAll) {
                // Rebuild the entire extension.
                extensionBuilder.RebuildExtension(extId, userId);
            } else {
                extensionBuilder.BuildChanges(extId, userId);
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
