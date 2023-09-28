package org.example.controller.saver;

import java.io.IOException;

/**
 * Save information on app closing.
 */
public interface Saver {

    /**
     * Save setting data before closing.
     */
    void saveSettingInfo() throws IOException;


    /**
     * Save transformation text info before closing.
     */
    void saveTextFileInfo() throws IOException;
}
