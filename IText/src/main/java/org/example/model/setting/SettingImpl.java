package org.example.model.setting;

import java.util.Objects;

/**
 * Setting implementation, uses singleton pattern.
 */
public final class SettingImpl implements Setting {
    private static final SettingImpl SETTING = new SettingImpl();
    private String mainDirectory;
    private String mainFont;
    private Theme appTheme;

    /**
     * Private construct matching singleton pattern.
     */
    private SettingImpl() {
        this.appTheme = Theme.LIGHT;
        this.mainDirectory = "";
        this.mainFont = "";
    }

    /**
     * Methods to get the instance of singleton.
     * @return the singleton object
     */
    public static SettingImpl getInstance() {
        return SETTING;
    }
    @Override
    public String getMainDirectory() {
        return mainDirectory;
    }

    @Override
    public void setMainDirectory(final String mainDirectory) {
        this.mainDirectory = mainDirectory;
    }

    @Override
    public String getMainFont() {
        return mainFont;
    }

    @Override
    public void setMainFont(final String mainFont) {
        this.mainFont = mainFont;
    }

    @Override
    public Theme getAppTheme() {
        return appTheme;
    }

    @Override
    public void setAppTheme(final Theme appTheme) {
        this.appTheme = appTheme;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final SettingImpl setting = (SettingImpl) o;
        return mainDirectory.equals(setting.mainDirectory) && mainFont.equals(setting.mainFont) && appTheme == setting.appTheme;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mainDirectory, mainFont, appTheme);
    }
}
