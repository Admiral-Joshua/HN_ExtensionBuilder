package org.lunasphere.hn;

import org.zeroturnaround.zip.ZipUtil;

import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.zip.ZipOutputStream;

public class Writer {
    Path srcDir;
    Path outDir;

    private static final String USER_DATA = System.getenv("user_data_dir");
    private static final String BASE_PATH = System.getenv("export_base_dir");

    public Writer(int extensionId, int userId, Long buildId) {
        srcDir = Paths.get(USER_DATA, Integer.toString(userId), Integer.toString(extensionId));
        if (!Files.exists(srcDir)) {
            System.err.println("FATAL ERROR - EACCESS Error: '" + srcDir.toString() + "'\nUser data directory missing or invalid! Please check environmental variable 'user_data_dir' and try again!");
            System.exit(1);
        }

        outDir = Paths.get(BASE_PATH, Integer.toString(extensionId));

        if (!Files.exists(outDir)) {
            try {
                Files.createDirectory(outDir);
            } catch (IOException ex) {
                System.err.println("CRITICAL ERROR - Could not create output directory.");
            }
        }

        outDir = Paths.get(BASE_PATH, Integer.toString(extensionId), Long.toString(buildId));

        if (!Files.exists(outDir)) {
            try {
                Files.createDirectory(outDir);
            } catch (IOException ex) {
                System.err.println("CRITICAL ERROR - Could not create output directory.");
            }
        }
    }

    protected void writeXml(File file, String xml) throws IOException {
        if (!file.createNewFile()) {
            System.out.println(file.getName() + " already exists... overwriting...");
        }

        FileWriter writer = new FileWriter(file);
        writer.write(xml);
        writer.close();
    }

    public void WriteExtensionInfo(String xml) throws IOException {
        File file = new File(outDir.toString(), "ExtensionInfo.xml");

        writeXml(file, xml);
    }

    public void WriteNode(String id, String xml) throws IOException {
        // Check for, and create nodes directory if not exists first.
        Path nodesDir = Paths.get(outDir.toString(), "Nodes");

        if (!Files.exists(nodesDir)) {
            Files.createDirectory(nodesDir);
        }

        File file = new File(nodesDir.toString(), id + ".xml");

        writeXml(file, xml);
    }

    public void WriteTheme(String id, String xml) throws IOException {
        // Check for, and create THemes directory if not exists first.
        Path themesDir = Paths.get(outDir.toString(), "Themes");

        if (!Files.exists(themesDir)) {
            Files.createDirectory(themesDir);
        }

        File file = new File(themesDir.toString(), id + ".xml");

        writeXml(file, xml);
    }

    public void WriteMission(String id, String xml) throws IOException {
        Path missionDir = Paths.get(outDir.toString(), "Missions");

        if (!Files.exists(missionDir))
            Files.createDirectory(missionDir);

        File file = new File(missionDir.toString(), id + ".xml");

        writeXml(file, xml);
    }

    public void WriteActionSet(int id, String xml) throws IOException {
        Path actionDir = Paths.get(outDir.toString(), "Actions");

        if (!Files.exists(actionDir))
            Files.createDirectory(actionDir);

        File file = new File(actionDir.toString(), id + ".xml");

        writeXml(file, xml);
    }

    public void copyMusicTrack(int trackId) throws IOException {
        Path musicDir = Paths.get(outDir.toString(), "Music");

        if (!Files.exists(musicDir))
            Files.createDirectory(musicDir);

        Path dest = Paths.get(musicDir.toString(), trackId + ".ogg");

        Path src;
        if (trackId > 23) {
            src = Paths.get(srcDir.toString(), "..", trackId + ".ogg");
        } else {
            // Nothing to copy, this track comes with Hacknet.
            return;
        }

        if (Files.exists(src)) {
            Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
        } else {
            System.err.println("Could not find requested Music Track " + trackId + ".ogg");
        }
    }

    public void copyThemeBackground(int themeId) throws IOException {
        Path themeDir = Paths.get(outDir.toString(), "Themes", "Backgrounds");

        if (!Files.exists(themeDir))
            Files.createDirectory(themeDir);

        Path dest = Paths.get(themeDir.toString(), themeId + ".png");

        Path src = Paths.get(srcDir.toString(), "Themes", themeId + ".png");

        if (Files.exists(src)) {
            Files.copy(src, dest, StandardCopyOption.REPLACE_EXISTING);
        } else {
            System.err.println("Could not find requested Background " + src.toString());
        }
    }


    public void archiveBuild(Long buildId) {
        String zipLoc = Paths.get(outDir.toString(), "..", Long.toString(buildId) + ".zip").toString();
        File src = new File(outDir.toString());
        File dest = new File(zipLoc);

        ZipUtil.pack(src, dest);
    }
}
