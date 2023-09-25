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
import org.example.utils.graphics.FileChooserOption;
import org.example.utils.graphics.GraphicsUtil;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.net.URL;;
import java.util.ResourceBundle;

/**
 * Setting view implementation, Handling input from user in the setting GUI.
 */
public final class SettingViewImpl implements SettingView, Initializable, PropertyChangeListener {

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
        fontChoiceBox.setValue(controller.getMainFontInSetting());
        mainDirectoryTextField.setText(controller.getMainDirectoryInSetting());
        if (controller.getMainThemeInSetting().equals(Theme.DARK)) {
            this.darkCheckBox.setSelected(true);
            this.lightCheckBox.setSelected(false);
        } else {
            this.lightCheckBox.setSelected(true);
            this.darkCheckBox.setSelected(false);
        }
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
        lightCheckBox.setSelected(true);
        controller.setAppTheme(Theme.LIGHT);
    }

    @Override
    public void setThemeToDark() {
        //log("Theme dark");
        lightCheckBox.setSelected(false);
        darkCheckBox.setSelected(true);
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

    @Override
    public void applyAndClose() {
        this.borderPane.getScene().getWindow().hide();
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
        GraphicsUtil.changeAppTheme(borderPane.getScene(), this);
    }
}
