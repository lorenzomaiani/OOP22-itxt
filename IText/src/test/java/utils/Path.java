package utils;

import java.io.File;

public class Path {
    public static final String SEP = File.separator;
    public static final String PATH_TO_DIR = System.getProperty("user.home") + SEP + "Documents" + SEP + "Test";
    public static final String PATH_TO_FILE = PATH_TO_DIR + SEP + "Test.txt";
}
