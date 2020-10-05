package org.lunasphere.hn.models.themes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

@JacksonXmlRootElement(localName = "CustomTheme")
public class Theme {
    @JsonIgnore
    String id;

    String themeLayoutName;
    String backgroundImagePath;
    @JacksonXmlProperty(localName = "UseAspectPreserveBackgroundScaling")
    boolean useAspectPreserveBackgroundScaling = false;
    @JacksonXmlProperty(localName = "BackgroundImageFillColor")
    String backgroundImageFillColor;

    String defaultHighlightColor;
    String defaultTopBarColor;

    String moduleColorSolidDefault;
    String moduleColorStrong;
    String moduleColorBacking;

    String exeModuleTopBar;
    String exeModuleTitleText;

    String warningColor;
    String subtleTextColor;
    String darkBackgroundColor;
    String indentBackgroundColor;
    String outlineColor;
    String lockedColor;
    String brightLockedColor;
    String brightUnlockedColor;
    String unlockedColor;

    String shellColor;
    String shellButtonColor;
    String semiTransText;
    String terminalTextColor;
    String topBarTextColor;
    String connectedNodeHighlight;
    String netmapToolTipColor;
    String netmapToolTipBackground;
    String topBarIconsColor;
    String thisComputerNode;

    String superLightWhite;
    String lightGray;
    String scanlinesColor;

    public Theme() {
    }

    public Theme(String themeLayoutName, String backgroundImagePath, String backgroundImageFillColor, String defaultHighlightColor, String defaultTopBarColor, String moduleColorSolidDefault, String moduleColorStrong, String moduleColorBacking, String exeModuleTopBar, String exeModuleTitleText, String warningColor, String subtleTextColor, String darkBackgroundColor, String indentBackgroundColor, String outlineColor, String lockedColor, String brightLockedColor, String brightUnlockedColor, String unlockedColor, String shellColor, String shellButtonColor, String semiTransText, String terminalTextColor, String topBarTextColor, String connectedNodeHighlight, String netmapToolTipColor, String netmapToolTipBackground, String topBarIconsColor, String thisComputerNode, String superLightWhite, String lightGray, String scanlinesColor) {
        this.themeLayoutName = themeLayoutName;
        this.backgroundImagePath = backgroundImagePath;
        this.backgroundImageFillColor = backgroundImageFillColor;
        this.defaultHighlightColor = defaultHighlightColor;
        this.defaultTopBarColor = defaultTopBarColor;
        this.moduleColorSolidDefault = moduleColorSolidDefault;
        this.moduleColorStrong = moduleColorStrong;
        this.moduleColorBacking = moduleColorBacking;
        this.exeModuleTopBar = exeModuleTopBar;
        this.exeModuleTitleText = exeModuleTitleText;
        this.warningColor = warningColor;
        this.subtleTextColor = subtleTextColor;
        this.darkBackgroundColor = darkBackgroundColor;
        this.indentBackgroundColor = indentBackgroundColor;
        this.outlineColor = outlineColor;
        this.lockedColor = lockedColor;
        this.brightLockedColor = brightLockedColor;
        this.brightUnlockedColor = brightUnlockedColor;
        this.unlockedColor = unlockedColor;
        this.shellColor = shellColor;
        this.shellButtonColor = shellButtonColor;
        this.semiTransText = semiTransText;
        this.terminalTextColor = terminalTextColor;
        this.topBarTextColor = topBarTextColor;
        this.connectedNodeHighlight = connectedNodeHighlight;
        this.netmapToolTipColor = netmapToolTipColor;
        this.netmapToolTipBackground = netmapToolTipBackground;
        this.topBarIconsColor = topBarIconsColor;
        this.thisComputerNode = thisComputerNode;
        this.superLightWhite = superLightWhite;
        this.lightGray = lightGray;
        this.scanlinesColor = scanlinesColor;
    }

    public String getThemeLayoutName() {
        return themeLayoutName;
    }

    public void setThemeLayoutName(String themeLayoutName) {
        this.themeLayoutName = themeLayoutName;
    }

    public String getBackgroundImagePath() {
        return backgroundImagePath;
    }

    public void setBackgroundImagePath(String backgroundImagePath) {
        this.backgroundImagePath = backgroundImagePath;
    }

    public String getBackgroundImageFillColor() {
        return backgroundImageFillColor;
    }

    public void setBackgroundImageFillColor(String backgroundImageFillColor) {
        this.backgroundImageFillColor = backgroundImageFillColor;
    }

    public String getDefaultHighlightColor() {
        return defaultHighlightColor;
    }

    public void setDefaultHighlightColor(String defaultHighlightColor) {
        this.defaultHighlightColor = defaultHighlightColor;
    }

    public String getDefaultTopBarColor() {
        return defaultTopBarColor;
    }

    public void setDefaultTopBarColor(String defaultTopBarColor) {
        this.defaultTopBarColor = defaultTopBarColor;
    }

    public String getModuleColorSolidDefault() {
        return moduleColorSolidDefault;
    }

    public void setModuleColorSolidDefault(String moduleColorSolidDefault) {
        this.moduleColorSolidDefault = moduleColorSolidDefault;
    }

    public String getModuleColorStrong() {
        return moduleColorStrong;
    }

