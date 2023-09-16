package org.example.controller.session;

import org.example.controller.file.OpenFileController;
import org.example.controller.file.OpenFileControllerImpl;
import org.example.controller.file.SaveFileController;
import org.example.controller.file.SaveFileControllerImpl;

/**
 * Implementation of the SessionController.
 */
public class SessionControllerImpl implements SessionController {

    private SaveFileController saveFileController;
    private OpenFileController openFileController;





    @Override
    public void saveFile(final String text, final String filePath, final String fileName) {
        saveFileController = new SaveFileControllerImpl(filePath, fileName);

    }

    @Override
    public String openFile(final String filePath, final String fileName) {
        openFileController = new OpenFileControllerImpl(filePath, fileName);
        return null;
    }

    @Override
    public void restoreFileInfo() {

    }

    @Override
    public String getFileInfo() {
        return null;
    }

    @Override
    public boolean isFileInfoSetted() {
        return false;
    }
}
