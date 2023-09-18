package org.example.view.settingView;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import org.example.utils.graphics.FileChooserOption;
import org.example.utils.graphics.GraphicsUtil;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

/**
 * Setting view implementation, Handling input from user in the setting GUI.
 */
public final class  SettingViewImpl implements SettingView, Initializable {

    @FXML
    private BorderPane borderPane;

    @FXML
    private ChoiceBox<String> fontChoiceBox;

    @FXML
    private TextField mainDirectoryTextField;

    @FXML
    private CheckBox darkCheckBox;

    @FXML
    private CheckBox lightCheckBox;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        fontChoiceBox.getItems().addAll(Font.getFamilies());
        fontChoiceBox.setOnAction(this::getFontFromChoiceBox);
    }

    @Override
    public void getMainDirectoryInput() {
       log(mainDirectoryTextField.getText());
    }

    @Override
    public void setThemeToLight() {
        log("Theme light");
        darkCheckBox.setSelected(false);
    }

    @Override
    public void setThemeToDark() {
        log("Theme dark");
        lightCheckBox.setSelected(false);
    }

    @Override
    public void searchIntoFileSystem() {
        File f = GraphicsUtil.openFileChooser(FileChooserOption.OPEN, "Sfoglia", borderPane.getScene().getWindow());
    }

    private void getFontFromChoiceBox(final ActionEvent event) {
        log(fontChoiceBox.getValue());
    }

    private void log(final String msg) {
        System.out.println(msg);
    }


}
