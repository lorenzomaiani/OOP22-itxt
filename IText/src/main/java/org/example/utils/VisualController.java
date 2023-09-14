package org.example.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
import java.util.Optional;

public class VisualController {

    /**
     * static method which change the scene in a specific stage
     * @param FXMLFileName the name of the FXML file which it wants to load
     * @param event event passed from an interration wiht a button
     * @throws Exception can be call if the FXML file doesn't exist
     */
    public static void changeScene(String FXMLFileName, ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(ClassLoader.getSystemResource(FXMLFileName));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }

    /**
     * static method to load a new stage above another
     * @param FXMLFileName the name of the FXML file to load
     * @param event event from a pressed button
     * @param stageTitle title of the new stage
     * @param minWidth optional param, set the minimal width of the stage
     * @param minHeigth optional param, set the minimal height of the stage
     * @throws IOException can be call if the FXML file doesn't exist
     */

    public static void chageStage(String FXMLFileName, ActionEvent event, String stageTitle,
                                  Optional<Integer> minWidth, Optional<Integer> minHeigth) throws IOException {
        Parent root = FXMLLoader.load(ClassLoader.getSystemResource(FXMLFileName));
        Stage newStage = new Stage();
        Scene newScene = new Scene(root);
        newStage.setScene(newScene);
        newStage.setTitle(stageTitle);
        if(minHeigth.isPresent() && minWidth.isPresent()){
            newStage.setMinHeight(minHeigth.get());
            newStage.setMinWidth(minWidth.get());
        }
        newStage.show();
    }
}
