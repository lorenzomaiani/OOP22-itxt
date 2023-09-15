package org.example;

import javafx.application.Application;
import org.example.app.IText;

/**
 * Application Main.
 */
public final class Main {
    /**
     * Main laucher of the app.
     * @param args argument of main
     */
    public static void main(final String[] args) {
        Application.launch(IText.class, args);
    }
}
