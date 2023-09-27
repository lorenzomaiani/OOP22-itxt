package org.example.controller.session;

import org.example.controller.file.OpenFileController;
import org.example.controller.file.OpenFileControllerImpl;
import org.example.controller.file.SaveFileController;
import org.example.controller.file.SaveFileControllerImpl;
import org.example.controller.loader.Loader;
import org.example.controller.loader.LoaderImpl;
import org.example.controller.saver.Saver;
import org.example.controller.saver.SaverImpl;
import org.example.model.filemodel.FileModel;
import org.example.model.info.Info;
import org.example.model.info.InfoImpl;
import org.example.model.setting.SettingImpl;
import java.io.IOException;

/**
 * Implementation of the SessionController.
 */
public final class SessionControllerImpl implements SessionController {

    private SaveFileController saveFileController;
    private Saver saverController;
    private Loader loaderController;
    private final Info info = new InfoImpl();

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
                info.setFileModel(saveFileController.getFileToSave());
                System.out.println(info.getFileModel().getFilePath());
                saveFileController.saveOnFile(text);

            }
        }.start();

    }

    @Override
    public String openFile(final String filePath, final String fileName) {
        final OpenFileController openFileController = new OpenFileControllerImpl(filePath, fileName);
        if (openFileController.isAnExistingFile()) {
            info.setFileModel(openFileController.getOpenedFile());
            return openFileController.getTextFromText();
        } else {
            return "";
        }
    }

    @Override
    public void restoreFileInfo() {
        info.setFileModel(null);
    }

    @Override
    public FileModel getFileInfo() {
        return info.getFileModel();
    }

    @Override
    public boolean isFileInfoSetted() {
        return info.getFileModel() != null;
    }

    @Override
    public void saveInfoOnClose() throws IOException {
        info.setSetting(SettingImpl.getInstance());
        saverController = new SaverImpl(info);
        saverController.saveSettingOnClose();
        if (info.getFileModel() != null) {
            saverController.saveFileInfo();
        }

    }

    @Override
    public void loadInfoOnOpen() {
        info.setSetting(SettingImpl.getInstance());
        loaderController = new LoaderImpl(info);
        loaderController.loadSettingInfo();
    }

}
