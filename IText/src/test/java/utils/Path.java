package utils;

import java.io.File;

/**
 * Util class to have path on file and directory.
 */
public final class Path {

    /**
     * Constructor.
     */
    private Path(){

    }

    /**
     * File separator.
     */
    public static final String SEP = File.separator;

    /**
     * The path to main directory.
     */
    public static final String PATH_TO_DIR = System.getProperty("user.home") + SEP + "Documents" + SEP + "Test";

    /**
     * The path to file.
     */
    public static final String PATH_TO_FILE = PATH_TO_DIR + SEP + "Test.txt";
}
