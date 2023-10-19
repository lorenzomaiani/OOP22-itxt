package org.example.controller.file;

import org.example.model.filemodel.FileModel;
import org.example.model.filemodel.FileModelImpl;
import org.example.utils.constant.StringConstants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 * Open Controller class, execute function to get content from text.
 */
public class OpenFileControllerImpl implements OpenFileController, FileOperationController<String, Void> {

    private final FileModel fileToOpen;

    /**
     * Constructor.
     * @param path the path
     * @param name the name
     */
    public OpenFileControllerImpl(final String path, final String name) {
        fileToOpen = new FileModelImpl(path, name);
    }


    @Override
    public final boolean isAnExistingFile() {
        return fileToOpen.getFilePath() != null && new File(fileToOpen.getFilePath()).isFile();
    }

    @Override
    public final String getTextFromText() {
       return operationOnFile(null);
    }

    /**
     * Javadoc inside interface.
     * @return the file
     */
    @Override
    public FileModel getOpenedFile() {
        return this.fileToOpen;
    }


    @Override
    public final String operationOnFile(final Void unused) {
        String temp;
        final StringBuilder text = new StringBuilder();
        try (BufferedReader bfr = new BufferedReader(new FileReader(this.fileToOpen.getFilePath(), StandardCharsets.UTF_8))) {
            temp = bfr.readLine();
            while (temp  != null) {
                text.append(temp).append(StringConstants.LINE_SEP);
                temp = bfr.readLine();
            }
            return text.toString();
        } catch (IOException e) {
            final Logger logger = Logger.getLogger(this.getClass().getName());
            logger.log(Level.WARNING, "Errore - impossibile eseguire l'operazione");
        }
        return null;
    }
}
