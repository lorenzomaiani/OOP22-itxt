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
            try{
                System.out.println(fileToSave.getFilePath().replace(fileToSave.getFileName(), ""));
                boolean result = new File(fileToSave.getFilePath().replace(fileToSave.getFileName(), "")).createNewFile();
                if (result){
                    return new File(fileToSave.getFilePath()).createNewFile();
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
