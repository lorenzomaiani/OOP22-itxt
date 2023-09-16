package org.example.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.Objects;

/**
 * Launcher class for the main application.
 */
public final class IText extends Application {
    private static final int MIN_WIDTH = 600;
    private static final int MIN_HEIGHT = 400;

    /**
     * Start methods.
     * @param primaryStage the primary stage for this application, onto which
     * the application scene can be set.
     * Applications may create other stages, if needed, but they will not be
     * primary stages.
     * @throws Exception if file FXML cannot be found
     */
    @Override
    public void start(final Stage primaryStage) throws Exception {
        final Parent root = FXMLLoader.load(ClassLoader.getSystemResource("layout/Home.fxml"));
        final Scene scene = new Scene(root);
        primaryStage.setMinWidth(IText.MIN_WIDTH);
        primaryStage.setMinHeight(IText.MIN_HEIGHT);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(IText.class.getResourceAsStream("/icon/IText_icon.png"))));
        primaryStage.setTitle("IText");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}

