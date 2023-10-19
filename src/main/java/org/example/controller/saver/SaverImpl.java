package org.example.controller.saver;

import org.example.model.info.Info;
import org.example.utils.constant.StringConstants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Implementation class of Saver interface.
 */
public final class SaverImpl implements Saver {
    private final Info info;

    /**
     * Constructor.
     * @param info the info
     */
    public SaverImpl(final Info info) {
        this.info = info;
    }

    @Override
    public void saveSettingInfo() throws IOException {
        final File settingFile = new File(StringConstants.PATH_TO_SETTING_FILE);
        final File directory = new File(StringConstants.PATH_TO_SETTING_DIRECTORY);
        final Logger logger = Logger.getLogger(this.getClass().getName());
        logger.log(Level.INFO, "Path to direcotry: " + StringConstants.PATH_TO_SETTING_DIRECTORY
                + " Path to file: " + StringConstants.PATH_TO_SETTING_FILE);
        if (!directory.isDirectory() && directory.mkdir() && !settingFile.isFile()) {
            final boolean res = settingFile.createNewFile();  // create a file
            if (!res) {
                logger.log(Level.WARNING, "ERROR");
            }
        }
        writeSettingOnFile(settingFile);
    }

    @Override
    public void saveTextFileInfo() throws IOException {
        final String directoryFile = info.getFileModel().getFilePath()
                .replace(info.getFileModel().getFileName(), "");
        final Logger logger = Logger.getLogger(this.getClass().getName());
        logger.log(Level.INFO, directoryFile);
        final File infoFile = new File(directoryFile
                + StringConstants.SEPARATOR
                + info.getFileModel().getFileName().split("\\.")[0] + "info.ini");
        if (!infoFile.isFile()) {
           final boolean res = infoFile.createNewFile();
            if (!res) {
                logger.log(Level.WARNING, "ERROR");
            }
        }
        writeTextInfoOnFile(infoFile);
    }

    /**
     *
     * @param file
     */
    private void writeSettingOnFile(final File file) {
        try (BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), StandardCharsets.UTF_8))) {
            bfw.write(info.getSetting().getMainDirectory());
            bfw.newLine();
            bfw.write(info.getSetting().getMainFont());
            bfw.newLine();
            bfw.write(info.getSetting().getAppTheme().toString());
            bfw.newLine();
            bfw.close();
        } catch (IOException e) {
            final Logger logger = Logger.getLogger(this.getClass().getName());
            logger.log(Level.WARNING, "Errore nella scrittura del file, ERR: " + e.getMessage());

        }
    }

    private void writeTextInfoOnFile(final File file) {
        try (BufferedWriter bfw = new BufferedWriter(new OutputStreamWriter(
                new FileOutputStream(file), StandardCharsets.UTF_8))) {
            bfw.write(info.getFileModel().getFileName());
            bfw.newLine();
            bfw.close();
        } catch (IOException e) {
            final Logger logger = Logger.getLogger(this.getClass().getName());
            logger.log(Level.WARNING, "Errore nella scrittura del file, ERR: " + e.getMessage());
        }

    }
}
