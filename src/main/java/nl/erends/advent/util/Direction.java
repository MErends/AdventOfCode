package nl.erends.advent.util;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;
    
    public Direction turnRight() {
        switch (this) {
            case UP:
                return RIGHT;
            case DOWN:
                return LEFT;
            case LEFT:
                return UP;
            default:
                return DOWN;
        }
    }
    
    public Direction turnLeft() {
        switch (this) {
            case UP:
                return LEFT;
            case DOWN:
                return RIGHT;
            case LEFT:
                return DOWN;
            default:
                return UP;
        }
    }
}
