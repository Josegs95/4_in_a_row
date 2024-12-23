package main.java.model;

import java.awt.*;

public enum Piece {
    RED(Color.RED), YELLOW(Color.YELLOW);

    private Color color;

    Piece(Color color){
        this.color = color;
    }
}
