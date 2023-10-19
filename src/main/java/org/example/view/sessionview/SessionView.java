package org.example.view.sessionview;

import javafx.event.ActionEvent;

/**
 * The handler to all the input from user.
 */
public interface SessionView {
    /**
     * loading the setting stage calling the visualController.
     */
    void loadSettingStage();

    /**
     * to handle the exit event on the main stage, used to save info on exit with the controller.
     */
    void onExit();

    /**
     * open a save file dialog to handle the input from user
     * who want to save the current file.
     */
    void startSaveDialog();

    /**
     * open an open file dialog to handle the input from user
     * who want to open a file.
     */
    void startOpenDialog();

    /**
     * Open a save dialog to save the file with a name, hasn't got the control over the file open.
     */
    void startSaveWithName();

    /**
     * open a new file and append the text to the one already inside the file.
     */
    void startTextAcquisition();

    /**
     * clear the current text and create a new one.
     */
    void newText();

    /**
     * Get the user selected font.
     * @param event the event from the GUI
     */
    void selectedFont(ActionEvent event);

    /**
     * Get the size font selected by the user.
     * @param event the event from the GUI
     */
    void selectedSizeFont(ActionEvent event);

}
