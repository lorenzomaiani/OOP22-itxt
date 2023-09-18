package org.example.controller.file;

/**
 * Controller that execute all the operation on the opening of a new file.
 */
public interface OpenFileController {

    /**
     * Check if the file existing.
     * @return true if it exists, false otherwise
     */
    boolean isAnExistingFile();

    /**
     * Function which allows to read text from TextFile.
     * @return the read text
     */
    String getTextFromText();


}
