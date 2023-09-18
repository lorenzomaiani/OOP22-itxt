package org.example.controller.file;

import org.example.model.filemodel.FileModel;
import org.example.model.filemodel.FileModelImpl;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * SaveFileController implementation class.
 */
public class SaveFileControllerImpl implements SaveFileController, FileOperationController<Void, String> {

    private FileModel fileToSave;

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
                return new File(fileToSave.getFilePath()).createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
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
    public final Void operationOnFile(final String s) {
        if (s != null) {
            String[] values = s.split("\n");
            try (BufferedWriter bfw = new BufferedWriter(new FileWriter(fileToSave.getFilePath()))) {
                for (String value : values) {
                    bfw.write(value);
                    bfw.write("\n");
                }
                bfw.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
