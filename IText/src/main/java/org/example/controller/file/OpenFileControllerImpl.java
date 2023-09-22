package org.example.controller.file;

import org.example.model.filemodel.FileModel;
import org.example.model.filemodel.FileModelImpl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

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
        try (BufferedReader bfr = new BufferedReader(new FileReader(this.fileToOpen.getFilePath()))) {
            while ((temp = bfr.readLine()) != null) {
                text.append(temp).append("\n");
            }
            return text.toString();
        } catch (IOException e) {
            System.err.print("Error on Opening a file, please retry");
        }
        return null;
    }
}
