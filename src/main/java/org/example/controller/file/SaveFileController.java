package org.example.controller.file;

import org.example.model.filemodel.FileModel;

/**
 * Controller that save information in a specific file.
 */
public interface SaveFileController {

    /**
     * create a new file in a specific path.
     * @return true if the creation goes well, false otherwise.
     */
    boolean createAFile();

    /**
     *  check if a file already exist based on its path.
     * @return true if the file exist, false otherwise.
     */
    boolean isAlreadyExist();

    /**
     * Function that allow to write on file.
     * @param mess the text to write
     */
    void saveOnFile(String mess);

    /**
     * Get the file model of the file to be saved.
     * @return the file model
     */
    FileModel getFileToSave();
}
