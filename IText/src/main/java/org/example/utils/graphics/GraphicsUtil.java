package org.example.utils.graphics;

import javafx.scene.Scene;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Window;
import org.example.model.setting.SettingImpl;
import org.example.model.setting.Theme;
import org.example.utils.constant.StringConstants;

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
               FileChooser saveFileChooser = new FileChooser();
               FileChooser.ExtensionFilter saveFilter =
                       new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
               saveFileChooser.getExtensionFilters().add(saveFilter);
               if (!SettingImpl.getInstance().getMainDirectory().equals("")) {
                   saveFileChooser.setInitialDirectory(new File(SettingImpl.getInstance().getMainDirectory()));
               }
               return saveFileChooser.showSaveDialog(window);
           case OPEN:
               FileChooser openFileChooser = new FileChooser();
               FileChooser.ExtensionFilter openFilter =
                       new FileChooser.ExtensionFilter("TEXT files (*.txt)", "*.txt");
               openFileChooser.getExtensionFilters().add(openFilter);
               if (!SettingImpl.getInstance().getMainDirectory().equals("")) {
                   openFileChooser.setInitialDirectory(new File(SettingImpl.getInstance().getMainDirectory()));
               }
               return openFileChooser.showOpenDialog(window);
           case DIRECTORY:
               return new DirectoryChooser().showDialog(window);
           default:
               return null;
       }
    }

    /**
     * Methods to change the application theme, meant to be used with observable pattern.
     * @param scene the scene that need to be change
     * @param o the root
     */
    public static void changeAppTheme(final Scene scene, final Object o) {
        if (!SettingImpl.getInstance().getAppTheme().equals(Theme.DARK)) {
            scene.getStylesheets().add(
                    Objects.requireNonNull(o.getClass().getResource(
                            StringConstants.DARK_THEME_CSS_LOCATION)).toExternalForm());
        } else {
            scene.getStylesheets().remove(
                    Objects.requireNonNull(o.getClass().getResource(
                            StringConstants.DARK_THEME_CSS_LOCATION)).toExternalForm());
        }
    }


    /**
     * Change a simple theme in the app, not meant to be used with observer pattern.
     * @param scene the scene that change color
     * @param o the object root
     */
    public static void changeSimpleTheme(final Scene scene, final Object o) {
        if (SettingImpl.getInstance().getAppTheme().equals(Theme.DARK)) {
            scene.getStylesheets().add(
                    Objects.requireNonNull(o.getClass().getResource(
                            StringConstants.DARK_THEME_CSS_LOCATION)).toExternalForm());
        }
    }
}
