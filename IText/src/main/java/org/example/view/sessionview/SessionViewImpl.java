package org.example.view.sessionview;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import org.example.controller.session.SessionController;
import org.example.controller.session.SessionControllerImpl;
import org.example.model.setting.Setting;
import org.example.model.setting.SettingImpl;
import org.example.utils.constant.NumericConstants;
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

    private Stage stage;

    private SessionController controller;

    private boolean isTextAlreadySaved = false;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        final SettingImpl setting = SettingImpl.getInstance();
        controller = new SessionControllerImpl();
        controller.loadInfoOnOpen();
        fontChoiceBox.getItems().addAll(Font.getFamilies());
        sizeChoiceBox.getItems().addAll(Stream.iterate(2, (x) -> x + 2).limit(NumericConstants.MAX_LONG).toList());
        fontChoiceBox.setOnAction(this::getFontValue);
        sizeChoiceBox.setOnAction(this::getSizeValue);
        fontChoiceBox.setValue(setting.getMainFont());
        sizeChoiceBox.setValue(NumericConstants.DEFAULT_TEXT_SIZE);
        fontChoiceBox.setOnAction(this::getSelectedFont);
        sizeChoiceBox.setOnAction(this::getSelectedSizeFont);
        setting.addPropertyChangeListener(this);
        initTextAreaOnChangeMethods();
        initGUI(setting);
        newText();
    }

    @Override
    public void loadSettingStage() {
        log("Load setting");
        try {
            VisualController.chageStage("layout/Setting.fxml", "Setting",
                    new Image(Objects.requireNonNull(SessionViewImpl.class.getResourceAsStream("/icon/setting.png"))),
                    NumericConstants.MIN_SETTING_STAGE_WIDTH, NumericConstants.MIN_SETTING_STAGE_HEIGHT, this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void startSaveDialog() {
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
        isTextAlreadySaved = true;
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
    public void startTextAcquisition() {
         final File af = GraphicsUtil.openFileChooser(FileChooserOption.OPEN,
                 "Seleziona file", borderPane.getScene().getWindow());
         if (af != null) {
             final String aText = controller.openFile(af.getPath(), af.getName());
             final StringBuilder stringBuilder = new StringBuilder();
             stringBuilder.append(textArea.getText());
             textArea.clear();
             stringBuilder.append("\n");
             stringBuilder.append(aText);
             textArea.setText(stringBuilder.toString());
         }

     }

    @Override
    public void newText() {
        log("New Text");
        textArea.clear();
        infoFile.setText("Nuovo file");
        controller.restoreFileInfo();
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        GraphicsUtil.changeAppTheme(borderPane.getScene(), this);
    }

    @Override
    public void onExit() {
        log("On exit...");
        if (!isTextAlreadySaved) {
            showExitDialog();
        } else {
            stage = (Stage) borderPane.getScene().getWindow();
            stage.close();
        }
        try {
            controller.saveInfoOnClose();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    private void initTextAreaOnChangeMethods() {
        textArea.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(final ObservableValue<? extends String> observable,
                                final String oldValue, final String newValue) {
                isTextAlreadySaved = false;
            }
        });
    }

    private void showExitDialog() {
        final ButtonType sureButton = new ButtonType("Sono sicuro", ButtonBar.ButtonData.YES);
        final ButtonType saveButton = new ButtonType("Salva", ButtonBar.ButtonData.NO);
        final Alert exitAlert = new Alert(Alert.AlertType.CONFIRMATION,
                "Il file e' stato modificato e non salvato, sei sicuro di voler uscire senza salvare?",
                sureButton, saveButton, ButtonType.CANCEL);
        exitAlert.setTitle("Esci");
        exitAlert.setHeaderText("L'app sta per chiudersi");
        final Optional<ButtonType> result = exitAlert.showAndWait();
        if (result.isPresent()) {
            ButtonType buttonType = result.get();
            if (buttonType.equals(sureButton)) {
                log("Exit without saving");
                stage = (Stage) borderPane.getScene().getWindow();
                stage.close();
            } else if (buttonType.equals(saveButton)) {
                log("Save");
                startSaveDialog();
                stage = (Stage) borderPane.getScene().getWindow();
                stage.close();
            } else if (buttonType.equals(ButtonType.CANCEL)) {
                log("Hide");
                exitAlert.close();
            }
        }
    }

    private void initGUI(final Setting setting) {
        textArea.setWrapText(true);
        textArea.setStyle("-fx-font-family: '" + setting.getMainFont() + "';");
    }

    public void getSelectedFont(final ActionEvent event) {
        String selectedFont = fontChoiceBox.getValue();
        textArea.setStyle("-fx-font-family: '" + selectedFont + "';");
    }
    public void getSelectedSizeFont(final ActionEvent event) {
        int selectedSize = sizeChoiceBox.getValue();
        textArea.setStyle("-fx-font-size: " + selectedSize + "pt;");
    }

}
