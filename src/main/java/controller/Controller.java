package main.java.controller;

import main.java.model.BoardModel;
import main.java.model.GameMode;
import main.java.model.Piece;
import main.java.view.BoardPanelView;

/**
 * It's called by the view when the user use the app and communicate with the model to update it.
 */

public class Controller {
    final private BoardPanelView VIEW;
    final private BoardModel MODEL;

    /**
     * Creates a Controller associated to a view and a model
     *
     * @param view the view to control
     * @param model the model with the data for the view
     */
    public Controller(BoardPanelView view, BoardModel model) {
        this.VIEW = view;
        this.MODEL = model;

        initView();
    }

    private void initView(){
        VIEW.setController(this);
        VIEW.setVisible(true);
    }

    /**
     * This method is called when the user click on a panel of the board. Create a new Piece in the same column
     * of the panel clicked and check if with that new piece, a 4-in-a-row is formed.
     *
     * @param targetColumn the target column on which the new Piece will be created.
     * @return -1 if a new Piece wasn't created, 0 if was created but no 4-in-a-row was found and 1 if it was.
     * @see main.java.model.BoardModel
     */
    public int panelWasClicked(int targetColumn){
        Piece newPiece = MODEL.createPieceAt(targetColumn);
        if (newPiece == null)
            return -1;

        boolean is4InARow = MODEL.checkBoard(newPiece);
        if (is4InARow)
            MODEL.addWin();
        else
            MODEL.switchTurn();

        return is4InARow ? 1 : 0;
    }

    /**
     * This method is called when the user click the 'Reiniciar partida' JButton or JMenuItem. Communicate to the model
     * and the view this event.
     */
    public void resetGameButtonPressed(){
        MODEL.resetGame();
        VIEW.resetGame();
    }

    /**
     * This method is called when the user click the 'Reiniciar sesión' JButton or JMenuItem. Communicate to the model
     * and the view this event.
     */
    public void resetWinCountButtonPressed(){
        MODEL.resetAll();
        VIEW.resetAll();
    }

    /**
     * This method is called when the user changes the game mode from the current one. This method reset the board and
     * the scores too.
     *
     * @param gameMode the GameMode to change into
     */
    public void changeGameMode(GameMode gameMode){
        MODEL.setGameMode(gameMode);
        MODEL.resetAll();
        VIEW.resetAll();
    }
}
