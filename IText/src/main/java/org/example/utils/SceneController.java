package org.example.utils;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SceneController {

    /**
     * static method which change the scene in a specific stage
     * @param FXMLFileName the name of the FXML file which it wants to load
     * @param event event passed from an interration wiht a button
     * @throws Exception
     */
    public static void changeScene(String FXMLFileName, ActionEvent event) throws Exception{
        Parent root = FXMLLoader.load(ClassLoader.getSystemResource(FXMLFileName));
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        Scene scene = new Scene(root);
        stage.setScene(scene);
    }
}
