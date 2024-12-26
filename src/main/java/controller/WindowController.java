package main.java.controller;

import main.java.model.BoardModel;
import main.java.model.Piece;
import main.java.view.BoardView;

public class WindowController{
    final private BoardView VIEW;
    final private BoardModel MODEL;

    public WindowController(BoardView view, BoardModel model) {
        this.VIEW = view;
        this.MODEL = model;
    }

    public void initView(){
        VIEW.setController(this);
        VIEW.setVisible(true);
    }

    public boolean panelWasClicked(int index){
        Piece newPiece = MODEL.createPieceAt(index);
        if (newPiece == null)
            return false;

        boolean is4InARow = MODEL.checkBoard(newPiece);
        if (is4InARow)
            MODEL.addWin();
        else
            MODEL.switchTurn();

        VIEW.printBoard();

        return is4InARow;
    }

    public void resetGameButtonPressed(){
        MODEL.resetGame();
        VIEW.resetGame();
    }

    public void resetWinCountButtonPressed(){
        MODEL.resetAll();
        VIEW.resetAll();
    }
}
