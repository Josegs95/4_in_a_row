package main.java.controller;

import main.java.view.MainWindow;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class WindowController extends WindowAdapter {
    private MainWindow view;

    public WindowController(MainWindow view) {
        this.view = view;
    }

    @Override
    public void windowClosing(WindowEvent e) {
        view.dispose();
    }
}
