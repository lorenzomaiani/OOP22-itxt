package org.example.utils.constant;

import java.io.File;

/**
 * String utilities constant.
 */
public final class StringConstants {

    /**
     * The location of the css style code.
     */
    public static final String DARK_THEME_CSS_LOCATION = "/css/dark_style.css";

    /**
     * String path separator, depending on which operating system is running.
     */
    public static final String SEPARATOR = File.separator;

    /**
     * Main setting directory path.
     */
    public static final String PATH_TO_SETTING_DIRECTORY = System.getProperty("user.home")
            + StringConstants.SEPARATOR + "Documents" + StringConstants.SEPARATOR + "ITextSetting";

    /**
     * Main setting file path.
     */
    public static final String PATH_TO_SETTING_FILE = PATH_TO_SETTING_DIRECTORY + StringConstants.SEPARATOR + "Setting.bin";

    /**
     *
     */
    public static final String LINE_SEP = System.getProperty("line.separator");

    private StringConstants() {

    }

}

