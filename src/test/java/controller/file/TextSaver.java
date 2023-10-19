package controller.file;

import utils.Path;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Test saving text and creating a new file.
 */
public final class TextSaver {

    /**
     * Create a simple file on a specific path.
     * @return true if file is created correctly, false if file is already in system or if some error are occurred
     */
    public boolean createAFile() {
        try {
            final boolean result = new File(Path.PATH_TO_DIR).mkdir();
            if (result) {
                return new File(Path.PATH_TO_FILE).createNewFile();
            }
        } catch (IOException e) {
            final Logger logger = Logger.getLogger(this.getClass().getName());
            logger.log(Level.WARNING, "Errore - impossibile creare il file");
        }
        return false;
    }

    /**
     * Remove a specific file.
     * @return true if deleting is ended successfully, false otherwise
     */
    public boolean removeFile() {
        return new File(Path.PATH_TO_FILE).delete() && new File(Path.PATH_TO_DIR).delete();
    }


    /**
     * Write text mess on file.
     * @param mess the text that has to be written
     */
    public void writeOnFile(final String mess) {
        try (BufferedWriter brw = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(Path.PATH_TO_FILE), StandardCharsets.UTF_8.name()))) {
            brw.write(mess);
            brw.close();
        } catch (IOException e) {
            final Logger logger = Logger.getLogger(this.getClass().getName());
            logger.log(Level.WARNING, "Errore - impossibile salvare il file");
        }
    }
}
