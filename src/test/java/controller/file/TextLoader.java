package controller.file;

import utils.Path;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Test loader from file.
 */
public final class TextLoader {

    /**
     * Read from a specific file.
     * @return the message read
     */
    public String readFromFile() {
        String text;
        try (BufferedReader bfr = new BufferedReader(new InputStreamReader(
                new FileInputStream(Path.PATH_TO_FILE), StandardCharsets.UTF_8.name()))) {
            text = bfr.readLine();
            return text;
        } catch (IOException e) {
            final Logger logger = Logger.getLogger(this.getClass().getName());
            logger.log(Level.WARNING, "Errore - impossibile aprire il file");
            return null;
        }
    }

}
