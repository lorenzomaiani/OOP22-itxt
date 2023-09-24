package org.example.controller.loader;

/**
 * Interface that allow to load specific information and save it on the info model.
 */
public interface Loader {

    /**
     * Load setting info from a specific file.
     */
    void loadSettingInfo();

    /**
     * Load file info, transformation info, from a specific file.
     */
    void loadFileInfo();
}
