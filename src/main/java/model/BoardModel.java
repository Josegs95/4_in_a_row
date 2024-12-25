package main.java.model;

public class BoardModel {

    private Piece[][] board;
    final private int WIDTH = 7;
    final private int HEIGHT = 6;

    private boolean redTurn = true;

    public BoardModel(){
        board = new Piece[HEIGHT][WIDTH];
        board[2][6] = new Piece(Piece.PieceType.RED, 6, 2);
        board[3][5] = new Piece(Piece.PieceType.RED, 5, 3);
        board[4][4] = new Piece(Piece.PieceType.RED, 4, 4);
    }

    public Piece[][] getBoard() {
        return board;
    }

    public Piece putPiece(int index){
        int x = index % WIDTH;

        for (int i = HEIGHT - 1; i >= 0; i--){
            if (board[i][x] == null){
                Piece piece = new Piece(redTurn ? Piece.PieceType.RED : Piece.PieceType.YELLOW, x, i);
                board[i][x] = piece;
                redTurn = !redTurn;

                return piece;
            }
        }

        return null;
    }

    public boolean checkBoard(Piece piece){
        if (checkLine(piece, 1, 0, 1, false) == 4)
            return true;
        if (checkLine(piece, 0, 1, 1, false) == 4)
            return true;
        if (checkLine(piece, -1, -1, 1, false) == 4)
            return true;
        if (checkLine(piece, 1, -1, 1, false) == 4)
            return true;
        return false;
    }

    public int checkLine(Piece piece, int hIncrement, int vIncrement, int counter, boolean reversed){
        int x = piece.getX() + hIncrement;
        int y = piece.getY() + vIncrement;

        for (int i = 0; i < 3; i++){
            if (!((x > 0 && x < WIDTH) && (y > 0 && y < HEIGHT)) )
                break;

            if (board[y][x] != null && board[y][x].getType() == piece.getType()) {
                counter++;
                x += hIncrement;
                y += vIncrement;
            }
            else
                break;
        }

        if (reversed)
            return counter;
        else
            return checkLine(piece, hIncrement * -1, vIncrement * -1, counter, true);
    }
}
