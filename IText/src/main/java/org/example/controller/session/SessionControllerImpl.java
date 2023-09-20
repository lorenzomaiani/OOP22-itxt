package org.example.controller.session;

import org.example.controller.file.OpenFileController;
import org.example.controller.file.OpenFileControllerImpl;
import org.example.controller.file.SaveFileController;
import org.example.controller.file.SaveFileControllerImpl;
import org.example.model.filemodel.FileModel;

/**
 * Implementation of the SessionController.
 */
public final class SessionControllerImpl implements SessionController {

    private SaveFileController saveFileController;
    private FileModel currentFileInSession;


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
        final OpenFileController openFileController = new OpenFileControllerImpl(filePath, fileName);
        if (openFileController.isAnExistingFile()) {
            currentFileInSession = openFileController.getOpenedFile();
            return openFileController.getTextFromText();
        } else {
            return "";
        }

    }

    @Override
    public void restoreFileInfo() {
        this.currentFileInSession = null;
    }

    @Override
    public FileModel getFileInfo() {
        return currentFileInSession;
    }

    @Override
    public boolean isFileInfoSetted() {
        return currentFileInSession != null;
    }
}
