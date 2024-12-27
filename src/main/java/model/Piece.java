package main.java.model;

import java.awt.*;

/**
 * Represents a piece (red or yellow) which players use in the board to play.
 */

public class Piece {

    /**
     * The type of the piece. In this case, red and yellow pieces.
     */
    public enum PieceType{
        /**
         * Represents a red piece
         */
        RED(Color.RED),

        /**
         * Represents a yellow piece
         */
        YELLOW(Color.YELLOW);

        final private Color COLOR;

        PieceType(Color color){
            this.COLOR = color;
        }

        public Color getColor() {
            return COLOR;
        }
    }

    final private PieceType TYPE;
    final private int X;
    final private int Y;

    /**
     * Create a Piece object with the following params.
     *
     * @param type the type of the piece
     * @param x the horizontal position of the piece in the board, starting at 0
     * @param y the vertical position of the piece in the board, starting at 0
     */
    Piece(PieceType type, int x, int y) {
        this.TYPE = type;
        this.X = x;
        this.Y = y;
    }

    /**
     * Get the type of the piece.
     *
     * @return a PieceType which represents the piece's type.
     */
    public PieceType getType() {
        return TYPE;
    }

    /**
     * Get the 'x' coordinate of the piece.
     *
     * @return the horizontal position of the piece in the board, as an int.
     */
    public int getX() {
        return X;
    }

    /**
     * Get the 'y' coordinate of the piece.
     *
     * @return the vertical position of the piece in the board, as an int.
     */
    public int getY() {
        return Y;
    }

    /**
     * Get the String representation of the object.
     *
     * @return a representation of the Piece object, as a String.
     */
    @Override
    public String toString() {
        return "Color: " + TYPE.COLOR + " x: " + X + " y: " + Y;
    }
}
