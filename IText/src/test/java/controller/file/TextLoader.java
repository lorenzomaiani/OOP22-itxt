package controller.file;

import utils.Path;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class TextLoader {

    public String readFromFile(){
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
