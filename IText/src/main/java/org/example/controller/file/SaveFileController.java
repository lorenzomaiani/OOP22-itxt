package org.example.controller.file;

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
}
