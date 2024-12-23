package main.java.view;

import net.miginfocom.swing.MigLayout;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class MainWindow extends JFrame {
    private String title;

    public MainWindow(String title){
        super(title);
        this.title = title;

        init();
    }

    private void init(){
        setDefaultCloseOperation(DO_NOTHING_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);

        JPanel mainPanel = new JPanel(
                new MigLayout("wrap, fill, debug", "[][][][][][][]", "[][][][][][]"));
        for (int i = 0; i < 42; i++){
            mainPanel.add(new JPanel());
        }
        add(mainPanel);

        JPanel infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(400, 100));
        add(infoPanel, BorderLayout.SOUTH);

        //pack();
    }
}
