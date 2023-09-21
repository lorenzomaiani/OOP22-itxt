package org.example.view.settingview;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import org.example.controller.setting.SettingController;
import org.example.controller.setting.SettingControllerImpl;
import org.example.model.setting.SettingImpl;
import org.example.model.setting.Theme;
import org.example.utils.constant.Constants;
import org.example.utils.graphics.FileChooserOption;
import org.example.utils.graphics.GraphicsUtil;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.URL;
import java.util.Objects;
import java.util.ResourceBundle;

/**
 * Setting view implementation, Handling input from user in the setting GUI.
 */
public final class  SettingViewImpl implements SettingView, Initializable, PropertyChangeListener {

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

    private SettingController controller;

    @Override
    public void initialize(final URL location, final ResourceBundle resources) {
        fontChoiceBox.getItems().addAll(Font.getFamilies());
        fontChoiceBox.setOnAction(this::getFontFromChoiceBox);
        controller = new SettingControllerImpl();
        SettingImpl.getInstance().addPropertyChangeListener(this);
    }

    @Override
    public String getMainDirectoryInput() {
       log(mainDirectoryTextField.getText());
       controller.setMainDirectoryInSetting(mainDirectoryTextField.getText());
       return mainDirectoryTextField.getText();
    }

    @Override
    public void setThemeToLight() {
        //log("Theme light");
        darkCheckBox.setSelected(false);
        controller.setAppTheme(Theme.LIGHT);
    }

    @Override
    public void setThemeToDark() {
        //log("Theme dark");
        lightCheckBox.setSelected(false);
        controller.setAppTheme(Theme.DARK);
    }

    @Override
    public void searchIntoFileSystem() {
        final File f = GraphicsUtil.openFileChooser(FileChooserOption.DIRECTORY, "Sfoglia", borderPane.getScene().getWindow());
        if (f != null && f.isDirectory()) {
            mainDirectoryTextField.setText(f.getAbsolutePath());
            controller.setMainDirectoryInSetting(mainDirectoryTextField.getText());
        }
    }

    private String getFontFromChoiceBox(final ActionEvent event) {
        log(fontChoiceBox.getValue());
        controller.setFontInSetting(fontChoiceBox.getValue());
        return fontChoiceBox.getValue();
    }

    private void log(final String msg) {
        System.out.println(msg);
    }

    @Override
    public void propertyChange(final PropertyChangeEvent evt) {
        if (!SettingImpl.getInstance().getAppTheme().equals(Theme.DARK)){
            borderPane.getScene().getStylesheets().add(Objects.requireNonNull(this.getClass().getResource(Constants.DARK_THEME_CSS_LOCATION)).toExternalForm());
        } else {
            borderPane.getScene().getStylesheets().remove(Objects.requireNonNull(this.getClass().getResource(Constants.DARK_THEME_CSS_LOCATION)).toExternalForm());
        }
    }
}
