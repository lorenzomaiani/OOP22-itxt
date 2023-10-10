package org.example.controller.file;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import org.example.model.filemodel.FileModel;
import org.example.model.filemodel.FileModelImpl;
import org.example.utils.constant.StringConstants;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * SaveFileController implementation class.
 */
public final class SaveFileControllerImpl implements SaveFileController, FileOperationController<Void, String> {

    private final FileModel fileToSave;
    private SaveType saveType = SaveType.TXT;


    /**
     * Constructor.
     * @param path the path
     * @param name the name
     */
    public SaveFileControllerImpl(final String path, final String name) {
        fileToSave = new FileModelImpl(path, name);
    }

    @Override
    public boolean createAFile() {
            try {
                final String pathWithoutFileName = fileToSave.getFilePath().replace(
                        fileToSave.getFileName(), "");
                final String directoryName = fileToSave.getFileName().split("\\.")[0];

                boolean result = new File(pathWithoutFileName + fileToSave.getFileName().split("\\.")[0]).mkdir();
                if (result) {
                    fileToSave.setFilePath(pathWithoutFileName + directoryName
                            + StringConstants.SEPARATOR + fileToSave.getFileName()); // reset the previous selected path to saving
                    return new File(pathWithoutFileName + directoryName
                            + StringConstants.SEPARATOR + fileToSave.getFileName()).createNewFile();
                }
            } catch (IOException e) {
                System.err.print("Error on creating a file, please retry");
            }
        return false;
    }

    @Override
    public boolean isAlreadyExist() {
        return new File(fileToSave.getFilePath()).isFile();
    }

    @Override
    public void saveOnFile(final String mess) {
        operationOnFile(mess);
    }

    @Override
    public FileModel getFileToSave() {
        return this.fileToSave;
    }


    @Override
    public Void operationOnFile(final String s) {
        if (s != null) {
            computeFileSaveType();
            switch (saveType) {
                case TXT -> {
                    final String[] values = s.split("\n");
                    try (BufferedWriter bfw = new BufferedWriter(new FileWriter(fileToSave.getFilePath(),
                            StandardCharsets.UTF_8))) {
                        for (final String value : values) {
                            bfw.write(value);
                            bfw.write("\n");
                        }
                        bfw.close();
                    } catch (IOException e) {
                        System.err.print("Error on saving file, please retry");
                    }
                }
                case PDF -> {
                    Document document = new Document();
                    try {
                        PdfWriter.getInstance(document, new FileOutputStream(fileToSave.getFilePath()));
                        document.open();
                        String[] lines = s.split("\n");
                        for (var l : lines) {
                            document.add(new Paragraph(l));
                        }

                        document.close();
                    } catch (DocumentException | FileNotFoundException e) {
                        System.err.println("Error on saving file, please retry");
                    }
                }
                default -> {
                    return null;
                }
            }
        }
        return null;
    }


    /**
     * Decide the file type by the file name.
     */
    private void computeFileSaveType() {
        if (fileToSave.getFileName().contains(".txt")) {
            this.saveType = SaveType.TXT;
        } else if (fileToSave.getFileName().contains(".pdf")) {
            this.saveType = SaveType.PDF;
        }
    }
}
