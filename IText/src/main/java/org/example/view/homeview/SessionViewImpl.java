package org.example.view.homeview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

import org.example.controller.session.SessionController;
import org.example.controller.session.SessionControllerImpl;
import org.example.utils.constant.Constants;
import org.example.utils.graphics.FileChooserOption;
import org.example.utils.graphics.GraphicsUtil;
import org.example.utils.VisualController;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.Objects;
import java.util.Optional;
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

    @FXML
    private TextArea textArea;

    private SessionController controller;


    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        fontChoiceBox.getItems().addAll(Font.getFamilies());
        sizeChoiceBox.getItems().addAll(Stream.iterate(2, (x) -> x + 2).limit(SessionViewImpl.MAX_LONG).toList());
        fontChoiceBox.setOnAction(this::getFontValue);
        sizeChoiceBox.setOnAction(this::getSizeValue);
        controller = new SessionControllerImpl();
    }

    @Override
    public void loadSettingStage() {
        log("Load setting");
        try {
            VisualController.chageStage("layout/Setting.fxml", "Setting",
                    new Image(Objects.requireNonNull(SessionViewImpl.class.getResourceAsStream("/icon/setting.png"))),
                    Optional.of(Constants.MIN_STAGE_WIDTH), Optional.of(Constants.MIN_STAGE_HEIGHT));
        } catch (IOException e) {
            System.err.print("File not found, pls enter a valid file before");
        }

    }

    @Override
    public void updateGui() {
        log("Update Gui");

    }

    @Override
    public void startSaveDialog() {
        log("Start save dialog");
        //openFileChooser(FileChooserOption.SAVE);
        File sf = GraphicsUtil.openFileChooser(FileChooserOption.SAVE, "Salva file", borderPane.getScene().getWindow());
        if (sf != null) {
            controller.saveFile(textArea.getText(), sf.getPath(), sf.getName());
        }

    }

    @Override
    public void startOpenDialog() {
        log("Start Open Dialog");
        //openFileChooser(FileChooserOption.OPEN);
        File of = GraphicsUtil.openFileChooser(FileChooserOption.OPEN, "Apri file", borderPane.getScene().getWindow());
        if (of != null) {
            textArea.setText(controller.openFile(of.getPath(), of.getName()));
        }
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

    private void getFontValue(final ActionEvent event) {
        log(fontChoiceBox.getValue());
    }

    private void getSizeValue(final ActionEvent event) {
        log(sizeChoiceBox.getValue().toString());
    }

}