    public void setModuleColorStrong(String moduleColorStrong) {
        this.moduleColorStrong = moduleColorStrong;
    }

    public String getModuleColorBacking() {
        return moduleColorBacking;
    }

    public void setModuleColorBacking(String moduleColorBacking) {
        this.moduleColorBacking = moduleColorBacking;
    }

    public String getExeModuleTopBar() {
        return exeModuleTopBar;
    }

    public void setExeModuleTopBar(String exeModuleTopBar) {
        this.exeModuleTopBar = exeModuleTopBar;
    }

    public String getExeModuleTitleText() {
        return exeModuleTitleText;
    }

    public void setExeModuleTitleText(String exeModuleTitleText) {
        this.exeModuleTitleText = exeModuleTitleText;
    }

    public String getWarningColor() {
        return warningColor;
    }

    public void setWarningColor(String warningColor) {
        this.warningColor = warningColor;
    }

    public String getSubtleTextColor() {
        return subtleTextColor;
    }

    public void setSubtleTextColor(String subtleTextColor) {
        this.subtleTextColor = subtleTextColor;
    }

    public String getDarkBackgroundColor() {
        return darkBackgroundColor;
    }

    public void setDarkBackgroundColor(String darkBackgroundColor) {
        this.darkBackgroundColor = darkBackgroundColor;
    }

    public String getIndentBackgroundColor() {
        return indentBackgroundColor;
    }

    public void setIndentBackgroundColor(String indentBackgroundColor) {
        this.indentBackgroundColor = indentBackgroundColor;
    }

    public String getOutlineColor() {
        return outlineColor;
    }

    public void setOutlineColor(String outlineColor) {
        this.outlineColor = outlineColor;
    }

    public String getLockedColor() {
        return lockedColor;
    }

    public void setLockedColor(String lockedColor) {
        this.lockedColor = lockedColor;
    }

    public String getBrightLockedColor() {
        return brightLockedColor;
    }

    public void setBrightLockedColor(String brightLockedColor) {
        this.brightLockedColor = brightLockedColor;
    }

    public String getBrightUnlockedColor() {
        return brightUnlockedColor;
    }

    public void setBrightUnlockedColor(String brightUnlockedColor) {
        this.brightUnlockedColor = brightUnlockedColor;
    }

    public String getUnlockedColor() {
        return unlockedColor;
    }

    public void setUnlockedColor(String unlockedColor) {
        this.unlockedColor = unlockedColor;
    }

    public String getShellColor() {
        return shellColor;
    }

    public void setShellColor(String shellColor) {
        this.shellColor = shellColor;
    }

    public String getShellButtonColor() {
        return shellButtonColor;
    }

    public void setShellButtonColor(String shellButtonColor) {
        this.shellButtonColor = shellButtonColor;
    }

    public String getSemiTransText() {
        return semiTransText;
    }

    public void setSemiTransText(String semiTransText) {
        this.semiTransText = semiTransText;
    }

    public String getTerminalTextColor() {
        return terminalTextColor;
    }

    public void setTerminalTextColor(String terminalTextColor) {
        this.terminalTextColor = terminalTextColor;
    }

    public String getTopBarTextColor() {
        return topBarTextColor;
    }

    public void setTopBarTextColor(String topBarTextColor) {
        this.topBarTextColor = topBarTextColor;
    }

    public String getConnectedNodeHighlight() {
        return connectedNodeHighlight;
    }

    public void setConnectedNodeHighlight(String connectedNodeHighlight) {
        this.connectedNodeHighlight = connectedNodeHighlight;
    }

    public String getNetmapToolTipColor() {
        return netmapToolTipColor;
    }

    public void setNetmapToolTipColor(String netmapToolTipColor) {
        this.netmapToolTipColor = netmapToolTipColor;
    }

    public String getNetmapToolTipBackground() {
        return netmapToolTipBackground;
    }

    public void setNetmapToolTipBackground(String netmapToolTipBackground) {
        this.netmapToolTipBackground = netmapToolTipBackground;
    }

    public String getTopBarIconsColor() {
        return topBarIconsColor;
    }

    public void setTopBarIconsColor(String topBarIconsColor) {
        this.topBarIconsColor = topBarIconsColor;
    }

    public String getThisComputerNode() {
        return thisComputerNode;
    }

    public void setThisComputerNode(String thisComputerNode) {
        this.thisComputerNode = thisComputerNode;
    }

    public String getSuperLightWhite() {
        return superLightWhite;
    }

    public void setSuperLightWhite(String superLightWhite) {
        this.superLightWhite = superLightWhite;
    }

    public String getLightGray() {
        return lightGray;
    }

    public void setLightGray(String lightGray) {
        this.lightGray = lightGray;
    }

    public String getScanlinesColor() {
        return scanlinesColor;
    }

    public void setScanlinesColor(String scanlinesColor) {
        this.scanlinesColor = scanlinesColor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public boolean isUseAspectPreserveBackgroundScaling() {
        return useAspectPreserveBackgroundScaling;
    }

    public void setUseAspectPreserveBackgroundScaling(boolean useAspectPreserveBackgroundScaling) {
        this.useAspectPreserveBackgroundScaling = useAspectPreserveBackgroundScaling;
    }
}
