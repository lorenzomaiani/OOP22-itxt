package org.example.controller.file;

import org.example.model.filemodel.FileModel;
import org.example.model.filemodel.FileModelImpl;
import org.example.utils.constant.StringConstants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * SaveFileController implementation class.
 */
public class SaveFileControllerImpl implements SaveFileController, FileOperationController<Void, String> {

    private final FileModel fileToSave;


    /**
     * Constructor.
     * @param path the path
     * @param name the name
     */
    public SaveFileControllerImpl(final String path, final String name) {
        fileToSave = new FileModelImpl(path, name);
    }

    @Override
    public final boolean createAFile() {
            try {
                final String pathWithoutFileName = fileToSave.getFilePath().replace(
                        fileToSave.getFileName(), "");
                final String directoryName = fileToSave.getFileName().split("\\.")[0];

                boolean result = new File(pathWithoutFileName + fileToSave.getFileName().split("\\.")[0]).mkdir();
                if (result) {
                    fileToSave.setFilePath(pathWithoutFileName + directoryName + StringConstants.SEPARATOR + fileToSave.getFileName()); // reset the previous selected path to saving
                    return new File(pathWithoutFileName + directoryName + StringConstants.SEPARATOR + fileToSave.getFileName()).createNewFile();
                }
            } catch (IOException e) {
                System.err.print("Error on creating a file, please retry");
            }
        return false;
    }

    @Override
    public final boolean isAlreadyExist() {
        return new File(fileToSave.getFilePath()).isFile();
    }

    @Override
    public final void saveOnFile(final String mess) {
        operationOnFile(mess);
    }

    @Override
    public FileModel getFileToSave() {
        return this.fileToSave;
    }


    @Override
    public final Void operationOnFile(final String s) {
        if (s != null) {
            final String[] values = s.split("\n");
            try (BufferedWriter bfw = new BufferedWriter(new FileWriter(fileToSave.getFilePath()))) {
                for (final String value : values) {
                    bfw.write(value);
                    bfw.write("\n");
                }
                bfw.close();
            } catch (IOException e) {
                System.err.print("Error on saving file, please retry");
            }
        }
        return null;
    }
}
