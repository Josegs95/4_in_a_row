package main.java;

import main.java.controller.Controller;
import main.java.model.BoardModel;
import main.java.view.BoardPanelView;
import main.java.view.FrameView;

import javax.swing.*;

/**
 * Main Class which creates the GUI and initialize their components
 * <p>To see a full explanation of the application, see
 * <a href="https://github.com/Josegs95/4_in_a_row" target="_blank">4-in-a-row in Github</a></p>
 *
 * @author Josegs95
 * created at 22-12-2024
 */

public class MyGame {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
                ex.printStackTrace();
            }

//            BoardModel model = new BoardModel();
//            BoardPanelView window = new BoardPanelView("4 en raya", model);
//            new Controller(window, model);
            FrameView frame = new FrameView("4 en raya");
            frame.setVisible(true);
        });
    }
}
