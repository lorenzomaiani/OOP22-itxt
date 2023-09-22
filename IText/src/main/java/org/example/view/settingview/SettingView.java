package org.example.view.settingview;

/**
 * Setting view interface, defines all methods to handle event from the setting GUI.
 */
public interface SettingView {

    /**
     * Get input from a text field to set the main directory.
     * @return the main directory selected by the user
     */
    String getMainDirectoryInput();

    /**
     *  Setting, after an input, the main theme to Light (default at start).
     */
    void setThemeToLight();

    /**
     * Setting, after an input, the main theme to Dark (default at start).
     */
    void setThemeToDark();

    /**
     * Open FileChooser to select the main directory.
     */
    void searchIntoFileSystem();

    /**
     * After choose the setting option, it closes the windows and apply the changes
     */
    void applyAndClose();


}
