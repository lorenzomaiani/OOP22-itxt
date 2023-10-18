package org.example.model.setting;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.util.Objects;

/**
 * Setting implementation, uses singleton pattern.
 */
public final class SettingImpl implements Setting {
    private static final SettingImpl SETTING = new SettingImpl();
    private String mainDirectory;
    private String mainFont;
    private Theme appTheme;
    private final PropertyChangeSupport support;
    /**
     * Private construct matching singleton pattern.
     */
    private SettingImpl() {
        this.appTheme = Theme.LIGHT;
        this.mainDirectory = "";
        this.mainFont = "";
        support = new PropertyChangeSupport(this);
    }

    /**
     * Methods to get the instance of singleton.
     * @return the singleton object
     */
    public static SettingImpl getInstance() {
        return SETTING;
    }

    /**
     * Methods to add listener on changing.
     * @param pcl the property change listener
     */
    public void addPropertyChangeListener(final PropertyChangeListener pcl) {
        support.addPropertyChangeListener(pcl);
    }

    /**
     * Methods to remove listener on changing.
     * @param pcl the property change listener
     */
    public void removePropertyChangeListener(final PropertyChangeListener pcl) {
        support.removePropertyChangeListener(pcl);
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
        support.firePropertyChange("Theme", this.appTheme, appTheme);
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
