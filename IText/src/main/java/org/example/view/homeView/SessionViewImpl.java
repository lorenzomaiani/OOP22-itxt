package org.example.view.homeView;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;
import javafx.stage.FileChooser;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Implementing the SessionView Interface
 */
public class SessionViewImpl implements SessionView, Initializable {

    @FXML
    private BorderPane borderPane;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {

    }

    @Override
    public void loadSettingStage() {

    }

    @Override
    public void updateGui() {

    }

    @Override
    public void startSaveDialog() {

    }

    @Override
    public void startOpenDialog() {

    }

    @Override
    public void newText() {

    }

    private File openFileChooser() {
        FileChooser fileChooser = new FileChooser();
//        if(setting.getMainDirectory().isPresent()){
//            fileChooser.setInitialDirectory(setting.getMainDirectory());
//        }
        return fileChooser.showOpenDialog(borderPane.getScene().getWindow());

    }
}
