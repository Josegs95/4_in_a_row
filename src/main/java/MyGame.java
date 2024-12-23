package main.java;

import main.java.controller.WindowController;
import main.java.model.BoardModel;
import main.java.view.BoardView;

import javax.swing.*;

public class MyGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            BoardModel model = new BoardModel();
            BoardView window = new BoardView("4 en raya", model);
            WindowController windowController = new WindowController(window, model);
            windowController.initView();
        });
    }
}
