package org.example.controller.session;

import org.example.model.filemodel.FileModel;

/**
 * Controller that handle event coming from SessionView.
 */
public interface SessionController {
    /**
     * React to the event save file from GUI.
     * @param text the text to save
     * @param filePath the file path choose by the use
     * @param fileName the file name
     */
    void saveFile(String text, String filePath, String fileName);

    /**
     * React to the event openFile from GUI.
     * @param filePath the file path choose by the use
     * @param fileName the file name
     * @return the text that is got from the file
     */
    String openFile(String filePath, String fileName);

    /**
     * restore information about the current opened file.
     */
    void restoreFileInfo();

    /**
     * Get information about the opened file.
     * @return the path
     */
    FileModel getFileInfo();

    /**
     * check if a file is set.
     * @return true if it's set, false otherwise.
     */
    boolean isFileInfoSetted();
}
