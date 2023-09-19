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


}
