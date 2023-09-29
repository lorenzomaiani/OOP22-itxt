package controller.file;

import utils.Path;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class TextSaver {

    public boolean createAFile() {
        try {
            boolean result = new File(Path.PATH_TO_DIR).mkdir();
            if (result) {
                return new File(Path.PATH_TO_FILE).createNewFile();
            }
        } catch (IOException e) {
            System.err.print("Error on creating a file, please retry");
        }
        return false;
    }

    public boolean removeFile(){
        return new File(Path.PATH_TO_FILE).delete() && new File(Path.PATH_TO_DIR).delete();
    }


    public void writeOnFile(final String mess){
        try (BufferedWriter brw = new BufferedWriter(new FileWriter(new File(Path.PATH_TO_FILE)))) {
            brw.write(mess);
            brw.close();
        } catch (IOException e) {
             e.printStackTrace();
        }
    }
}
