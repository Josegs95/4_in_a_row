package main.java.controller;

import main.java.model.BoardModel;
import main.java.model.Piece;
import main.java.view.BoardView;

public class WindowController{
    private BoardView view;
    private BoardModel model;

    public WindowController(BoardView view, BoardModel model) {
        this.view = view;
        this.model = model;
    }

    public void initView(){
        view.setController(this);
        view.setVisible(true);
    }

    public Piece panelWasClicked(int index){
        return model.putPiece(index);
    }

    public boolean checkBoard(Piece piece){
        return model.checkBoard(piece);
    }
}
