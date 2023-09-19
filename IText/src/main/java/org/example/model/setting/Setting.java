package org.example.model.setting;

/**
 * Setting interface, this is a singleton (Singleton pattern).
 */
public interface Setting {
    /**
     * Get main directory set in setting.
     * @return the path of the directory
     */
    String getMainDirectory();

    /**
     * Set the main directory.
     * @param mainDirectory the path of the directory
     */
    void setMainDirectory(String mainDirectory);

    /**
     * Get the main font.
     * @return the font name
     */
    String getMainFont();

    /**
     * Set the font.
     * @param mainFont the font passed from controller
     */
    void setMainFont(String mainFont);

    /**
     * Get the current theme in the app.
     * @return the theme
     */
    Theme getAppTheme();

    /**
     * Set the new theme of the app.
     * @param appTheme the theme.
     */
    void setAppTheme(Theme appTheme);
}
