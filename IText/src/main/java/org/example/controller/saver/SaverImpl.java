package org.example.controller.saver;

import org.example.model.info.Info;
import org.example.utils.constant.StringConstants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Implementation class of Saver interface.
 */
public final class SaverImpl implements Saver {
    private Info info;
    private String home = System.getProperty("user.home");
    private static final String PATH_TO_SETTING_DIRECTORY = System.getProperty("user.home")
            + StringConstants.SEPARATOR + "Documents" + StringConstants.SEPARATOR + "ITextSetting";
    private static final String PATH_TO_SETTING_FILE = PATH_TO_SETTING_DIRECTORY + StringConstants.SEPARATOR + "Setting.bin";

    /**
     * Constructor.
     * @param info the info
     */
    public SaverImpl(final Info info) {
        this.info = info;
    }

    @Override
    public void saveSettingOnClose() throws IOException {
        final File settingFile = new File(PATH_TO_SETTING_FILE);
        final File directory = new File(PATH_TO_SETTING_DIRECTORY);
        System.out.println("Path to direcotry: " + PATH_TO_SETTING_DIRECTORY);
        System.out.println("Path to file: " + PATH_TO_SETTING_FILE);
        if (!directory.isDirectory()) { // if directory doesn't exist...
            if (directory.mkdir()) { // create a directory
                if (!settingFile.isFile()) {  // if file doesn't exist...
                    settingFile.createNewFile();  // create a file
                }
            }
        }
        writeSettingOnFile(settingFile);
    }

    @Override
    public void saveFileInfo() {

    }

    /**
     *
     * @param file
     */
    private void writeSettingOnFile(final File file) {
        try (BufferedWriter bfw = new BufferedWriter(new FileWriter(file))) {
            bfw.write(info.getSetting().getMainDirectory());
            bfw.newLine();
            bfw.write(info.getSetting().getMainFont());
            bfw.newLine();
            bfw.write(info.getSetting().getAppTheme().toString());
            bfw.newLine();
            bfw.close();
        } catch (IOException e) {
            System.err.println("Errore nella scrittura del file, ERR: " + e.getMessage());
        }
    }
}
