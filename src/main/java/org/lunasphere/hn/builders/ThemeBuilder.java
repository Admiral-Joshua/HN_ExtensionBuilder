package org.lunasphere.hn.builders;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.lunasphere.hn.ErrorStrings;
import org.lunasphere.hn.SQLConfig;
import org.lunasphere.hn.models.themes.RGBA;
import org.lunasphere.hn.models.themes.Theme;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ThemeBuilder {
    SQLConfig db;
    int themeId;

    public ThemeBuilder(SQLConfig db, int themeId) {
        this.db = db;
        this.themeId = themeId;
    }

    protected String hexToRgb(String hex) {
        if (hex.length() >= 7) {
            int r, g, b, a;
            /*System.out.println("attempting to decode hex string: " + hex);
            System.out.println("r: " + hex.substring(1, 3));
            System.out.println("g: " + hex.substring(3, 5));
            System.out.println("b: " + hex.substring(5, 7));*/

            r = Integer.parseInt(hex.substring(1, 3), 16);
            g = Integer.parseInt(hex.substring(3, 5), 16);
            b = Integer.parseInt(hex.substring(5, 7), 16);

            if (hex.length() > 7) {
                a = Integer.parseInt(hex.substring(7, 9), 16);

                return String.format("%d,%d,%d,%d", r, g, b, a);
            } else {
                return String.format("%d,%d,%d", r, g, b);
            }
        } else {
            return "";
        }
    }

    protected String objToRgb(String rgba) {
        ObjectMapper om = new ObjectMapper();

        try {
            RGBA color = om.readValue(rgba, RGBA.class);

            int alpha = (int)(255 * color.getA());

            return String.format("%d,%d,%d,%d", color.getR(), color.getG(), color.getB(), alpha);
        } catch (IOException ex) {
            return "";
        }
    }

    public Theme Build() {
        System.out.println(String.format("ThemeBuilder job started for Theme with ID: %d", themeId));

        try (Connection conn = db.getConnection()) {

            PreparedStatement stmt = conn.prepareStatement("SELECT \"hn_Theme\".*, \"hn_ThemeLayout\".\"LayoutName\" FROM \"hn_Theme\" INNER JOIN \"hn_ThemeLayout\" ON \"hn_Theme\".\"layoutId\" = \"hn_ThemeLayout\".\"layoutId\" WHERE \"themeId\" = ?");
            stmt.setInt(1, themeId);

            ResultSet results = stmt.executeQuery();

            if (results.next()) {

                Theme theme = new Theme(
                        results.getString("LayoutName"),
                        String.format("Themes/Backgrounds/%d.png", themeId),
                        "0,0,0",
                        hexToRgb(results.getString("defaultHighlightColor")),
                        hexToRgb(results.getString("defaultTopBarColor")),

                        hexToRgb(results.getString("moduleColorSolidDefault")),
                        hexToRgb(results.getString("moduleColorStrong")),
                        objToRgb(results.getString("moduleColorBacking")),

                        hexToRgb(results.getString("exeModuleTopBar")),
                        hexToRgb(results.getString("exeModuleTitleText")),

                        hexToRgb(results.getString("warningColor")),
                        hexToRgb(results.getString("subtleTextColor")),
                        hexToRgb(results.getString("darkBackgroundColor")),
                        hexToRgb(results.getString("indentBackgroundColor")),
                        hexToRgb(results.getString("outlineColor")),
                        hexToRgb(results.getString("lockedColor")),
                        hexToRgb(results.getString("brightLockedColor")),
                        hexToRgb(results.getString("brightUnlockedColor")),
                        hexToRgb(results.getString("unlockedColor")),

                        hexToRgb(results.getString("shellColor")),
                        hexToRgb(results.getString("shellButtonColor")),
                        hexToRgb(results.getString("semiTransText")),
                        hexToRgb(results.getString("terminalTextColor")),
                        hexToRgb(results.getString("topBarTextColor")),
                        hexToRgb(results.getString("connectedNodeHighlight")),
                        hexToRgb(results.getString("netmapToolTipColor")),
                        hexToRgb(results.getString("netmapToolTipBackground")),
                        hexToRgb(results.getString("topBarIconsColor")),
                        hexToRgb(results.getString("thisComputerNode")),

                        hexToRgb(results.getString("superLightWhite")),
                        hexToRgb(results.getString("lightGray")),
                        objToRgb(results.getString("scanlinesColor"))
                );

                theme.setId(results.getString("id"));

                return theme;
            } else {
                return null;
            }

        } catch (SQLException ex) {
            System.err.println(String.format(ErrorStrings.DB_CONN_ERR, ex.getMessage()));
        }

        return null;
    }
}
