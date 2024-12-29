package main.java.view;

import javax.swing.*;

/**
 * Represents the main window where the whole application will execute.
 */
public class FrameView extends JFrame {

    /**
     * Creates a FrameView object set the title to the window
     *
     * @param title the title of the window
     */
    public FrameView(String title){
        super(title);
        init();
    }

    private void init(){
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(600, 550);
        setLocationRelativeTo(null);

        StartingPanelView startingPanel = new StartingPanelView(this);
        getContentPane().add(startingPanel);
    }
}
