package org.example.controller.session;

import org.example.controller.file.OpenFileController;
import org.example.controller.file.OpenFileControllerImpl;
import org.example.controller.file.SaveFileController;
import org.example.controller.file.SaveFileControllerImpl;

/**
 * Implementation of the SessionController.
 */
public final class SessionControllerImpl implements SessionController {

    private SaveFileController saveFileController;


    @Override
    public void saveFile(final String text, final String filePath, final String fileName) {
        saveFileController = new SaveFileControllerImpl(filePath, fileName);
        new Thread() {
            @Override
            public void run() {
                super.run();
                if (!saveFileController.isAlreadyExist()) {
                    saveFileController.createAFile();
                }
                saveFileController.saveOnFile(text);

            }
        }.start();

    }

    @Override
    public String openFile(final String filePath, final String fileName) {
        OpenFileController openFileController = new OpenFileControllerImpl(filePath, fileName);
        return openFileController.isAnExistingFile() ? openFileController.getTextFromText() : "";

    }

    @Override
    public void restoreFileInfo() {
        // infoFile.reset();
    }

    @Override
    public String getFileInfo() {
        // return infoFile.getName;
        return null;
    }

    @Override
    public boolean isFileInfoSetted() {
        // return infoFile != null;
        return false;
    }
}
