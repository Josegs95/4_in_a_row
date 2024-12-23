package main.java.model;

public class BoardModel {

    private Piece[][] board;

    public BoardModel(){
        board = new Piece[6][7];
        board[0][3] = Piece.RED;
        board[5][0] = Piece.RED;
        board[2][6] = Piece.YELLOW;
        board[5][1] = Piece.YELLOW;
    }

    public Piece[][] getBoard() {
        return board;
    }
}
