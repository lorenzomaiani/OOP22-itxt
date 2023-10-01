package controller.file;

import utils.Path;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Test loader from file.
 */
public final class TextLoader {

    /**
     * Read from a specific file.
     * @return the message read
     */
    public String readFromFile() {
        String text = "";
        try (BufferedReader bfr = new BufferedReader(new FileReader(new File(Path.PATH_TO_FILE)))) {
            text = bfr.readLine();
            return text;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

}
