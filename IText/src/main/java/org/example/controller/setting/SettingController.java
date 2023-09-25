package org.example.controller.setting;

import org.example.model.setting.Theme;

/**
 * Controller to handle setting input.
 */
public interface SettingController {

    /**
     * Set the main directory in setting.
     * @param mainDirectoryPath the directory selected by the user
     */
    void setMainDirectoryInSetting(String mainDirectoryPath);

    /**
     * Set the font in setting.
     * @param font selected by the user
     */
    void setFontInSetting(String font);

    /**
     * Set the theme of the application.
     * @param theme the theme, can be DARK or LIGHT
     */
    void setAppTheme(Theme theme);

    /**
     * Get the main directory from setting.
     * @return the directory path
     */
    String getMainDirectoryInSetting();

    /**
     * Get the main font from setting.
     * @return the font name
     */
    String getMainFontInSetting();

    /**
     * Get the selected app theme in setting.
     * @return the app theme
     */
    Theme getMainThemeInSetting();


}
