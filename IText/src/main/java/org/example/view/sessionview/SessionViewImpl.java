package org.example.view.sessionview;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.input.InputMethodEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;

import javafx.scene.text.Text;
import org.example.app.IText;
import org.example.controller.session.SessionController;
import org.example.controller.session.SessionControllerImpl;
import org.example.model.setting.SettingImpl;
import org.example.utils.constant.Constants;
import org.example.utils.graphics.FileChooserOption;
import org.example.utils.graphics.GraphicsUtil;
import org.example.utils.visualmanager.VisualController;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
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
public final class SessionViewImpl implements SessionView, Initializable, PropertyChangeListener {

    @FXML
    private BorderPane borderPane;

    @FXML
    private ChoiceBox<String> fontChoiceBox;

    @FXML
    private ChoiceBox<Integer> sizeChoiceBox;

    @FXML
    private TextArea textArea;

    @FXML
    private Label infoFile;

    private SessionController controller;

    private boolean isTextAlreadySaved = false;


    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        fontChoiceBox.getItems().addAll(Font.getFamilies());
        sizeChoiceBox.getItems().addAll(Stream.iterate(2, (x) -> x + 2).limit(Constants.MAX_LONG).toList());
        fontChoiceBox.setOnAction(this::getFontValue);
        sizeChoiceBox.setOnAction(this::getSizeValue);
        controller = new SessionControllerImpl();
        initTextAreaOnChangeMethods();
        SettingImpl.getInstance().addPropertyChangeListener(this);
        newText();
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
        isTextAlreadySaved = true;
        if (controller.isFileInfoSetted()) {
            log("Exist an opened file");
            controller.saveFile(textArea.getText(), controller.getFileInfo().getFilePath(),
                    controller.getFileInfo().getFileName());
            infoFile.setText("Salvataggio in: " + controller.getFileInfo().getFileName());
        } else {
            log("Start save dialog");
            final File sf = GraphicsUtil.openFileChooser(FileChooserOption.SAVE, "Salva file", borderPane.getScene().getWindow());
            if (sf != null) {
                controller.saveFile(textArea.getText(), sf.getPath(), sf.getName());
                infoFile.setText("Salvataggio in: " + sf.getName());
            }
        }
    }

    @Override
    public void startOpenDialog() {
        log("Start Open Dialog");
        final File of = GraphicsUtil.openFileChooser(FileChooserOption.OPEN, "Apri file", borderPane.getScene().getWindow());
        if (of != null) {
            textArea.setText(controller.openFile(of.getPath(), of.getName()));
            infoFile.setText("File: " + of.getName());
        }
    }

    @Override
    public void newText() {
        log("New Text");
        textArea.setText("");
        infoFile.setText("Nuovo file");
        controller.restoreFileInfo();
    }

    private void log(final String mess) {
        System.out.println(new Date() + " " +  mess);
    }

    private String getFontValue(final ActionEvent event) {
        log(fontChoiceBox.getValue());
        return fontChoiceBox.getValue();
    }

    private int getSizeValue(final ActionEvent event) {
        log(sizeChoiceBox.getValue().toString());
        return sizeChoiceBox.getValue();
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        GraphicsUtil.changeAppTheme(borderPane.getScene(), this);
    }

    @Override
    public void onExit() {
        log("On exit");
        if (!isTextAlreadySaved) {
            log("Text need to be saved");
            // dialog e conseguente salvataggio
        } else {
            log("Exit easy");
        }

    }

    private void initTextAreaOnChangeMethods(){
        textArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable, final String oldValue, final String newValue) {
                isTextAlreadySaved = false;
            }
        });
    }
}
