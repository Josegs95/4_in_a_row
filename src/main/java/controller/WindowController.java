package main.java.controller;

import main.java.model.BoardModel;
import main.java.view.BoardView;

public class WindowController{
    private BoardView view;
    private BoardModel model;

    public WindowController(BoardView view, BoardModel model) {
        this.view = view;
        this.model = model;
    }

    public void initView(){
        view.setVisible(true);
    }
}
