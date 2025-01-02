package main.java.model;

public enum GameMode {
    PVP,
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
