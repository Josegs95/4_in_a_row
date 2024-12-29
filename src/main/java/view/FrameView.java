package main.java.view;

import main.java.controller.Controller;
import main.java.model.BoardModel;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;

public class FrameView extends JFrame {

    private Controller controller;

    public FrameView(String title){
        super(title);
        init();
    }

    private void init(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 550);
        setLocationRelativeTo(null);

//        BoardModel model = new BoardModel();
//        BoardPanelView boardPanel = new BoardPanelView(this, model);
//        boardPanel.setController(new Controller(boardPanel, model));
//
//        getContentPane().add(boardPanel);
        StartingPanelView startingPanel = new StartingPanelView(this);
        getContentPane().add(startingPanel);
    }

    /**
     * Set the controller to the view
     *
     * @param controller the controller of the view
     */
    public void setController(Controller controller){
        this.controller = controller;
    }
}
