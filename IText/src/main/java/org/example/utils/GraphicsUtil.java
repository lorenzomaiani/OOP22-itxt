package org.example.utils;

import javafx.stage.FileChooser;
import javafx.stage.Window;

import java.io.File;

/**
 * Utility class used to create single element to display.
 */
public class GraphicsUtil {

    /**
     * Create a File Chooser.
     * @param option declare if is a SAVE or OPEN file chooser.
     * @param title the title of the file chooser.
     * @param window the main window, where to display the file chooser.
     * @return the file selected from the user.
     */
    public static File openFileChooser(final FileChooserOption option, final String title, final Window window) {
        return option == FileChooserOption.SAVE ? new FileChooser().showSaveDialog(window)
                : new FileChooser().showOpenDialog(window);

    }
}