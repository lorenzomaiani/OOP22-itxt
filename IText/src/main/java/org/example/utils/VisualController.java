package org.example.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;

/**
 * Utilities class to change the context of the application, switching scene or directly the stage.
 */
public final class VisualController {

    /**
     * static method which change the scene in a specific stage.
     * @param fxmlFileName the name of the FXML file which it wants to load
     * @param event event passed from an interration wiht a button
     * @throws Exception can be call if the FXML file doesn't exist
     */
    public static void changeScene(final String fxmlFileName, final ActionEvent event) throws IOException {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource(fxmlFileName));
        final Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        final Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * static method to load a new stage above another.
     * @param fxmlFileName the name of the FXML file to load
     * @param event event from a pressed button
     * @param stageTitle title of the new stage
     * @param minWidth optional param, set the minimal width of the stage
     * @param minHeigth optional param, set the minimal height of the stage
     * @throws IOException can be call if the FXML file doesn't exist
     */

    public static void chageStage(final String fxmlFileName, final ActionEvent event, final String stageTitle, final Image iconImage,
                                  final Optional<Double> minWidth, final Optional<Double> minHeigth) throws IOException {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource(fxmlFileName));
        final Stage newStage = new Stage();
        final Scene newScene = new Scene(root);
        newStage.setScene(newScene);
        newStage.setTitle(stageTitle);
        newStage.getIcons().add(iconImage);
        minHeigth.ifPresent(x -> newStage.setMinWidth(minHeigth.get()));
        minWidth.ifPresent(x -> newStage.setMinWidth(minWidth.get()));
        newStage.show();
    }
}
