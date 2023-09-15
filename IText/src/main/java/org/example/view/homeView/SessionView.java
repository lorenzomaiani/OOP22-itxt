package org.example.view.homeView;

/**
 * The handler to all the input from user.
 */
public interface SessionView {
    /**
     * loading the setting stage calling the visualController.
     */
    void loadSettingStage();

    /**
     * refresh the SessionGui if something is changed.
     */
    void updateGui();

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
     * clear the current text and create a new one.
     */
    void newText();


}
