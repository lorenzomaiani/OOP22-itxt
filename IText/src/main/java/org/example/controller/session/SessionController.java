package org.example.controller.session;

/**
 * Controller that handle event coming from SessionView.
 */
public interface SessionController {
    /**
     * React to the event save file from GUI.
     */
    void saveFile();

    /**
     * React to the event openFile from GUI.
     */
    void openFile();

    /**
     * restore information about the current opened file.
     */
    void restoreFileInfo();

    /**
     * Get information about the opened file.
     * @return the path
     */
    String getFileInfo();

    /**
     * check if a file is set.
     * @return true if it's set, false otherwise.
     */
    boolean isFileInfoSetted();
}
