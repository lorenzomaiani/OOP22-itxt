package org.example.controller.loader;

import org.example.model.info.Info;
import org.example.model.setting.Theme;
import org.example.utils.constant.StringConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Loader implementation.
 */
public final class LoaderImpl implements Loader {
    private final Info info;

    /**
     * Constructor.
     * @param info
     */
    public LoaderImpl(final Info info) {
        this.info = info;
    }


    @Override
    public void loadSettingInfo() {
        final File settingFile = new File(StringConstants.PATH_TO_SETTING_FILE);
        if (settingFile.isFile()) {  // app have been open at least one time
            final String[] res = Objects.requireNonNull(readSettingFromFile(settingFile)).split("\n");
            info.getSetting().setMainDirectory(res[0]);
            info.getSetting().setMainFont(res[1]);
            final String themeString = res[2];
            if ("LIGHT".equals(themeString)) {
                info.getSetting().setAppTheme(Theme.LIGHT);
            } else {
                info.getSetting().setAppTheme(Theme.DARK);
            }
        }
    }

    @Override
    public void loadTextFileInfo() {
        final String pathToInfoFile = info.getFileModel().getFilePath().replace(info.getFileModel().getFileName(), "")
                + info.getFileModel().getFileName().split("\\.")[0] + "info.ini";
        final File infoTextFile = new File(pathToInfoFile);
        if (infoTextFile.isFile()) {
            final String[] res = Objects.requireNonNull(readTextInfoFromFile(infoTextFile)).split("\n");
            if (!res[0].isEmpty()){
                info.getFileModel().setFileName(res[0]);
            }

        }
    }

    private String readSettingFromFile(final File file) {
        final StringBuilder res = new StringBuilder();
        String line;
        try (BufferedReader bfr = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8))) {
            line = bfr.readLine();
            while (line != null) {
                res.append(line).append('\n');
                line = bfr.readLine();
            }
            return res.toString();
        } catch (IOException e) {
            final Logger logger = Logger.getLogger(this.getClass().getName());
            logger.log(Level.WARNING, "Errore - impossibile eseguire l'operazione");
            return null;
        }
    }

    private String readTextInfoFromFile(final File file) {
        final StringBuilder stringBuilder = new StringBuilder();
        String line;
        try (BufferedReader bfr = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8))) {
            line = bfr.readLine();
            while (line  != null) {
                stringBuilder.append(line);
                stringBuilder.append('\n');
                line = bfr.readLine();
            }
            return stringBuilder.toString();
        } catch (IOException e) {
            final Logger logger = Logger.getLogger(this.getClass().getName());
            logger.log(Level.WARNING, "Errore - impossibile eseguire l'operazione");
            return null;
        }
    }
}
