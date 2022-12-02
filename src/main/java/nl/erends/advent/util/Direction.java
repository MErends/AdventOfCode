package nl.erends.advent.util;

public enum Direction {
    UP, DOWN, LEFT, RIGHT;
    
    public Direction turnRight() {
        return switch (this) {
            case UP -> RIGHT;
            case DOWN -> LEFT;
            case LEFT -> UP;
            default -> DOWN;
        };
    }
    
    public Direction turnLeft() {
        return switch (this) {
            case UP -> LEFT;
            case DOWN -> RIGHT;
            case LEFT -> DOWN;
            default -> UP;
        };
    }
}
