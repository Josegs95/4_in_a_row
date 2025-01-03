package main.java.model;

/**
 * Represents the game modes of the game.
 */
public enum GameMode {
    /**
     * It means player vs player, where two people play against each other.
     */
    PVP,
    /**
     * It means player vs environment, where a person play against the machine.
     */
    PVE;

    @Override
    public String toString() {
        switch (this){
            case PVP -> {
                return "PvP";
            }
            case PVE -> {
                return "PvE";
            }
            default -> {
                return "unknown";
            }
        }
    }
}
