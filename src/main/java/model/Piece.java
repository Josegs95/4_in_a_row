package main.java.model;

import java.awt.*;

public class Piece {

    public enum PieceType{
        RED(Color.RED), YELLOW(Color.YELLOW);

        private Color color;

        PieceType(Color color){
            this.color = color;
        }

        public Color getColor() {
            return color;
        }
    }

    private PieceType type;
    private int x;
    private int y;

    Piece(PieceType type, int x, int y) {
        this.type = type;
        this.x = x;
        this.y = y;
    }

    public PieceType getType() {
        return type;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public String toString() {
        return "Color: " + type.color + " x: " + x + " y: " + y;
    }
}
