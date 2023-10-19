package org.example.controller.session;

import org.example.controller.file.OpenFileController;
import org.example.controller.file.OpenFileControllerImpl;
import org.example.controller.file.OpenType;
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
import java.util.logging.Level;
import java.util.logging.Logger;

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
                try {
                    saveTextInfo();
                } catch (IOException e) {
                    final Logger logger = Logger.getLogger(this.getClass().getName());
                    logger.log(Level.WARNING, "Exception :" + e.getMessage());
                }
                final Logger logger = Logger.getLogger(this.getClass().getName());
                logger.log(Level.INFO, info.getFileModel().getFilePath());
                saveFileController.saveOnFile(text);

            }
        }.start();

    }

    @Override
    public String openFile(final String filePath, final String fileName, final OpenType openType) {
        final OpenFileController openFileController = new OpenFileControllerImpl(filePath, fileName);
        if (openFileController.isAnExistingFile()) {
            if (openType == OpenType.FILE) {
                info.setFileModel(openFileController.getOpenedFile());
                loaderController.loadTextFileInfo();
            }
            return openFileController.getTextFromText();
        } else {
            return "";
        }
    }

    @Override
    public void restoreFileInfo() {
        try {
            saveTextInfo();
        } catch (IOException e) {
            final Logger logger = Logger.getLogger(this.getClass().getName());
            logger.log(Level.WARNING, "Exception :" + e.getMessage());
        }
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
        saverController.saveSettingInfo();
        if (info.getFileModel() != null) {
            saverController.saveTextFileInfo();
        }
    }

    @Override
    public void saveTextInfo() throws IOException {
        if (info.getFileModel() != null) {
            saverController = new SaverImpl(info);
            saverController.saveTextFileInfo();
        }
    }

    @Override
    public void loadInfoOnOpen() {
        info.setSetting(SettingImpl.getInstance());
        loaderController = new LoaderImpl(info);
        loaderController.loadSettingInfo();

    }

}
