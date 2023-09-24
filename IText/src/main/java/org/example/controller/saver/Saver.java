package org.example.controller.saver;

/**
 * Save information on app closing.
 */
public interface Saver {

    /**
     * Save setting data before closing.
     */
    void saveSettingOnClose();


    /**
     * Save transformation text info before closing.
     */
    void saveFileInfo();
}
