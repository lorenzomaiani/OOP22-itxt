package org.example.controller.saver;

import java.io.IOException;

/**
 * Save information on app closing.
 */
public interface Saver {

    /**
     * Save setting data before closing.
     */
    void saveSettingOnClose() throws IOException;


    /**
     * Save transformation text info before closing.
     */
    void saveFileInfo() throws IOException;
}
