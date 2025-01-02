package main.java.model;

import java.util.Arrays;

/**
 * Represents the board where the players put their pieces (@see main.java.model.Piece). The standard dimensions
 * are 6 'squares' of height and 7 of width (6x7). The red pieces always starts first.
 */
public class BoardModel {

    final private Piece[][] BOARD;
    final private int WIDTH = 7;
    final private int HEIGHT = 6;

    private GameMode gameMode = GameMode.PVP;
    private boolean redTurn = true;
    private int redWins = 0;
    private int yellowWins = 0;

    /**
     * Creates a BoardModel and initialize the board with the standard height and width.
     */
    public BoardModel(){
        BOARD = new Piece[HEIGHT][WIDTH];
    }

    /**
     * Gets the board, with all the pieces in it.
     *
     * @return the board, as a two-dimensional Piece array.
     */
    public Piece[][] getBoard() {
        return BOARD;
    }

    /**
     * Gets the score of the player with red pieces.
     *
     * @return the wins score of the red player.
     */
    public int getRedWins() {
        return redWins;
    }

    /**
     * Gets the score of the player with yellow pieces.
     *
     * @return the wins score of the yellow player.
     */
    public int getYellowWins() {
        return yellowWins;
    }

    /**
     * Gets if it's the turn of red pieces to play.
     *
     * @return true if it's the red turn, false if not.
     */
    public boolean isRedTurn(){
        return redTurn;
    }

    /**
     * Switch the turn, if the current turn is the red pieces, after the call of this event the turn
     * will be of the yellow pieces (and vice versa)
     */
    public void switchTurn(){
        redTurn = !redTurn;
    }

    public int getWIDTH() {
        return WIDTH;
    }

    public int getHEIGHT() {
        return HEIGHT;
    }

    public GameMode getGameMode() {
        return gameMode;
    }

    public void setGameMode(GameMode gameMode) {
        this.gameMode = gameMode;
    }

    /**
     * Try to create a new piece in the same column of the targetColumn.
     *
     * @param targetColumn the target column on which the new Piece will be created.
     * @return a new Piece object, or null if no more Pieces can be created in the column.
     */
    public Piece createPieceAt(int targetColumn){
        for (int i = HEIGHT - 1; i >= 0; i--){
            if (BOARD[i][targetColumn] == null){
                Piece piece = new Piece(redTurn ? Piece.PieceType.RED : Piece.PieceType.YELLOW, targetColumn, i);
                BOARD[i][targetColumn] = piece;

                return piece;
            }
        }

        return null;
    }

    public int getRandomAvailableColumn(){
        while(true){
            int column = (int) (Math.random() * WIDTH);
            for (int i = 0; i < HEIGHT; i++){
                if (BOARD[i][column] == null)
                    return column;
            }
        }
    }

    /**
     * Checks if a 4-in-a-row is formed with the given Piece.
     *
     * @param piece the piece to check
     * @return true if a 4-in-a-row is found in any of the 'x, y' axis and their diagonals, or false if not.
     */
    public boolean checkBoard(Piece piece){
        if (checkLine(piece, 1, 0, 1, false) == 4)
            return true;
        if (checkLine(piece, 0, 1, 1, false) == 4)
            return true;
        if (checkLine(piece, -1, -1, 1, false) == 4)
            return true;

        return checkLine(piece, 1, -1, 1, false) == 4;
    }

    /**
     * Check an individual line to be checked for a 4-in-a-row. For example, to check the horizontal line, it should
     * be used checkLine(piece, 1, 0, 1, false) to check the right line of the piece. The method then calls itself
     * to check the left line and sum the counter.
     *
     * @param piece the piece to be checked.
     * @param hIncrement the horizontal increment of the line.
     * @param vIncrement the vertical increment of the line.
     * @param counter the counter of same color pieces in-a-row in the same line.
     * @param reversed if the method is the first time to be called or not.
     * @return the counter value at the end of calling this method two times (before and after the piece).
     */
    private int checkLine(Piece piece, int hIncrement, int vIncrement, int counter, boolean reversed){
        int x = piece.getX() + hIncrement;
        int y = piece.getY() + vIncrement;

        for (int i = 0; i < 3; i++){
            if (!((x >= 0 && x < WIDTH) && (y >= 0 && y < HEIGHT)) )
                break;

            if (BOARD[y][x] != null && BOARD[y][x].getType() == piece.getType()) {
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

    /**
     * Add a win game to the player with the active turn.
     */
    public void addWin(){
        if (redTurn)
            redWins++;
        else
            yellowWins++;
    }

    /**
     * Resets the board and set the turn to red pieces
     */
    public void resetGame(){
        Arrays.stream(BOARD).forEach(x -> Arrays.fill(x, null));
        redTurn = true;
    }

    /**
     * Resets the board, the scores and set the turn to red pieces
     */
    public void resetAll(){
        redWins = 0;
        yellowWins = 0;
        resetGame();
    }
}
