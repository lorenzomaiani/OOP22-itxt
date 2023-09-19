package org.example.model.setting;

import java.util.Objects;

/**
 * Setting implementation, uses singleton pattern.
 */
public class SettingImpl implements Setting {
    private static final SettingImpl SETTING = new SettingImpl();
    private String mainDirectory;
    private String mainFont;
    private Theme appTheme;

    private SettingImpl(){};

    public static SettingImpl getInstance(){
        return SETTING;
    }

    public String getMainDirectory() {
        return mainDirectory;
    }

    public void setMainDirectory(String mainDirectory) {
        this.mainDirectory = mainDirectory;
    }

    public String getMainFont() {
        return mainFont;
    }

    public void setMainFont(String mainFont) {
        this.mainFont = mainFont;
    }

    public Theme getAppTheme() {
        return appTheme;
    }

    public void setAppTheme(Theme appTheme) {
        this.appTheme = appTheme;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        SettingImpl setting = (SettingImpl) o;
        return mainDirectory.equals(setting.mainDirectory) && mainFont.equals(setting.mainFont) && appTheme == setting.appTheme;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainDirectory, mainFont, appTheme);
    }
}
