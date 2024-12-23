package main.java;

import main.java.controller.WindowController;
import main.java.view.MainWindow;

import javax.swing.*;

public class MyGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow("4 en raya");
            WindowController windowController = new WindowController(window);
            window.addWindowListener(windowController);

            window.setVisible(true);
        });
    }
}
