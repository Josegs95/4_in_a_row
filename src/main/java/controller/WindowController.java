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

    public boolean panelWasClicked(int index){
        Piece newPiece = model.createPieceAt(index);
        if (newPiece == null)
            return false;

        boolean is4InARow = model.checkBoard(newPiece);
        if (is4InARow)
            model.addWin();
        else
            model.switchTurn();

        view.printBoard();

        return is4InARow;
    }

    public void resetGameButtonPressed(){
        model.resetGame();
        view.resetGame();
    }

    public void resetWinCountButtonPressed(){
        model.resetAll();
        view.resetAll();
    }
}
