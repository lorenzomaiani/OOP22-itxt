package org.example.view.homeview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

import org.example.utils.FileChooserOption;
import org.example.utils.GraphicsUtil;

import java.net.URL;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.stream.Stream;

/**
 * Implementing the SessionView Interface.
 */
public final class SessionViewImpl implements SessionView, Initializable {

    private static final int MAX_LONG = 15;

    @FXML
    private BorderPane borderPane;

    @FXML
    private ChoiceBox<String> fontChoiceBox;

    @FXML
    private ChoiceBox<Integer> sizeChoiceBox;


    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        fontChoiceBox.getItems().addAll(Font.getFamilies());
        sizeChoiceBox.getItems().addAll(Stream.iterate(2, (x) -> x + 2).limit(SessionViewImpl.MAX_LONG).toList());
        fontChoiceBox.setOnAction(this::getFontValue);
        sizeChoiceBox.setOnAction(this::getSizeValue);
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
        //openFileChooser(FileChooserOption.SAVE);
        GraphicsUtil.openFileChooser(FileChooserOption.SAVE, "Salva file", borderPane.getScene().getWindow());
    }

    @Override
    public void startOpenDialog() {
        log("Start Open Dialog");
        //openFileChooser(FileChooserOption.OPEN);
        GraphicsUtil.openFileChooser(FileChooserOption.OPEN, "Apri file", borderPane.getScene().getWindow());
    }

    @Override
    public void newText() {
        log("New Text");
    }

//    private File openFileChooser(final FileChooserOption option) {
//        final FileChooser fileChooser = new FileChooser();
//       if(setting.getMainDirectory().isPresent()){
//           fileChooser.setInitialDirectory(setting.getMainDirectory());
//        }
//        if (option == FileChooserOption.SAVE) {
//            fileChooser.setTitle("Salvataggio");
//        } else {
//            fileChooser.setTitle("Apri");
//        }
//        return option == FileChooserOption.SAVE ? fileChooser.showSaveDialog(borderPane.getScene().getWindow())
//                : fileChooser.showOpenDialog(borderPane.getScene().getWindow());
//
//    }

    private void log(final String mess) {
        System.out.println(new Date() + " " +  mess);
    }

    private String getFontValue(final ActionEvent event) {
        log(fontChoiceBox.getValue());
        return null;
    }

    private String getSizeValue(final ActionEvent event) {
        log(sizeChoiceBox.getValue().toString());
        return null;
    }

}
