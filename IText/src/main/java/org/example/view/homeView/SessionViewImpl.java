package org.example.view.homeView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;

/**
 * Implementing the SessionView Interface.
 */
public final class SessionViewImpl implements SessionView, Initializable {

    @FXML
    private BorderPane borderPane;

    private enum FileChooserOption { SAVE, OPEN }


    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }

    @Override
    public void loadSettingStage() {
        log("Load setting");
    }

    @Override
    public void updateGui() {
        log("Update Gui");
    }

    @Override
    public void startSaveDialog() {
        log("Start save dialog");
        openFileChooser(FileChooserOption.SAVE);
    }

    @Override
    public void startOpenDialog() {
        log("Start Open Dialog");
        openFileChooser(FileChooserOption.OPEN);
    }

    @Override
    public void newText() {
        log("New Text");
    }

    private File openFileChooser(final FileChooserOption option) {
        final FileChooser fileChooser = new FileChooser();
//        if(setting.getMainDirectory().isPresent()){
//            fileChooser.setInitialDirectory(setting.getMainDirectory());
//        }
        if (option == FileChooserOption.SAVE) {
            fileChooser.setTitle("Salvataggio");
        } else {
            fileChooser.setTitle("Apri");
        }
        return option == FileChooserOption.SAVE ? fileChooser.showSaveDialog(borderPane.getScene().getWindow()) : fileChooser.showOpenDialog(borderPane.getScene().getWindow());

    }

    private void log(final String mess) {
        System.out.println(new Date() + " " +  mess);
    }
}
