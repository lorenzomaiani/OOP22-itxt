package org.example.utils.graphics;

/**
 * Different type of file chooser, one to save and other to open file.
 */
public enum FileChooserOption {
    /**
     * SAVE option.
     */
    SAVE,
    /**
     * OPEN option.
     */
    OPEN,
    /**
     * DIRECTORY type, if the file chooser has to return a directory and not a file.
     */
    DIRECTORY;
}
