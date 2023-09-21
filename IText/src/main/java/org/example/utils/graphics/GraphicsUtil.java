package org.example.utils.graphics;

import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.example.model.setting.SettingImpl;
import org.example.model.setting.Theme;
import org.example.utils.constant.Constants;

import java.io.File;
import java.util.Objects;

/**
 * Utility class used to create single element to display.
 */
public final class GraphicsUtil {

    private GraphicsUtil() {

    }


    /**
     * Create a File Chooser.
     * @param option declare if is a SAVE or OPEN file chooser.
     * @param title the title of the file chooser.
     * @param window the main window, where to display the file chooser.
     * @return the file selected from the user.
     */
    public static File openFileChooser(final FileChooserOption option, final String title, final Window window) {
       switch (option) {
           case SAVE:
               FileChooser fileChooser = new FileChooser();
               if (!SettingImpl.getInstance().getMainDirectory().equals("")) {
                   fileChooser.setInitialDirectory(new File(SettingImpl.getInstance().getMainDirectory()));
               }
               return fileChooser.showSaveDialog(window);
           case OPEN:
               return new FileChooser().showOpenDialog(window);
           case DIRECTORY:
               return new DirectoryChooser().showDialog(window);
           default:
               return null;
       }
    }


    /**
     * Methods to change the application theme.
     * @param scene
     * @param o
     */
    public static void changeAppTheme(final Scene scene, final Object o) {
        if (!SettingImpl.getInstance().getAppTheme().equals(Theme.DARK)) {
            scene.getStylesheets().add(
                    Objects.requireNonNull(o.getClass().getResource(
                            Constants.DARK_THEME_CSS_LOCATION)).toExternalForm());
        } else {
            scene.getStylesheets().remove(
                    Objects.requireNonNull(o.getClass().getResource(
                            Constants.DARK_THEME_CSS_LOCATION)).toExternalForm());
        }
    }
}
