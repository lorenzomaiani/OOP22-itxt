package org.example;

import javafx.application.Application;
import org.example.app.IText;

/**
 * Application Main.
 */
public class Main {
    /**
     * Main launcher of the app.
     * @param args argument of main
     */
    public static void main(final String[] args) {
        Application.launch(IText.class, args);
    }
}
