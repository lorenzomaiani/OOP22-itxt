package org.example.app;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.example.utils.constant.NumericConstants;
import org.example.view.sessionview.SessionViewImpl;

import java.util.Objects;

/**
 * Launcher class for the main application.
 */
public final class IText extends Application {

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
        final FXMLLoader loader = new FXMLLoader(ClassLoader.getSystemResource("layout/Home.fxml"));
        final Parent root = loader.load();
        final SessionViewImpl controller = loader.getController();
        Scene scene = new Scene(root);
        primaryStage.setMinWidth(NumericConstants.MIN_STAGE_WIDTH);
        primaryStage.setMinHeight(NumericConstants.MIN_STAGE_HEIGHT);
        primaryStage.getIcons().add(new Image(Objects.requireNonNull(IText.class.getResourceAsStream("/icon/itxtIcon.png"))));
        primaryStage.setTitle("IText");
        primaryStage.setOnCloseRequest(event -> {
            event.consume();
            controller.onExit();
        });
        primaryStage.setScene(scene);
        primaryStage.show();
    }



}

