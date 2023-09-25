package org.example.controller.loader;

import org.example.model.info.Info;
import org.example.model.setting.Theme;
import org.example.utils.constant.StringConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Objects;

/**
 * Loader implementation.
 */
public final class LoaderImpl implements Loader {
    private Info info;

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
            String[] res = Objects.requireNonNull(readSettingFromFile(settingFile)).split("\n");
            info.getSetting().setMainDirectory(res[0]);
            info.getSetting().setMainFont(res[1]);
            if (res[2].equals("LIGHT")) {
                info.getSetting().setAppTheme(Theme.LIGHT);
            } else {
                info.getSetting().setAppTheme(Theme.DARK);
            }
        }
    }

    @Override
    public void loadFileInfo() {

    }

    private String readSettingFromFile(final File file) {
        final StringBuilder res = new StringBuilder();
        String line = "";
        try (BufferedReader bfr = new BufferedReader(new FileReader(file))) {
            while ((line = bfr.readLine()) != null) {
                res.append(line).append("\n");
            }
            return res.toString();
        } catch (IOException e) {
            System.err.println(e.getMessage());
            return null;
        }
    }

}
